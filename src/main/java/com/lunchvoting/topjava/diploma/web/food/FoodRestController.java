package com.lunchvoting.topjava.diploma.web.food;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.service.FoodService;
import com.lunchvoting.topjava.diploma.to.FoodTo;
import com.lunchvoting.topjava.diploma.util.FoodUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.assureIdConsistent;
import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = FoodRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class FoodRestController {

    static final String REST_URL = "/api/admin/foods";

    private final FoodService service;

    public FoodRestController(FoodService service) {
        this.service = service;
    }

    @GetMapping()
    public List<FoodTo> getAll() {
        log.info("getAll");
        return FoodUtil.getTos(service.getAll());
    }

    @GetMapping("/restaurant")
    public List<FoodTo> getAllByRestaurant(@RequestParam int restaurantId) {
        log.info("getAll for restaurant {}", restaurantId);
        return FoodUtil.getTos(service.getAllByRestaurant(restaurantId));
    }

    @GetMapping("/{id}")
    public FoodTo get(@PathVariable int id) {
        log.info("get food {}", id);
        return FoodUtil.createTo(service.get(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete food {}", id);
        service.delete(id);
    }

    @GetMapping("/by-date")
    public List<FoodTo> getAllByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @RequestParam LocalDate voteDate) {
        log.info("getAllByDate {}", voteDate);
        return FoodUtil.getTos(service.getAllByDate(voteDate));
    }

    @GetMapping("/by-restaurant-date")
    public List<FoodTo> getAllByRestaurantAndDate(@RequestParam int restaurantId,
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  @RequestParam LocalDate voteDate) {
        log.info("getAll from restaurant {} by date {}", restaurantId, voteDate);
        return FoodUtil.getTos(service.getAllByRestaurantAndDate(restaurantId, voteDate));
    }

    @PostMapping(value = "/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodTo> createWithLocation(@Valid @RequestBody Food food, @RequestParam int restaurantId) {
        log.info("create food {} for restaurant {}", food, restaurantId);
        checkNew(food);
        FoodTo created = FoodUtil.createTo(service.create(food, restaurantId));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Food food, @RequestParam int restaurantId) {
        log.info("update food {} for restaurant {}", food, restaurantId);
        assureIdConsistent(food, food.id());
        service.update(food, restaurantId);
    }
}
