package com.dreamteam.dhome.register.microservice.command.application.dtos.response;

import lombok.Value;

@Value
public class RegisterAccountResponse {
    private String accountId;
    private String username;
    private String password;
    private boolean verify;
}
