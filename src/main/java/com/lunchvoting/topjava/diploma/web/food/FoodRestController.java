package com.lunchvoting.topjava.diploma.web.food;

import com.lunchvoting.topjava.diploma.model.Food;
import com.lunchvoting.topjava.diploma.service.FoodService;
import com.lunchvoting.topjava.diploma.to.FoodTo;
import com.lunchvoting.topjava.diploma.util.FoodUtil;
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

    @GetMapping("/{restaurantId}")
    public List<FoodTo> getAllByRestaurant(@PathVariable int restaurantId) {
        log.info("getAll for restaurant {}", restaurantId);
        return FoodUtil.getTos(service.getAllByRestaurant(restaurantId));
    }

    @GetMapping("/{restaurantId}/{id}")
    public FoodTo get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("get food {} for restaurant {}", id, restaurantId);
        return FoodUtil.createTo(service.get(id, restaurantId));
    }

    @DeleteMapping("/{restaurantId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete food {} for restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    @GetMapping("/{restaurantId}/by-date")
    public List<FoodTo> getAllByDate(@PathVariable int restaurantId,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @RequestParam LocalDate voteDate) {
        log.info("getAllByDate {} for restaurant {}", voteDate, restaurantId);
        return FoodUtil.getTos(service.getAllByDate(restaurantId, voteDate));
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodTo> createWithLocation(@RequestBody Food food, @PathVariable int restaurantId) {
        log.info("create food {} for restaurant {}", food, restaurantId);
        checkNew(food);
        FoodTo created = FoodUtil.createTo(service.create(food, restaurantId));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Food food, @PathVariable int restaurantId) {
        log.info("update food {} for restaurant {}", food, restaurantId);
        assureIdConsistent(food, food.id());
        service.update(food, restaurantId);
    }
}
