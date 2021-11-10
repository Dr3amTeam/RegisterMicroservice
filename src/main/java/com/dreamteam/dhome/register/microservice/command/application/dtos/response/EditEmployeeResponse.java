package com.dreamteam.dhome.register.microservice.command.application.dtos.response;

import com.dhome.registermicroservice.contracts.others.Office;
import lombok.Getter;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class EditEmployeeResponse {
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
    private BigDecimal balance;
}
