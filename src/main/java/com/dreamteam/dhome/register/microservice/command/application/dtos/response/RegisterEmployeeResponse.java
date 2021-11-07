package com.dreamteam.dhome.register.microservice.command.application.dtos.response;

import com.dhome.registermicroservice.contracts.others.Office;
import lombok.Value;

@Value
public class RegisterEmployeeResponse {
    private String email;
    private String username;
    private Office office;
}
