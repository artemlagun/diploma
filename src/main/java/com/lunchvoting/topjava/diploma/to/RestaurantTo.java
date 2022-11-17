package com.lunchvoting.topjava.diploma.to;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.model.Vote;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantTo {

    private Integer id;

    @Size(min = 2, max = 120)
    private String name;

    private List<Food> menu;

    private List<Vote> votes;

    public RestaurantTo(Integer id, String name, List<Food> menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }
}
