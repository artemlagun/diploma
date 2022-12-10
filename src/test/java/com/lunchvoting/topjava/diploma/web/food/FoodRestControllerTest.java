package com.lunchvoting.topjava.diploma.web.food;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.service.FoodService;
import com.lunchvoting.topjava.diploma.to.FoodTo;
import com.lunchvoting.topjava.diploma.util.FoodUtil;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import com.lunchvoting.topjava.diploma.web.AbstractControllerTest;
import com.lunchvoting.topjava.diploma.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.topjava.diploma.testdata.FoodTestData.*;
import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.RESTAURANT1_ID;
import static com.lunchvoting.topjava.diploma.testdata.RestaurantTestData.RESTAURANT3_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FoodRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/api/admin/foods/";

    @Autowired
    private FoodService service;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(FOOD_TO_MATCHER.contentJson(FoodUtil.getTos(foods)));
    }

    @Test
    void getAllByRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(FOOD_TO_MATCHER.contentJson(FoodUtil.getTos(foodsByRestaurant)));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT1_ID + '/' + FOOD1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(FOOD_TO_MATCHER.contentJson(FoodUtil.createTo(food1)));

    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT1_ID + '/' + FOOD1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(FOOD1_ID, RESTAURANT1_ID));
    }

    @Test
    void getAllByDate() throws Exception {
        List<FoodTo> expected = FoodUtil.getTos(service.getAllByDate(RESTAURANT3_ID,
                LocalDate.of(2022, 11, 7)));
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT3_ID + '/' + "by-date?voteDate="
                + LocalDate.of(2022, 11, 7)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(FOOD_TO_MATCHER.contentJson(expected));
    }

    @Test
    void createWithLocation() throws Exception {
        Food newFood = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newFood)))
                .andExpect(status().isCreated());

        FoodTo created = FOOD_TO_MATCHER.readFromJson(action);
        int newId = created.id();
        newFood.setId(newId);
        FOOD_TO_MATCHER.assertMatch(created, FoodUtil.createTo(newFood));
        FOOD_MATCHER.assertMatch(service.get(newId, RESTAURANT1_ID), newFood);
    }

    @Test
    void update() throws Exception {
        Food updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        FOOD_MATCHER.assertMatch(service.get(FOOD1_ID, RESTAURANT1_ID), updated);
    }
}