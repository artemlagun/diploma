package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Vote;
import com.lunchvoting.topjava.diploma.repository.RestaurantRepository;
import com.lunchvoting.topjava.diploma.repository.UserRepository;
import com.lunchvoting.topjava.diploma.repository.VoteRepository;
import com.lunchvoting.topjava.diploma.util.exception.OutOfTimeException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.*;

@Service
public class VoteService {

    private static final Sort SORT_DATE = Sort.by(Sort.Direction.DESC, "voteDate", "id");

    private Clock clock = Clock.system(ZoneId.systemDefault());

    private final VoteRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository repository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public Vote get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public List<Vote> getAll() {
        return repository.findAll(SORT_DATE);
    }

    public Vote getByUserAndDate(int userId, LocalDate voteDate) {
        Assert.notNull(voteDate, "voteDate shouldn't be null");
        return checkNotFoundWithId(repository.getByUserAndDate(userId, voteDate), userId);
    }

    public List<Vote> getAllByDate(LocalDate voteDate) {
        Assert.notNull(voteDate, "date shouldn't be null");
        return checkNotFound(repository.getAllByDate(voteDate), "date=" + voteDate);
    }

    public List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate voteDate) {
        Assert.notNull(voteDate, "date shouldn't be null");
        return checkNotFoundWithId(repository.getByRestaurantAndDate(restaurantId, voteDate), restaurantId);
    }

    @Transactional
    public Vote create(int userId, int restaurantId) {
        Vote vote = new Vote(null, LocalDate.now(), userRepository.findById(userId).orElse(null),
                restaurantRepository.findById(restaurantId).orElse(null));
        Assert.notNull(vote, "vote shouldn't be null");
        checkNew(vote);
        votingTimeVerification(clock);
        return repository.save(vote);
    }

    @Transactional
    public void update(int id, int restaurantId) {
        Vote vote = repository.findById(id).orElseThrow(() -> new OutOfTimeException("Now " + LocalTime.now() +
                ", sorry voting time expired. You could vote till 11:00:00"));
        Assert.notNull(vote, "vote shouldn't be null");
        assureIdConsistent(vote, vote.id());
        votingTimeVerification(clock);
        vote.setRestaurant(restaurantRepository.findById(restaurantId).orElse(null));
        checkNotFoundWithId(repository.save(vote), vote.id());
    }
}
