package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.model.Restaurant;
import com.lunchvoting.topjava.diploma.testdata.FoodTestData;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;

import static com.lunchvoting.topjava.diploma.testdata.FoodTestData.FOOD_MATCHER;
import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.*;
import static org.junit.Assert.assertThrows;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        Objects.requireNonNull(cacheManager.getCache("restaurants")).clear();
    }

    @Test
    public void create() {
        Restaurant created = service.create(getNew());
        int newId = created.id();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT1_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurant1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT1_ID), getUpdated());
    }

    @Test
    public void getAll() {
        RESTAURANT_MATCHER.assertMatch(service.getAll(), restaurants);
    }

    @Test
    public void getMenuOfDay() {
        List<Food> restaurantMenuOfDay = service.getMenuOfDay(RESTAURANT2_ID).getMenu();
        FOOD_MATCHER.assertMatch(restaurantMenuOfDay, FoodTestData.menuOfDay);
    }

    @Test
    public void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Restaurant(null, "  ")));
    }
}
