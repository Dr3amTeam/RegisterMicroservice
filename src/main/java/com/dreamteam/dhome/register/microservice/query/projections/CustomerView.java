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

    @Id @Column(length = 36) @Getter @Setter
    private String accountId;

    @Schema(example = "Alonso")
    @Column(length = 50) @Getter @Setter
    private String name;

    @Schema(defaultValue = "Yuen")
    @Column(length = 50) @Getter @Setter
    private String lastname;

    @Schema(defaultValue = "21")
    @Column(length = 50) @Getter @Setter
    private String age;

    @Schema(defaultValue = "999000123")
    @Column(length = 50) @Getter @Setter
    private String phone;

    @Schema(defaultValue = "09812390")
    @Column(length = 50) @Getter @Setter
    private String dni;

    @Schema(defaultValue = "alonso@dhome.com")
    @Column(length = 50) @Getter @Setter
    private String email;

    @Schema(defaultValue = "aea2021")
    @Column(length = 10) @Getter @Setter
    private String password;

    @Schema(defaultValue = "alonso.yuen")
    @Column(length = 50) @Getter @Setter
    private String username;

    @Schema(defaultValue = "Calle Los Bastardos 420")
    @Column(length = 50) @Getter @Setter
    private String address;

    @Getter @Setter
    private boolean verify;

    @Getter @Setter
    private BigDecimal balance;

    private Instant createdAt;
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
