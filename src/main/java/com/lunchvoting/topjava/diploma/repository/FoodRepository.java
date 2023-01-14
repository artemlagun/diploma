package com.lunchvoting.topjava.diploma.repository;

import com.lunchvoting.topjava.diploma.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Food f WHERE f.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT f FROM Food f WHERE f.restaurant.id=:restaurantId ORDER BY f.voteDate DESC")
    List<Food> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT f FROM Food f WHERE f.restaurant.id=:restaurantId AND f.voteDate=:voteDate ORDER BY f.voteDate, f.id")
    List<Food> getAllByRestaurantAndDate(@Param("restaurantId") int restaurantId, @Param("voteDate") LocalDate voteDate);

    @Query("SELECT f FROM Food f WHERE f.voteDate=:voteDate ORDER BY f.voteDate, f.id")
    List<Food> getAllByDate(@Param("voteDate") LocalDate voteDate);
}
