package com.lunchvoting.topjava.diploma.to;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VoteTo {

    private Integer id;

    @NotNull
    private LocalDate voteDate;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer restaurantId;

    @ConstructorProperties({"id", "voteDate", "userId", "restaurantId"})
    public VoteTo(Integer id, LocalDate voteDate, Integer userId, Integer restaurantId) {
        this.id = id;
        this.voteDate = voteDate;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }
}
