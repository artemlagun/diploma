package com.lunchvoting.topjava.diploma.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "foods", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"restaurant_id", "vote_date", "description"}, name = "food_unique_restaurant_vote_date_description_idx")})
public class Food extends AbstractBaseEntity {

    @Column(name = "vote_date", nullable = false)
    @NotNull
    private LocalDate voteDate;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "price", nullable = false)
    @Range(min = 0, max = 1000)
    private BigDecimal price;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Food() {
    }

    public Food(LocalDate voteDate, String description, BigDecimal price, Restaurant restaurant) {
        this(null, voteDate, description, price, restaurant);
    }

    public Food(Integer id, LocalDate voteDate, String description, BigDecimal price, Restaurant restaurant) {
        super(id);
        this.voteDate = voteDate;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return voteDate;
    }

    public void setDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", voteDate=" + voteDate +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
