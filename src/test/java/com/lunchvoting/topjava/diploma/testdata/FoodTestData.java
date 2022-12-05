package com.lunchvoting.topjava.diploma.testdata;

import com.lunchvoting.topjava.diploma.MatcherFactory;
import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.to.FoodTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.*;
import static com.lunchvoting.topjava.diploma.model.AbstractBaseEntity.START_SEQ;

public class FoodTestData {

    public static final MatcherFactory.Matcher<Food> FOOD_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Food.class, "restaurant");
    public static final MatcherFactory.Matcher<FoodTo> FOOD_TO_MATCHER = MatcherFactory.usingEqualsComparator(FoodTo.class);

    public static final int NOT_FOUND = 10;
    public static final int FOOD1_ID = START_SEQ + 6;

    public static final Food food1 = new Food(FOOD1_ID, LocalDate.now(), "Beef tips with rice", new BigDecimal("5.75"), restaurant1);
    public static final Food food2 = new Food(FOOD1_ID + 1, LocalDate.now(), "Country fried steak", new BigDecimal("6.25"), restaurant1);
    public static final Food food3 = new Food(FOOD1_ID + 2, LocalDate.now(), "Potato salad", new BigDecimal("4.50"), restaurant1);
    public static final Food food4 = new Food(FOOD1_ID + 3, LocalDate.now(), "Hot coffee", new BigDecimal("1.25"), restaurant1);
    public static final Food food5 = new Food(FOOD1_ID + 4, LocalDate.now(), "Lemonade", new BigDecimal("1.25"), restaurant1);

    public static final Food food6 = new Food(FOOD1_ID + 5, LocalDate.now(), "Center cut Virginia ham", new BigDecimal("10.75"), restaurant2);
    public static final Food food7 = new Food(FOOD1_ID + 6, LocalDate.now(), "Filet of catfish", new BigDecimal("11.25"), restaurant2);
    public static final Food food8 = new Food(FOOD1_ID + 7, LocalDate.now(), "Dinner salad", new BigDecimal("3.25"), restaurant2);
    public static final Food food9 = new Food(FOOD1_ID + 8, LocalDate.now(), "Americano", new BigDecimal("2.25"), restaurant2);
    public static final Food food10 = new Food(FOOD1_ID + 9, LocalDate.now(), "Cappuccino", new BigDecimal("2.25"), restaurant2);

    public static final Food food11 = new Food(FOOD1_ID + 10, LocalDate.now(), "Meet lovers pizza", new BigDecimal("11.95"), restaurant3);
    public static final Food food12 = new Food(FOOD1_ID + 11, LocalDate.now(), "Pasta Bolognese", new BigDecimal("10.95"), restaurant3);
    public static final Food food13 = new Food(FOOD1_ID + 12, LocalDate.now(), "Cesar salad", new BigDecimal("8.95"), restaurant3);
    public static final Food food14 = new Food(FOOD1_ID + 13, LocalDate.now(), "Espresso", new BigDecimal("1.85"), restaurant3);
    public static final Food food15 = new Food(FOOD1_ID + 14, LocalDate.now(), "Cappuccino", new BigDecimal("2.45"), restaurant3);

    public static final List<Food> foods = List.of(food1, food2, food3, food4, food5);
    public static final List<Food> menuOfDay = List.of(food6, food7, food8, food9, food10);

    public static Food getNew() {
        return new Food(null, LocalDate.now().plusDays(10), "New food", new BigDecimal("12.55"), restaurant1);
    }

    public static Food getUpdated() {
        return new Food(FOOD1_ID, food1.getVoteDate().plusDays(1), "Updated food", new BigDecimal("6.75"), food1.getRestaurant());
    }
}
