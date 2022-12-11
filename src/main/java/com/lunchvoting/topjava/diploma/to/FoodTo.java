package com.lunchvoting.topjava.diploma.to;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FoodTo extends BaseTo {

    @NotNull
    private LocalDate voteDate;

    @Size(min = 2, max = 120)
    @NotBlank
    private String description;

    @Range(min = 0, max = 1000)
    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer restaurantId;

    @ConstructorProperties({"id", "voteDate", "description", "price", "restaurantId"})
    public FoodTo(Integer id, LocalDate voteDate, String description, BigDecimal price, Integer restaurantId) {
        super(id);
        this.voteDate = voteDate;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodTo foodTo = (FoodTo) o;
        return voteDate.equals(foodTo.voteDate) &&
                description.equals(foodTo.description) &&
                price.equals(foodTo.price) &&
                restaurantId.equals(foodTo.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteDate, description, price, restaurantId);
    }
}
