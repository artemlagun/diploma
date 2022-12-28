package com.lunchvoting.topjava.diploma.web.restaurant;

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
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantRestController {

    static final String REST_URL = "/api/restaurants";

    private final RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/with-menu")
    public List<RestaurantTo> getAllWithMenu() {
        log.info("getAllWithMenu");
        return RestaurantUtil.getTosWithMenu(service.getAllWithMenu());
    }

    @GetMapping("/{id}/menu")
    public RestaurantTo getMenuOfDay(@PathVariable int id) {
        log.info("getMenuOfDay for restaurant {}", id);
        return RestaurantUtil.createToWithMenu(service.getMenuOfDay(id));
    }
}
