package com.dreamteam.dhome.register.microservice.command.infra;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class EmployeeDni {
    @Id
    private String accountId;
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    private String age;
    @NotNull
    private String phone;
    @NotNull
    private String dni;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String username;
    @NotNull
    private String address;
    private boolean verify;

    public EmployeeDni() {
    }

    public EmployeeDni(String accountId, String name, String lastname, String age, String phone, String dni, String email, String password, String username, String address, boolean verify) {
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
    }
}
