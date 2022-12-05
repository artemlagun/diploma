package com.lunchvoting.topjava.diploma.web.restaurant;

import com.lunchvoting.topjava.diploma.model.Restaurant;
import com.lunchvoting.topjava.diploma.service.RestaurantService;
import com.lunchvoting.topjava.diploma.to.RestaurantTo;
import com.lunchvoting.topjava.diploma.util.RestaurantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantProfileRestController {

    static final String REST_URL = "/api/profile/restaurants";

    private final RestaurantService service;

    public RestaurantProfileRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping()
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return RestaurantUtil.getTos(service.getAll());
    }

    @GetMapping("/with-menu")
    public List<Restaurant> getAllWithMenu() {
        log.info("getAllWithMenu");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return RestaurantUtil.createTo(service.get(id));
    }

    @GetMapping("/{id}/menu")
    public Restaurant getMenuOfDay(@PathVariable int id) {
        log.info("getMenuOfDay for restaurant {}", id);
        return service.getMenuOfDay(id);
    }
}
