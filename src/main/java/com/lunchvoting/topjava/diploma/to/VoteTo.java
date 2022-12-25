package com.lunchvoting.topjava.diploma.to;

import lombok.*;

import java.beans.ConstructorProperties;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoteTo extends BaseTo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDate voteDate;

    private Integer userId;

    private Integer restaurantId;

    @ConstructorProperties({"id", "voteDate", "userId", "restaurantId"})
    public VoteTo(Integer id, LocalDate voteDate, Integer userId, Integer restaurantId) {
        super(id);
        this.voteDate = voteDate;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteTo voteTo = (VoteTo) o;
        return voteDate.equals(voteTo.voteDate) &&
                userId.equals(voteTo.userId) &&
                restaurantId.equals(voteTo.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteDate, userId, restaurantId);
    }
}
