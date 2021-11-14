package com.dreamteam.dhome.register.microservice.command.application.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class EditEmployeeRequest {

    @Schema(example = "1", description = "ID del empleado")
    private @Setter @Getter String accountId;

    @Schema(example = "Daniel", description = "Nombre del empleado")
    private @Getter String name;

    @Schema(example = "Jauregui", description = "Apellido del empleado")
    private @Getter String lastname;

    @Schema(example = "21", description = "Edad del empleado")
    private @Getter String age;

    @Schema(example = "987123000", description = "Celular del empleado")
    private @Getter String phone;

    @Schema(example = "09812389", description = "DNI del empleado")
    private @Getter String dni;

    @Schema(example = "daniel.jauregui@dhome.com", description = "Email del empleado")
    private @Getter String email;

    @Schema(example = "dhome21", description = "Contraseña del empleado")
    private @Getter String password;

    @Schema(example = "danielj", description = "Nombre de usuario del empleado")
    private @Getter String username;

    @Schema(example = "Calle Los Manzanos 170", description = "Dirección del empleado")
    private @Getter String address;

    @Schema(example = "false", description = "Estado de verificación de la cuenta")
    private @Getter boolean verify;

    @Schema(example = "200", description = "Monto disponible en la billetera del empleado")
    private @Getter BigDecimal balance;

}
