package com.lunchvoting.topjava.diploma.to;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoteTo {

    private Integer id;

    @NotNull
    private LocalDate voteDate;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer restaurantId;
}
