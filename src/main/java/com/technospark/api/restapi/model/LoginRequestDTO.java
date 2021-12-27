package com.technospark.api.restapi.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    private String userId;
    private String password;
}
