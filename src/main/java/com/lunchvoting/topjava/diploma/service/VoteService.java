package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Vote;
import com.lunchvoting.topjava.diploma.repository.RestaurantRepository;
import com.lunchvoting.topjava.diploma.repository.UserRepository;
import com.lunchvoting.topjava.diploma.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNotFound;
import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNotFoundWithId;
import static com.lunchvoting.topjava.diploma.util.ValidationUtil.votingTimeVerification;

@Service
public class VoteService {

    private final VoteRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository repository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Vote get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Vote> getAll() {
        return repository.findAll();
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

    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote shouldn't be null");
        votingTimeVerification();
        vote.setUser(userRepository.findById(userId).orElse(null));
        vote.setRestaurant(restaurantRepository.findById(restaurantId).orElse(null));
        return repository.save(vote);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote shouldn't be null");
        votingTimeVerification();
        vote.setUser(userRepository.findById(userId).orElse(null));
        vote.setRestaurant(restaurantRepository.findById(restaurantId).orElse(null));
        checkNotFoundWithId(repository.save(vote), vote.id());
    }
}
