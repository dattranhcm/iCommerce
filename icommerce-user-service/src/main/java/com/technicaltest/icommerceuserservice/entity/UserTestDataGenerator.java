package com.technicaltest.icommerceuserservice.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class UserTestDataGenerator {
    private final UserRepository userRepository;

    @Autowired
    public UserTestDataGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void generateTestData() {
        userRepository
                .save(new TUser("Dat Tran", "0393974369", "Nguyen Van Linh Stress", "hoangdat@gmail.com", new Date(), new Date()));
    }
}
