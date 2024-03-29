package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.RESTAURANT1_ID;
import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.RESTAURANT3_ID;
import static com.lunchvoting.topjava.diploma.testdata.FoodTestData.*;
import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.restaurant1;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FoodServiceTest extends AbstractServiceTest {

    @Autowired
    protected FoodService service;

    @Test
    void create() {
        Food created = service.create(getNew(), RESTAURANT1_ID);
        int newId = created.id();
        Food newFood = getNew();
        newFood.setId(newId);
        FOOD_MATCHER.assertMatch(created, newFood);
        FOOD_MATCHER.assertMatch(service.get(newId), newFood);
    }

    @Test
    void duplicateFoodDescription() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Food(null, LocalDate.now(), food1.getDescription(),
                        new BigDecimal("6.75"), food1.getRestaurant()), RESTAURANT1_ID));
    }

    @Test
    void delete() {
        service.delete(FOOD1_ID);
        assertThrows(NotFoundException.class, () -> service.get(FOOD1_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void get() {
        Food food = service.get(FOOD1_ID);
        FOOD_MATCHER.assertMatch(food, food1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getAllByDate() {
        List<Food> allByDate = service.getAllByDate(LocalDate.of(2022, 11, 7));
        FOOD_MATCHER.assertMatch(allByDate, foodsByDate);
    }

    @Test
    void getAllByRestaurantAndDate() {
        List<Food> allByDate = service.getAllByRestaurantAndDate(RESTAURANT3_ID, LocalDate.of(2022, 11, 7));
        FOOD_MATCHER.assertMatch(allByDate, foodsByDate);
    }

    @Test
    void update() {
        Food updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        FOOD_MATCHER.assertMatch(service.get(FOOD1_ID), getUpdated());
    }

    @Test
    void getAll() {
        FOOD_MATCHER.assertMatch(service.getAll(), foods);
    }

    @Test
    void getAllByRestaurant() {
        FOOD_MATCHER.assertMatch(service.getAllByRestaurant(RESTAURANT1_ID), foodsByRestaurant);
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Food(null, null,
                "New Food", new BigDecimal("9.99"), restaurant1), RESTAURANT1_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Food(null, LocalDate.now(),
                "  ", new BigDecimal("9.99"), restaurant1), RESTAURANT1_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Food(null, LocalDate.now(),
                "New Food", new BigDecimal("-1.0"), restaurant1), RESTAURANT1_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Food(null, LocalDate.now(),
                "New Food", new BigDecimal("10001.0"), restaurant1), RESTAURANT1_ID));
    }
}
