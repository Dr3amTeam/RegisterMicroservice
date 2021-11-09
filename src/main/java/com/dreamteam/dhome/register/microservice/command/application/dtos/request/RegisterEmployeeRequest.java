package com.dreamteam.dhome.register.microservice.command.application.dtos.request;

import com.dhome.registermicroservice.contracts.others.Office;
import lombok.Value;

@Value
public class RegisterEmployeeRequest {
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
    private Office office;
}
