package com.lunchvoting.topjava.diploma;

import com.lunchvoting.topjava.diploma.model.User;
import com.lunchvoting.topjava.diploma.to.UserTo;
import com.lunchvoting.topjava.diploma.util.UserUtil;

import java.io.Serial;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    @Serial
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userTo = UserUtil.createTo(user);
    }

    public int getId() {
        return userTo.id();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}