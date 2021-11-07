package com.dreamteam.dhome.register.microservice.command.application.dtos.request;

import lombok.Value;

@Value
public class RegisterCustomerRequest {
    private String name;
    private String lastname;
    private String age;
    private String phone;
    private String dni;
    private String email;
    private String password;
    private String username;
    private String address;
    private boolean verify;
}
