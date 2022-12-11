package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Vote;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import com.lunchvoting.topjava.diploma.util.exception.OutOfTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.time.*;
import java.util.List;

import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.RESTAURANT1_ID;
import static com.lunchvoting.topjava.diploma.testdata.UserTestData.USER1_ID;
import static com.lunchvoting.topjava.diploma.testdata.VoteTestData.*;
import static org.junit.Assert.assertThrows;

 class VoteServiceTest extends AbstractServiceTest {

    private final LocalDateTime testTime = LocalDateTime.of(2022, 11, 23, 10, 30);
    private final LocalDateTime outOfTestTime = LocalDateTime.of(2022, 11, 23, 11, 1);
    private final ZoneId zoneId = ZoneId.systemDefault();
    private final Clock fixedClock = Clock.fixed(testTime.atZone(zoneId).toInstant(), zoneId);
    private final Clock fixedOutOfTimeClock = Clock.fixed(outOfTestTime.atZone(zoneId).toInstant(), zoneId);

    @Autowired
    private VoteService service;

    @BeforeEach
     void setUp() {
        service.setClock(fixedClock);
    }

    @Test
     void create() {
        service.delete(VOTE1_ID);
        Vote created = service.create(USER1_ID, RESTAURANT1_ID);
        int newId = created.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId), newVote);
    }

    @Test
     void createOutOfTime() {
        service.setClock(fixedOutOfTimeClock);
        assertThrows(OutOfTimeException.class, () -> service.create(USER1_ID, RESTAURANT1_ID));
    }

    @Test
     void delete() {
        service.delete(VOTE1_ID);
        assertThrows(NotFoundException.class, () -> service.get(VOTE1_ID));
    }

    @Test
     void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
     void get() {
        Vote vote = service.get(VOTE1_ID);
        VOTE_MATCHER.assertMatch(vote, vote1);
    }

    @Test
     void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
     void update() {
        service.update(VOTE1_ID, RESTAURANT1_ID);
        VOTE_MATCHER.assertMatch(service.get(VOTE1_ID), getUpdated());
    }

    @Test
     void updateOutOfTime() {
        service.setClock(fixedOutOfTimeClock);
        assertThrows(OutOfTimeException.class, () -> service.update(USER1_ID, RESTAURANT1_ID));
    }

    @Test
     void getAll() {
        VOTE_MATCHER.assertMatch(service.getAll(), votes);
    }

    @Test
     void getByUserAndDate() {
        Vote userVoteByDate = service.getByUserAndDate(USER1_ID, LocalDate.of(2022, 11, 7));
        VOTE_MATCHER.assertMatch(userVoteByDate, vote3);
    }

    @Test
     void getAllByDate() {
        List<Vote> allByDate = service.getAllByDate(LocalDate.of(2022, 11, 7));
        VOTE_MATCHER.assertMatch(allByDate, vote4, vote3);
    }

    @Test
     void getByRestaurantAndDate() {
        List<Vote> votesByRestaurantAndDate = service.getByRestaurantAndDate(RESTAURANT1_ID,
                LocalDate.of(2022, 11, 7));
        VOTE_MATCHER.assertMatch(votesByRestaurantAndDate, vote3);
    }

    @Test
     void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> service.create(0, RESTAURANT1_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(USER1_ID, 0));
    }
}
