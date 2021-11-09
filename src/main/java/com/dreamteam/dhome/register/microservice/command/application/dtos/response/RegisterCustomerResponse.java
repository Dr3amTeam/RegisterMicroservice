package com.dreamteam.dhome.register.microservice.command.application.dtos.response;

import lombok.Value;

@Value
public class RegisterCustomerResponse {
    private String email;
    private String username;
}
