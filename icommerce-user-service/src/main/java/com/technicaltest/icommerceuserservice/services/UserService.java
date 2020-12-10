package com.technicaltest.icommerceuserservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userservice")
@RequiredArgsConstructor
public class UserService {

    @GetMapping("/welcome")
    public String getAllUsers() {
        return "Welcome to User service";
    }
}
