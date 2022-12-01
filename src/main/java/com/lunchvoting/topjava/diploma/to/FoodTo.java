package com.lunchvoting.topjava.diploma.to;

import com.lunchvoting.topjava.diploma.model.Restaurant;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
}
