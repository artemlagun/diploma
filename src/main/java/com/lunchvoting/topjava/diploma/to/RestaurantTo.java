package com.lunchvoting.topjava.diploma.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lunchvoting.topjava.diploma.model.Food;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RestaurantTo {

    private Integer id;

    @Size(min = 2, max = 120)
    @NotBlank
    private String name;

    private List<Food> menu;

    @ConstructorProperties({"id", "name"})
    public RestaurantTo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @ConstructorProperties({"id", "name", "menu"})
    public RestaurantTo(Integer id, String name, List<Food> menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }
}
