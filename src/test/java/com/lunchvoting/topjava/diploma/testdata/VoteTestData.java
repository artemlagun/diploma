package com.lunchvoting.topjava.diploma.testdata;

import com.lunchvoting.topjava.diploma.MatcherFactory;
import com.lunchvoting.topjava.diploma.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.topjava.diploma.model.AbstractBaseEntity.START_SEQ;
import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.*;
import static com.lunchvoting.topjava.diploma.testdata.UserTestData.*;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user", "restaurant");

    public static final int NOT_FOUND = 10;
    public static final int VOTE1_ID = START_SEQ + 21;
    public static final int VOTE2_ID = START_SEQ + 22;
    public static final int VOTE3_ID = START_SEQ + 23;

    public static final Vote vote1 = new Vote(VOTE1_ID, LocalDate.now(), user1, restaurant1);
    public static final Vote vote2 = new Vote(VOTE2_ID, LocalDate.now(), user2, restaurant3);
    public static final Vote vote3 = new Vote(VOTE3_ID, LocalDate.now(), admin, restaurant1);

    public static final List<Vote> votes = List.of(vote3, vote2, vote1);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now().plusDays(1), UserTestData.getNew(), RestaurantTestData.getNew());
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1_ID, LocalDate.now(), user1, RestaurantTestData.getUpdated());
    }

    public static List<Vote> getVotesByRestaurantAndDate(Vote... votes) {
        restaurant1.setVotes(List.of(votes));
        return restaurant1.getVotes()
                .stream()
                .filter(vote -> vote.getVoteDate().isEqual(LocalDate.now()))
                .toList();
    }
}
