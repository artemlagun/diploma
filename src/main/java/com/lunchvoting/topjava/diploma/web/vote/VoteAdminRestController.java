package com.lunchvoting.topjava.diploma.web.vote;

import com.lunchvoting.topjava.diploma.service.VoteService;
import com.lunchvoting.topjava.diploma.to.VoteTo;
import com.lunchvoting.topjava.diploma.util.VoteUtil;
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

@RestController
@RequestMapping(value = VoteAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoteAdminRestController {

    static final String REST_URL = "/api/admin/votes";

    private final VoteService voteService;

    public VoteAdminRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/{id}")
    public VoteTo get(@PathVariable int id) {
        log.info("get {}", id);
        return VoteUtil.createTo(voteService.get(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        voteService.delete(id);
    }

    @GetMapping
    public List<VoteTo> getAll() {
        log.info("getAll");
        return VoteUtil.getTos(voteService.getAll());
    }

    @GetMapping("/by-user-date")
    public VoteTo getByUserAndDate(@RequestParam int userId,
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   @RequestParam LocalDate voteDate) {
        log.info("getByUserAndDate");
        return VoteUtil.createTo(voteService.getByUserAndDate(userId, voteDate));
    }

    @GetMapping("/by-date")
    public List<VoteTo> getAllByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @RequestParam LocalDate voteDate) {
        log.info("getByUserAndDate");
        return VoteUtil.getTos(voteService.getAllByDate(voteDate));
    }

    @GetMapping("/{restaurantId}/by-date")
    public List<VoteTo> getByRestaurantAndDate(@PathVariable int restaurantId,
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                               @RequestParam LocalDate voteDate) {
        log.info("getByUserAndDate");
        return VoteUtil.getTos(voteService.getByRestaurantAndDate(restaurantId, voteDate));
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteTo> createWithLocation(@RequestParam int userId, @PathVariable int restaurantId) {
        log.info("create vote from user {} for restaurant {}", userId, restaurantId);
        VoteTo created = VoteUtil.createTo(voteService.create(userId, restaurantId));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("/{id}/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("update vote {} for restaurant {}", id, restaurantId);
        voteService.update(id, restaurantId);
    }
}
