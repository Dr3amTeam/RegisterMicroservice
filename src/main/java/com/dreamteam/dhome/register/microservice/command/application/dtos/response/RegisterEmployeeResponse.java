package com.dreamteam.dhome.register.microservice.command.application.dtos.response;

import com.dhome.registermicroservice.contracts.others.Office;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class RegisterEmployeeResponse {

    @Schema(example = "daniel.jauregui@dhome.com", description = "Email del empleado")
    private String email;

    @Schema(example = "danielj", description = "Nombre de usuario del empleado")
    private String username;

    private Office office;

}
