package com.technospark.api.restapi.service;


import com.technospark.api.restapi.entity.User;
import com.technospark.api.restapi.model.LoginRequestDTO;

public interface UserService {


    User createUser(User user);

    String login(LoginRequestDTO request);

    String validateAndGenrateNewToken(String token) throws Exception;
}
