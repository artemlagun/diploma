package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Vote;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.RESTAURANT1_ID;
import static com.lunchvoting.topjava.diploma.testdata.UserTestData.USER1_ID;
import static com.lunchvoting.topjava.diploma.testdata.VoteTestData.*;
import static org.junit.Assert.assertThrows;

public class VoteServiceTest extends AbstractBaseServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void create() {
        Vote created = service.create(getNew(), USER1_ID, RESTAURANT1_ID);
        int newId = created.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId), newVote);
    }

    @Test
    public void delete() {
        service.delete(VOTE1_ID);
        assertThrows(NotFoundException.class, () -> service.get(VOTE1_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        Vote vote = service.get(VOTE1_ID);
        VOTE_MATCHER.assertMatch(vote, vote1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void update() {
        Vote updated = getUpdated();
        service.update(updated, USER1_ID, RESTAURANT1_ID);
        VOTE_MATCHER.assertMatch(service.get(VOTE1_ID), getUpdated());
    }

    @Test
    public void getAll() {
        VOTE_MATCHER.assertMatch(service.getAll(), votes);
    }

    @Test
    public void getByUserAndDate() {
        Vote userVoteByDate = service.getByUserAndDate(USER1_ID, LocalDate.now());
        VOTE_MATCHER.assertMatch(userVoteByDate, vote1);
    }

    @Test
    public void getAllByDate() {
        List<Vote> allByDate = service.getAllByDate(LocalDate.now());
        VOTE_MATCHER.assertMatch(allByDate, votes);
    }

    @Test
    public void getByRestaurantAndDate() {
        List<Vote> votesByRestaurantAndDate = service.getByRestaurantAndDate(RESTAURANT1_ID, LocalDate.now());
        VOTE_MATCHER.assertMatch(votesByRestaurantAndDate, getVotesByRestaurantAndDate(vote1, vote3));
    }
}
