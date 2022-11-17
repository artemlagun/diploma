package com.lunchvoting.topjava.diploma.util;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.to.FoodTo;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class FoodUtil {

    public static List<FoodTo> getTos (Collection<Food> foods) {
        return foods.stream().map(FoodUtil::createTo).toList();
    }

    public static FoodTo createTo (Food food) {
        return new FoodTo(food.getId(), food.getVoteDate(), food.getDescription(), food.getPrice(), food.getRestaurant());
    }
}
