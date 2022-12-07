package com.lunchvoting.topjava.diploma.util;

import com.lunchvoting.topjava.diploma.model.User;
import com.lunchvoting.topjava.diploma.to.UserTo;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class UserUtil {

    public static List<UserTo> getTos (Collection<User> users) {
        return users.stream().map(UserUtil::createTo).toList();
    }

    public static UserTo createTo (User user) {
        LocalDate dateConverted = user.getRegistered().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                user.isEnabled(), dateConverted);
    }
}