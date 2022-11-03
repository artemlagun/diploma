package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Vote;
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
    private final RestaurantService restaurantService;
    private final UserService userService;

    public VoteService(VoteRepository repository, RestaurantService restaurantService, UserService userService) {
        this.repository = repository;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Vote> getAll() {
        return repository.getAll();
    }

    public Vote getByUserAndDate(int userId, LocalDate date) {
        Assert.notNull(date, "date shouldn't be null");
        return checkNotFoundWithId(repository.getByUserAndDate(userId, date), userId);
    }

    public List<Vote> getAllByDate(LocalDate date) {
        Assert.notNull(date, "date shouldn't be null");
        return checkNotFound(repository.getAllByDate(date), "date=" + date);
    }

    public List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date shouldn't be null");
        return checkNotFoundWithId(repository.getByRestaurantAndDate(restaurantId, date), restaurantId);
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote shouldn't be null");
        votingTimeVerification();
        vote.setUser(userService.get(userId));
        vote.setRestaurant(restaurantService.get(restaurantId));
        return repository.save(vote);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote shouldn't be null");
        votingTimeVerification();
        vote.setUser(userService.get(userId));
        vote.setRestaurant(restaurantService.get(restaurantId));
        checkNotFoundWithId(repository.save(vote), vote.id());
    }
}
