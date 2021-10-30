package com.dreamteam.dhome.register.microservice.command.application.dtos.request;

import lombok.Value;

@Value
public class RegisterAccountRequest {
    private String username;
    private String password;
    private boolean verify;
    private String name;
    private String lastname;
    private String age;
    private Long phone;
    private String dni;
    private String email;
    private String address;
}
