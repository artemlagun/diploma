package com.lunchvoting.topjava.diploma.repository;

import com.lunchvoting.topjava.diploma.model.Food;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodRepository {

    Food save(Food food, int restaurantId);

    boolean delete(int id, int restaurantId);

    Food get(int id, int restaurantId);

    List<Food> getAll(int restaurantId);

    List<Food> getAllByDate(int restaurantId, LocalDate date);
}
