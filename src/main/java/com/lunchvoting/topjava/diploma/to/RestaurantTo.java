package com.lunchvoting.topjava.diploma.to;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.model.Vote;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantTo {

    private Integer id;

    @Size(min = 2, max = 120)
    @NotBlank
    private String name;

    public RestaurantTo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
