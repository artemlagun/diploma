package com.lunchvoting.topjava.diploma.to;

import com.lunchvoting.topjava.diploma.model.Restaurant;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FoodTo {

    private Integer id;

    @NotNull
    private LocalDate voteDate;

    @Size(min = 2, max = 120)
    private String description;

    @Range(min = 0, max = 1000)
    private BigDecimal price;

    @NotNull
    private Integer restaurantId;

    @ConstructorProperties({"id", "voteDate", "description", "price", "restaurantId"})
    public FoodTo(Integer id, LocalDate voteDate, String description, BigDecimal price, Integer restaurantId) {
        this.id = id;
        this.voteDate = voteDate;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }
}
