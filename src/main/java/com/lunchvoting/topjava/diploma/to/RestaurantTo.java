package com.lunchvoting.topjava.diploma.to;

import com.lunchvoting.topjava.diploma.model.Food;
import lombok.*;

import java.beans.ConstructorProperties;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantTo extends BaseTo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private List<Food> foods;

    @ConstructorProperties({"id", "name"})
    public RestaurantTo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @ConstructorProperties({"id", "name", "menu"})
    public RestaurantTo(Integer id, String name, List<Food> foods) {
        super(id);
        this.name = name;
        this.foods = foods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo that = (RestaurantTo) o;
        return name.equals(that.name) && Objects.equals(foods, that.foods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, foods);
    }
}
