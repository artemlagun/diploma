package com.lunchvoting.topjava.diploma.web.restaurant;

import com.lunchvoting.topjava.diploma.model.Restaurant;
import com.lunchvoting.topjava.diploma.service.RestaurantService;
import com.lunchvoting.topjava.diploma.to.FoodTo;
import com.lunchvoting.topjava.diploma.to.RestaurantTo;
import com.lunchvoting.topjava.diploma.util.FoodUtil;
import com.lunchvoting.topjava.diploma.util.RestaurantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.assureIdConsistent;
import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNew;


@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantAdminRestController {

    static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantService service;

    public RestaurantAdminRestController(RestaurantService service) {
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    @GetMapping("/{id}/menu")
    public Restaurant getMenuOfDay(@PathVariable int id) {
        log.info("getMenuOfDay for restaurant {}", id);
        return service.getMenuOfDay(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantTo> createWithLocation(@RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        checkNew(restaurant);
        RestaurantTo created = RestaurantUtil.createTo(service.create(restaurant));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant) {
        log.info("update restaurant {}", restaurant);
        assureIdConsistent(restaurant, restaurant.id());
        service.update(restaurant);
    }
}
