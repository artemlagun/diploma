package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.RESTAURANT1_ID;
import static com.lunchvoting.topjava.diploma.testdata.FoodTestData.*;
import static org.junit.Assert.assertThrows;

public class FoodServiceTest extends AbstractBaseServiceTest {

    @Autowired
    protected FoodService service;

    @Test
    public void create() {
        Food created = service.create(getNew(), RESTAURANT1_ID);
        int newId = created.id();
        Food newFood = getNew();
        newFood.setId(newId);
        FOOD_MATCHER.assertMatch(created, newFood);
        FOOD_MATCHER.assertMatch(service.get(newId, RESTAURANT1_ID), newFood);
    }

    @Test
    public void duplicateFoodDescription() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Food(null, LocalDate.now(), food1.getDescription(),
                        new BigDecimal("6.75"), food1.getRestaurant()), RESTAURANT1_ID));
    }

    @Test
    public void delete() {
        service.delete(FOOD1_ID, RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(FOOD1_ID, RESTAURANT1_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, RESTAURANT1_ID));
    }

    @Test
    public void get() {
        Food food = service.get(FOOD1_ID, RESTAURANT1_ID);
        FOOD_MATCHER.assertMatch(food, food1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, RESTAURANT1_ID));
    }

    @Test
    public void getAllByDate() {
        List<Food> allByDate = service.getAllByDate(RESTAURANT1_ID, LocalDate.now());
        FOOD_MATCHER.assertMatch(allByDate, foods);
    }

    @Test
    public void update() {
        Food updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        FOOD_MATCHER.assertMatch(service.get(FOOD1_ID, RESTAURANT1_ID), getUpdated());
    }

    @Test
    public void getAll() {
        FOOD_MATCHER.assertMatch(service.getAll(RESTAURANT1_ID), foods);
    }
}
