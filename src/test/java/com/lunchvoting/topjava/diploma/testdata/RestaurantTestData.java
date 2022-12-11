package com.lunchvoting.topjava.diploma.testdata;

import com.lunchvoting.topjava.diploma.MatcherFactory;
import com.lunchvoting.topjava.diploma.model.Restaurant;
import com.lunchvoting.topjava.diploma.to.RestaurantTo;

import java.util.List;

import static com.lunchvoting.topjava.diploma.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu", "votes");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final int NOT_FOUND = 10;
    public static final int RESTAURANT1_ID = START_SEQ + 4;
    public static final int RESTAURANT2_ID = START_SEQ + 5;
    public static final int RESTAURANT3_ID = START_SEQ + 6;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Mayflower restaurant");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Chaf-if restaurant");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT3_ID, "Florio's italian restaurant");

    public static final List<Restaurant> restaurants = List.of(restaurant2, restaurant3, restaurant1);

    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant");
    }
}
