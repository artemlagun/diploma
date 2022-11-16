package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.model.Restaurant;
import com.lunchvoting.topjava.diploma.repository.FoodRepository;
import com.lunchvoting.topjava.diploma.repository.RestaurantRepository;
import org.hsqldb.lib.Collection;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final RestaurantRepository repository;
    private final FoodRepository foodRepository;

    public RestaurantService(RestaurantRepository repository, FoodRepository foodRepository) {
        this.repository = repository;
        this.foodRepository = foodRepository;
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public List<Restaurant> getAll() {
        return repository.findAll(SORT_NAME);
    }

    public Restaurant getMenuOfDay(int id) {
        Restaurant restaurant = checkNotFoundWithId(repository.findById(id).orElse(null),id);
        Assert.notNull(restaurant, "restaurant shouldn't be null");
        List<Food> menuOfDay = foodRepository.getAll(id)
                .stream()
                .filter(all -> all.getDate().isEqual(LocalDate.now()))
                .toList();
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
