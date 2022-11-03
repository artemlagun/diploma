package com.lunchvoting.topjava.diploma.to;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.model.Vote;

import java.util.List;

public class RestaurantTo {
    private Integer id;
    private String name;
    private List<Food> menu;
    private List<Vote> votes;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, List<Food> menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }

    public RestaurantTo(Integer id, String name, List<Food> menu, List<Vote> votes) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public void setMenu(List<Food> menu) {
        this.menu = menu;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                ", votes=" + votes +
                '}';
    }
}
