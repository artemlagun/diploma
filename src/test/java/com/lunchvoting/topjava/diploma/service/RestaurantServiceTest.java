package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.model.Restaurant;
import com.lunchvoting.topjava.diploma.testdata.FoodTestData;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.lunchvoting.topjava.diploma.testdata.FoodTestData.FOOD_MATCHER;
import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
     void create() {
        Restaurant created = service.create(getNew());
        int newId = created.id();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
     void delete() {
        service.delete(RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT1_ID));
    }

    @Test
     void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
     void get() {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurant1);
    }

    @Test
     void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
     void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT1_ID), getUpdated());
    }

    @Test
     void getAll() {
        RESTAURANT_MATCHER.assertMatch(service.getAll(), restaurants);
    }

    @Test
     void getMenuOfDay() {
        List<Food> restaurantMenuOfDay = service.getMenuOfDay(RESTAURANT2_ID).getFoods();
        FOOD_MATCHER.assertMatch(restaurantMenuOfDay, FoodTestData.menuOfDay);
    }

    @Test
     void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Restaurant(null, "  ")));
    }
}
