package com.lunchvoting.topjava.diploma.util;

import com.lunchvoting.topjava.diploma.model.Restaurant;
import com.lunchvoting.topjava.diploma.to.RestaurantTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class RestaurantUtil {

    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantUtil::createTo).toList();
    }

    public static List<RestaurantTo> getTosWithMenu(Collection<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantUtil::createToWithMenu).toList();
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public static RestaurantTo createToWithMenu(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getFoods());
    }
}
