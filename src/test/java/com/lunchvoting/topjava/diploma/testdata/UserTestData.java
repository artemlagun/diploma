package com.lunchvoting.topjava.diploma.testdata;

import com.lunchvoting.topjava.diploma.MatcherFactory;
import com.lunchvoting.topjava.diploma.model.Role;
import com.lunchvoting.topjava.diploma.model.User;

import java.util.Collections;
import java.util.Date;

import static com.lunchvoting.topjava.diploma.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("registered", "roles", "votes");

    public static final int FIRST_USER_ID = START_SEQ;
    public static final int SECOND_USER_ID = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ + 2;

    public static final int NOT_FOUND = 10;

    public static final User firstUser = new User(FIRST_USER_ID, "Eva Hester", "evahester@gmail.com", "password", Role.USER);
    public static final User secondUser = new User(SECOND_USER_ID, "Bryan Terry", "bryanterry@gmail.com", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);


    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass",false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(firstUser);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
