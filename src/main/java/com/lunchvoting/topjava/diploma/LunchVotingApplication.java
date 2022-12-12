package com.lunchvoting.topjava.diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring/spring-db.xml",
                             "classpath:spring/spring-security.xml"})
public class LunchVotingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LunchVotingApplication.class);
    }
}
