package com.dreamteam.dhome.register.microservice.query.projections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class CustomerView {

    @Schema(example = "e123d4", description = "Número de cuenta")
    @Id @Column(length = 36) @Getter @Setter
    private String accountId;

    @Schema(example = "Alonso", description = "Nombre")
    @Column(length = 50) @Getter @Setter
    private String name;

    @Schema(example = "Yuen",description = "Apellido")
    @Column(length = 50) @Getter @Setter
    private String lastname;

    @Schema(example = "21",description = "Edad")
    @Column(length = 50) @Getter @Setter
    private String age;

    @Schema(example = "999000123", description = "Teléfono")
    @Column(length = 50) @Getter @Setter
    private String phone;

    @Schema(example = "09812390", description = "DNI")
    @Column(length = 50) @Getter @Setter
    private String dni;

    @Schema(example = "alonso@dhome.com",description = "Correo electrónico")
    @Column(length = 50) @Getter @Setter
    private String email;

    @Schema(example = "alonso2021", description = "Password")
    @Column(length = 10) @Getter @Setter
    private String password;

    @Schema(example = "alonso.yuen",description = "Nombre de usuario")
    @Column(length = 50) @Getter @Setter
    private String username;

    @Schema(example = "Calle Los Bastardos 420",description = "Dirección")
    @Column(length = 50) @Getter @Setter
    private String address;

    @Getter @Setter
    @Schema(example = "true",description = "Verifica la cuenta si es válida")
    private boolean verify;

    @Schema(example = "5000",description = "Balance")
    @Getter @Setter
    private BigDecimal balance;

    @Schema(example = "19/11/2021",description = "Fecha de creación")
    @Column(nullable = true) @Getter @Setter
    private Instant createdAt;

    @Schema(example = "21/11/2021",description = "Fecha de actualización")
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public CustomerView() {
    }

    public CustomerView(String accountId, String name, String lastname, String age, String phone, String dni, String email, String password, String username, String address, boolean verify, BigDecimal balance, Instant createdAt, Instant updatedAt) {
        this.accountId = accountId;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.phone = phone;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.verify = verify;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
