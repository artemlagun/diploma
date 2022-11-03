package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.model.Restaurant;
import com.lunchvoting.topjava.diploma.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;
    private final FoodService foodService;

    public RestaurantService(RestaurantRepository repository, FoodService foodService) {
        this.repository = repository;
        this.foodService = foodService;
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public Restaurant getMenuOfDay(int id) {
        Restaurant restaurant = checkNotFoundWithId(repository.get(id),id);
        List<Food> menuOfDay = foodService.getAllByDate(id, LocalDate.now());
        restaurant.setMenu(menuOfDay);
        return restaurant;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant shouldn't be null");
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant shouldn't be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }
}
