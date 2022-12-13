package com.lunchvoting.topjava.diploma.util;

import com.lunchvoting.topjava.diploma.model.Role;
import com.lunchvoting.topjava.diploma.model.User;
import com.lunchvoting.topjava.diploma.to.UserTo;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class UserUtil {

    public static List<UserTo> getTos(Collection<User> users) {
        return users.stream().map(UserUtil::createTo).toList();
    }

    public static UserTo createTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
