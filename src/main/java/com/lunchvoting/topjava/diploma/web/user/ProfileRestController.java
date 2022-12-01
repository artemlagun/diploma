package com.lunchvoting.topjava.diploma.web.user;

import com.lunchvoting.topjava.diploma.model.User;
import com.lunchvoting.topjava.diploma.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.assureIdConsistent;
import static com.lunchvoting.topjava.diploma.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ProfileRestController {

    private final UserService service;

    static final String REST_URL = "/api/profile";

    public ProfileRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public User get() {
        log.info("get {}", authUserId());
        return service.get(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        log.info("delete {}", authUserId());
        service.delete(authUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) {
        log.info("update {} with id={}", user, authUserId());
        assureIdConsistent(user, authUserId());
        service.update(user);
    }
}