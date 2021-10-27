package com.dreamteam.dhome.register.microservice.command.infra;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Customer {

    private String accountId;
    private String name;
    private String lastname;
    private String age;
    private Long phone;
    @Id
    private String DNI;
    private String email;
    private String password;
    private String username;
    private String address;

    public Customer() {
    }

    public Customer(String accountId, String name, String lastname, String age, Long phone, String DNI, String email, String password, String username, String address) {
        this.accountId = accountId;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.phone = phone;
        this.DNI = DNI;
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
    }
}
