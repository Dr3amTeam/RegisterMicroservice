package com.dreamteam.dhome.register.microservice.command.application.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class RegisterCustomerRequest {

    @Schema(example = "Alonso", description = "Nombre del cliente")
    private String name;

    @Schema(example = "Yuen", description = "Apellido del cliente")
    private String lastname;

    @Schema(example = "21", description = "Edad del cliente")
    private String age;

    @Schema(example = "987123000", description = "Celular del cliente")
    private String phone;

    @Schema(example = "09812389", description = "DNI del cliente")
    private String dni;

    @Schema(example = "alonso.yuen@dhome.com", description = "Email del cliente")
    private String email;

    @Schema(example = "dr3amt3am", description = "Contraseña del cliente")
    private String password;

    @Schema(example = "alonso2021", description = "Nombre de usuario del cliente")
    private String username;

    @Schema(example = "Calle Los Pelicanos 420", description = "Dirección del cliente")
    private String address;

    @Schema(example = "false", description = "Estado de verificación de la cuenta")
    private boolean verify;

    @Schema(example = "100", description = "Monto disponible en la billetera del cliente")
    private BigDecimal balance;

}
