package com.dreamteam.dhome.register.microservice.command.application.dtos.request;

import com.dhome.registermicroservice.contracts.others.Office;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class RegisterEmployeeRequest {

    @Schema(example = "Daniel", description = "Nombre del empleado")
    private String name;

    @Schema(example = "Jauregui", description = "Apellido del empleado")
    private String lastname;

    @Schema(example = "21", description = "Edad del empleado")
    private String age;

    @Schema(example = "987123000", description = "Celular del empleado")
    private String phone;

    @Schema(example = "09812389", description = "DNI del empleado")
    private String dni;

    @Schema(example = "daniel.jauregui@dhome.com", description = "Email del empleado")
    private String email;

    @Schema(example = "dhome21", description = "Contraseña del empleado")
    private String password;

    @Schema(example = "danielj", description = "Nombre de usuario del empleado")
    private String username;

    @Schema(example = "Calle Los Manzanos 170", description = "Dirección del empleado")
    private String address;

    @Schema(example = "false", description = "Estado de verificación de la cuenta")
    private boolean verify;

    @Schema(example = "200", description = "Monto disponible en la billetera del empleado")
    private BigDecimal balance;

    private Office office;

}
