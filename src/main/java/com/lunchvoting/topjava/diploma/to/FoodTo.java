package com.lunchvoting.topjava.diploma.to;

import lombok.*;

import java.beans.ConstructorProperties;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FoodTo extends BaseTo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDate voteDate;

    private String description;

    private BigDecimal price;

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
