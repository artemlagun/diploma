package com.lunchvoting.topjava.diploma.testdata;

import com.lunchvoting.topjava.diploma.MatcherFactory;
import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.model.Vote;

import java.time.LocalDate;

import static com.lunchvoting.topjava.diploma.model.AbstractBaseEntity.START_SEQ;
import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.*;
import static com.lunchvoting.topjava.diploma.testdata.UserTestData.*;



public class VoteTestData {

    public static final MatcherFactory.Matcher<Food> FOOD_MATCHER = MatcherFactory.usingIgnoringFieldsComparator();

    public static final int NOT_FOUND = 10;
    public static final int VOTE1_ID = START_SEQ + 21;
    public static final int VOTE2_ID = START_SEQ + 22;
    public static final int VOTE3_ID = START_SEQ + 23;

    public static final Vote vote1 = new Vote(VOTE1_ID, LocalDate.now(), firstUser, MayFlowerRestaurant);
    public static final Vote vote2 = new Vote(VOTE2_ID, LocalDate.now(), secondUser, FloriosItalianRestaurant);
    public static final Vote vote3 = new Vote(VOTE3_ID, LocalDate.now(), admin, MayFlowerRestaurant);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now().plusDays(1), UserTestData.getNew(), RestaurantTestData.getNew());
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1_ID, LocalDate.now(), firstUser, RestaurantTestData.getUpdated());
    }
}
