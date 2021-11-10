package com.dreamteam.dhome.register.microservice.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class EditEmployeeRequest {
    private @Setter @Getter String accountId;
    private @Getter String name;
    private @Getter String lastname;
    private @Getter String age;
    private @Getter String phone;
    private @Getter String dni;
    private @Getter String email;
    private @Getter String password;
    private @Getter String username;
    private @Getter String address;
    private @Getter boolean verify;
    private @Getter
    BigDecimal balance;
}
