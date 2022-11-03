package com.lunchvoting.topjava.diploma.repository;

import com.lunchvoting.topjava.diploma.model.Vote;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(int id);

    Vote get(int id);

    Vote getByUserAndDate(int userId, LocalDate date);

    List<Vote> getAll();

    List<Vote> getAllByDate(LocalDate date);

    List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate date);
}
