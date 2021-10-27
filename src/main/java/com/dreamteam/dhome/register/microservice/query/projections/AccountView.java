package com.dreamteam.dhome.register.microservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class AccountView {
    @Id @Column(length = 36) @Getter @Setter
    private String accountId;
    @Column(length = 50) @Getter @Setter
    private String username;
    @Column(length = 10) @Getter @Setter
    private String password;
    @Getter @Setter
    private boolean verify;
    private Instant createdAt;
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public AccountView(String accountId, String username, String password, boolean verify, Instant occurredOn) {
    }

    public AccountView(String accountId, String username, String password, boolean verify, Instant createdAt, Instant updatedAt) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.verify = verify;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
