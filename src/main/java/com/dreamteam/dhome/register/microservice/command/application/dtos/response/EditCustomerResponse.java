package com.dreamteam.dhome.register.microservice.command.application.dtos.response;

import lombok.Value;

@Value
public class EditCustomerResponse {
    private String accountId;
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
