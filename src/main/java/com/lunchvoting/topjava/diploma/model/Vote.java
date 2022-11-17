package com.lunchvoting.topjava.diploma.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"user_id", "restaurant_id", "vote_date"}, name = "vote_unique_user_restaurant_vote_date_idx")})
@NoArgsConstructor
@Getter
@Setter
public class Vote extends AbstractBaseEntity {

    @Column(name = "vote_date", nullable = false)
    @NotNull
    private LocalDate voteDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @NotNull
    private Restaurant restaurant;

    public Vote(LocalDate voteDate, User user, Restaurant restaurant) {
        this(null, voteDate, user, restaurant);
    }

    public Vote(Integer id, LocalDate voteDate, User user, Restaurant restaurant) {
        super(id);
        this.voteDate = voteDate;
        this.user = user;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", voteDate=" + voteDate +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
