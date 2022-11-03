package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.repository.FoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
public class FoodService {

    private final FoodRepository repository;

    public FoodService(FoodRepository repository) {
        this.repository = repository;
    }

    public Food get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public List<Food> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public List<Food> getAllByDate(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date shouldn't be null");
        return repository.getAllByDate(restaurantId,date);
    }

    public void update(Food food, int restaurantId) {
        Assert.notNull(food, "food shouldn't be null");
        checkNotFoundWithId(repository.save(food, restaurantId), food.id());
    }

    public Food create(Food food, int restaurantId) {
        Assert.notNull(food, "food shouldn't be null");
        return repository.save(food, restaurantId);
    }
}
