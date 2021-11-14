package com.dreamteam.dhome.register.microservice.command.application.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class RegisterCustomerResponse {

    @Schema(example = "alonso.yuen@dhome.com", description = "Email del cliente")
    private String email;

    @Schema(example = "alonso2021", description = "Nombre de usuario del cliente")
    private String username;

}
