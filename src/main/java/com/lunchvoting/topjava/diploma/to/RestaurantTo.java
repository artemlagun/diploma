package com.lunchvoting.topjava.diploma.to;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;

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

    @ConstructorProperties({"id", "name"})
    public RestaurantTo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
