package com.dreamteam.dhome.register.microservice.query.projections;

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
    @Column(length = 50) @Getter @Setter
    private String name;
    @Column(length = 50) @Getter @Setter
    private String lastname;
    @Column(length = 50) @Getter @Setter
    private String age;
    @Column(length = 50) @Getter @Setter
    private String phone;
    @Column(length = 50) @Getter @Setter
    private String dni;
    @Column(length = 50) @Getter @Setter
    private String email;
    @Column(length = 10) @Getter @Setter
    private String password;
    @Column(length = 50) @Getter @Setter
    private String username;
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
