package com.dreamteam.dhome.register.microservice.command.application.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class EditCustomerRequest {

    @Schema(example = "1", description = "ID del cliente")
    private @Setter @Getter String accountId;

    @Schema(example = "Alonso", description = "Nombre del cliente")
    private @Getter String name;

    @Schema(example = "Yuen", description = "Apellido del cliente")
    private @Getter String lastname;

    @Schema(example = "21", description = "Edad del cliente")
    private @Getter String age;

    @Schema(example = "987123000", description = "Celular del cliente")
    private @Getter String phone;

    @Schema(example = "09812389", description = "DNI del cliente")
    private @Getter String dni;

    @Schema(example = "alonso.yuen@dhome.com", description = "Email del cliente")
    private @Getter String email;

    @Schema(example = "dr3amt3am", description = "Contraseña del cliente")
    private @Getter String password;

    @Schema(example = "alonso2021", description = "Nombre de usuario del cliente")
    private @Getter String username;

    @Schema(example = "Calle Los Pelicanos 420", description = "Dirección del cliente")
    private @Getter String address;

    @Schema(example = "false", description = "Estado de verificación de la cuenta")
    private @Getter boolean verify;

    @Schema(example = "100", description = "Monto disponible en la billetera del cliente")
    private @Getter BigDecimal balance;

}
