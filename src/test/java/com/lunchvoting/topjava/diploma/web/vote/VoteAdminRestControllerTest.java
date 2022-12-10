package com.lunchvoting.topjava.diploma.web.vote;

import com.lunchvoting.topjava.diploma.model.Vote;
import com.lunchvoting.topjava.diploma.service.VoteService;
import com.lunchvoting.topjava.diploma.to.VoteTo;
import com.lunchvoting.topjava.diploma.util.VoteUtil;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import com.lunchvoting.topjava.diploma.web.AbstractControllerTest;
import com.lunchvoting.topjava.diploma.web.json.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.*;

import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.RESTAURANT1_ID;
import static com.lunchvoting.topjava.diploma.testdata.UserTestData.USER1_ID;
import static com.lunchvoting.topjava.diploma.testdata.VoteTestData.*;
import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteAdminRestControllerTest extends AbstractControllerTest {

    private final LocalDateTime testTime = LocalDateTime.of(2022, 11, 23, 10, 30);
    private final ZoneId zoneId = ZoneId.systemDefault();
    private final Clock fixedClock = Clock.fixed(testTime.atZone(zoneId).toInstant(), zoneId);

    private static final String REST_URL = "/api/admin/votes/";

    @Autowired
    private VoteService service;

    @BeforeEach
    public void setUp() {
        service.setClock(fixedClock);
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.createTo(service.get(VOTE1_ID))));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + VOTE1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.delete(VOTE1_ID));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.getTos(votes)));
    }

    @Test
    void getByUserAndDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "by-user-date?userId=" + USER1_ID
                + "&voteDate=" + LocalDate.now()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.createTo(vote1)));
    }

    @Test
    void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "by-date?voteDate="
                + LocalDate.now().minusDays(1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.getTos(votesByDate)));
    }

    @Test
    void getByRestaurantAndDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT1_ID + "/by-date?voteDate="
                + LocalDate.now().minusDays(1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.getTos(votesByRestaurantAndDate)));
    }

    @Test
    void createWithLocation() throws Exception {
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT1_ID
                + "/?userId=" + USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isCreated());

        VoteTo created = VOTE_TO_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_TO_MATCHER.assertMatch(created, VoteUtil.createTo(newVote));
        VOTE_MATCHER.assertMatch(service.get(newId), newVote);
    }

    @Test
    void update() throws Exception {
        Vote updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + VOTE1_ID + '/' + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        VOTE_MATCHER.assertMatch(service.get(VOTE1_ID), updated);
    }
}