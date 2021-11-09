package com.dreamteam.dhome.register.microservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class AccountHistoryView {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long accountHistoryId;
    @Column(length = 36)
    @Getter
    @Setter
    private String accountId;
    @Column(length = 50)
    @Getter
    @Setter
    private String username;
    @Column(length = 10)
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private boolean verify;
    private Instant createdAt;
    @Column(nullable = true)
    @Getter
    @Setter
    private Instant updatedAt;

    public AccountHistoryView(String accountId, String username, String password, boolean verify, Instant occurredOn) {
    }

    public AccountHistoryView(String accountId, String username, String password, boolean verify, Instant createdAt, Instant updatedAt) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.verify = verify;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AccountHistoryView(AccountHistoryView accountHistoryView) {
        this.accountId = accountHistoryView.accountId;
        this.username = accountHistoryView.username;
        this.password = accountHistoryView.password;
        this.verify = accountHistoryView.verify;
        this.createdAt = accountHistoryView.createdAt;
        this.updatedAt = accountHistoryView.updatedAt;
    }
}
