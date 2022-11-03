package com.lunchvoting.topjava.diploma.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends AbstractBaseEntity {

    private LocalDate date;
    private String description;
    private BigDecimal price;
    private Restaurant restaurant;

    public Food() {
    }

    public Food(LocalDate date, String description, BigDecimal price) {
        this(null, date, description, price);
    }

    public Food(Integer id, LocalDate date, String description, BigDecimal price) {
        super(id);
        this.date = date;
        this.description = description;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
