package com.technospark.api.restapi.controller;

import com.technospark.api.restapi.entity.User;
import com.technospark.api.restapi.model.AuthRequestDTO;
import com.technospark.api.restapi.model.LoginRequestDTO;
import com.technospark.api.restapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User createUser(AuthRequestDTO request) {
        User user = new User();
        user.setUserId(request.getUserId());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String login(LoginRequestDTO request) {
        return userService.login(request);
    }
}
