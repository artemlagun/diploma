package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Role;
import com.lunchvoting.topjava.diploma.model.User;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

import static com.lunchvoting.topjava.diploma.testdata.UserTestData.*;
import static org.junit.Assert.assertThrows;

 class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
     void create() {
        User created = service.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
     void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", user1.getEmail(), "newPass", Role.USER)));
    }

    @Test
     void delete() {
        service.delete(USER1_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER1_ID));
    }

    @Test
     void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
     void get() {
        User user = service.get(USER1_ID);
        USER_MATCHER.assertMatch(user, user1);
    }

    @Test
     void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
     void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
     void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER1_ID), getUpdated());
    }

    @Test
     void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, admin, user3, user2, user1);
    }

    @Test
     void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> service.create(
                new User(null, "  ", "newuser@gmail.com", "password", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(
                new User(null, "New User", "  ", "password", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(
                new User(null, "New User", "evahester@gmail.com", "  ", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(
                new User(null, "New User", "evahester@gmail.com", "password",
                        true, null, Set.of())));
    }
}
