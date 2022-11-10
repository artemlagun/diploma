package com.lunchvoting.topjava.diploma.testdata;

import com.lunchvoting.topjava.diploma.MatcherFactory;
import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.model.Restaurant;

import java.util.List;

import static com.lunchvoting.topjava.diploma.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator();

    public static final int NOT_FOUND = 10;
    public static final int RESTAURANT1_ID = START_SEQ + 3;

    public static final Restaurant MayFlowerRestaurant = new Restaurant(RESTAURANT1_ID, "Mayflower restaurant");
    public static final Restaurant ChafIfRestaurant = new Restaurant(RESTAURANT1_ID + 1, "Chaf-if restaurant");
    public static final Restaurant FloriosItalianRestaurant = new Restaurant(RESTAURANT1_ID + 2, "Florio's italian restaurant");

    public static final List<Restaurant> restaurants = List.of(MayFlowerRestaurant, ChafIfRestaurant, ChafIfRestaurant);

    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant");
    }
}
