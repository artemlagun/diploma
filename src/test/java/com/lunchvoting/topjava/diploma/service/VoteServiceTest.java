package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Vote;
import com.lunchvoting.topjava.diploma.util.ValidationUtil;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import com.lunchvoting.topjava.diploma.util.exception.OutOfTimeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import javax.validation.ConstraintViolationException;
import java.time.*;
import java.util.List;

import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.RESTAURANT1_ID;
import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.restaurant1;
import static com.lunchvoting.topjava.diploma.testdata.UserTestData.USER1_ID;
import static com.lunchvoting.topjava.diploma.testdata.UserTestData.user1;
import static com.lunchvoting.topjava.diploma.testdata.VoteTestData.*;
import static org.junit.Assert.assertThrows;

public class VoteServiceTest extends AbstractServiceTest {

    private final LocalDateTime testTime = LocalDateTime.of(2022, 11, 23, 10, 30);
    private final LocalDateTime outOfTestTime = LocalDateTime.of(2022, 11, 23, 11, 1);
    private final ZoneId zoneId = ZoneId.systemDefault();
    private final Clock fixedClock = Clock.fixed(testTime.atZone(zoneId).toInstant(), zoneId);
    private final Clock fixedOutOfTimeClock = Clock.fixed(outOfTestTime.atZone(zoneId).toInstant(), zoneId);

    @Autowired
    private VoteService service;

    @Before
    public void setUp() {
        service.setClock(fixedClock);
    }

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
    public void createOutOfTime() {
        service.setClock(fixedOutOfTimeClock);
        assertThrows(OutOfTimeException.class, () -> service.create(getNew(), USER1_ID, RESTAURANT1_ID));
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
    public void updateOutOfTime() {
        service.setClock(fixedOutOfTimeClock);
        assertThrows(OutOfTimeException.class, () -> service.update(getUpdated(), USER1_ID, RESTAURANT1_ID));
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

    @Test
    public void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class,
                () -> service.create(new Vote(null, null, user1, restaurant1), USER1_ID, RESTAURANT1_ID));
        validateRootCause(ConstraintViolationException.class,
                () -> service.create(new Vote(null, LocalDate.now(), null, restaurant1), 0, RESTAURANT1_ID));
        validateRootCause(ConstraintViolationException.class,
                () -> service.create(new Vote(null, LocalDate.now(), user1, null), USER1_ID, 0));
    }
}
