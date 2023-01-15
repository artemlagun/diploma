package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.repository.FoodRepository;
import com.lunchvoting.topjava.diploma.repository.RestaurantRepository;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.*;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkExisted;
import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
public class FoodService {

    private final FoodRepository repository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodService(FoodRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    public Food get(int id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Food " + id + " not found"));
    }

    @CacheEvict(value = "foods", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Cacheable("foods")
    public List<Food> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "foods", allEntries = true)
    public List<Food> getAllByRestaurant(int restaurantId) {
        checkExisted(restaurantRepository, restaurantId);
        return repository.getAll(restaurantId);
    }

    public List<Food> getAllByDate(LocalDate prepDate) {
        Assert.notNull(prepDate, "prepDate shouldn't be null");
        List<Food> foods = repository.getAllByDate(prepDate);
        return checkExisted(foods, prepDate);
    }

    public List<Food> getAllByRestaurantAndDate(int restaurantId, LocalDate prepDate) {
        Assert.notNull(prepDate, "prepDate shouldn't be null");
        List<Food> foods = repository.getAllByRestaurantAndDate(restaurantId, prepDate);
        return checkExisted(foods, prepDate, restaurantId);
    }

    @CacheEvict(value = "foods", allEntries = true)
    @Transactional
    public void update(Food food, int restaurantId) {
        Assert.notNull(food, "food shouldn't be null");
        food.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new NotFoundException("Restaurant " + restaurantId + " not found")));
        checkNotFoundWithId(repository.save(food), food.id());
    }

    @CacheEvict(value = "foods", allEntries = true)
    @Transactional
    public Food create(Food food, int restaurantId) {
        Assert.notNull(food, "food shouldn't be null");
        food.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new NotFoundException("Restaurant " + restaurantId + " not found")));
        return repository.save(food);
    }
}
