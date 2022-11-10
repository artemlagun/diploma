package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.Role;
import com.lunchvoting.topjava.diploma.model.User;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static com.lunchvoting.topjava.diploma.testdata.UserTestData.*;
import static org.junit.Assert.assertThrows;

public class UserServiceTest extends AbstractBaseServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void create() {
        User created = service.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", firstUser.getEmail(), "newPass", Role.USER)));
    }

    @Test
    public void delete() {
        service.delete(FIRST_USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(FIRST_USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        User user = service.get(FIRST_USER_ID);
        USER_MATCHER.assertMatch(user, firstUser);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(FIRST_USER_ID), getUpdated());
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, admin, secondUser, firstUser);
    }
}
