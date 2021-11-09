package com.dreamteam.dhome.register.microservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class EmployeeHistoryView {
    @Id @GeneratedValue @Getter @Setter
    private Long accountHistoryId;
    @Column(length = 36) @Getter @Setter
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
    private Instant createdAt;
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;
    private String Office;
    private String speciality;
    public EmployeeHistoryView() {
    }

    public EmployeeHistoryView(String accountId, String name, String lastname, String age, String phone, String dni, String email, String password, String username, String address, boolean verify, Instant createdAt, Instant updatedAt, String office, String speciality) {
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        Office = office;
        this.speciality = speciality;
    }

    public EmployeeHistoryView(EmployeeHistoryView employeeHistoryView) {
        this.accountId = employeeHistoryView.accountId;
        this.name = employeeHistoryView.name;
        this.lastname = employeeHistoryView.lastname;
        this.age = employeeHistoryView.age;
        this.phone = employeeHistoryView.phone;
        this.dni = employeeHistoryView.dni;
        this.email = employeeHistoryView.email;
        this.password = employeeHistoryView.password;
        this.username = employeeHistoryView.username;
        this.address = employeeHistoryView.address;
        this.verify = employeeHistoryView.verify;
        this.createdAt = employeeHistoryView.createdAt;
        this.updatedAt = employeeHistoryView.updatedAt;
        this.Office=employeeHistoryView.Office;
        this.speciality = employeeHistoryView.speciality;
    }
}
