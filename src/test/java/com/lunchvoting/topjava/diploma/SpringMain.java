package com.lunchvoting.topjava.diploma;

import com.lunchvoting.topjava.diploma.model.Restaurant;
import com.lunchvoting.topjava.diploma.model.Role;
import com.lunchvoting.topjava.diploma.model.User;
import com.lunchvoting.topjava.diploma.model.Vote;
import com.lunchvoting.topjava.diploma.service.RestaurantService;
import com.lunchvoting.topjava.diploma.service.UserService;
import com.lunchvoting.topjava.diploma.service.VoteService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {

            VoteService voteService = appCtx.getBean(VoteService.class);
            UserService userService = appCtx.getBean(UserService.class);
            RestaurantService restaurantService = appCtx.getBean(RestaurantService.class);

            User user1 = userService.create(new User(null,"Test Name1", "test1@gmail", "password", Role.USER));
            User user2 = userService.create(new User(null,"Test Name2", "test2@gmail", "password", Role.USER));
            Restaurant restaurant1 = restaurantService.create(new Restaurant(null, "New Restaurant1"));
            Restaurant restaurant2 = restaurantService.create(new Restaurant(null, "New Restaurant2"));
            Vote vote1 = voteService.create(new Vote(LocalDate.now(), user1, restaurant1), user1.id(), restaurant1.id());
            Vote vote2 = voteService.create(new Vote(LocalDate.now(), user2, restaurant1), user2.id(), restaurant1.id());

            voteService.delete(vote2.id());

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            System.out.println();
        }
    }
}
