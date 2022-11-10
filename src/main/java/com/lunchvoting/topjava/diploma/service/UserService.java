package com.lunchvoting.topjava.diploma.service;

import com.lunchvoting.topjava.diploma.model.User;
import com.lunchvoting.topjava.diploma.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNotFound;
import static com.lunchvoting.topjava.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email shouldn't be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    public User create(User user) {
        Assert.notNull(user, "user shouldn't be null");
        return repository.save(user);
    }

    public void update(User user) {
        Assert.notNull(user, "user shouldn't be null");
        checkNotFoundWithId(repository.save(user), user.id());
    }
}
