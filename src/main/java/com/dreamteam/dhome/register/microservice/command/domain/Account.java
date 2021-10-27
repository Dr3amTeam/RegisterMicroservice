package com.dreamteam.dhome.register.microservice.command.domain;

import com.dhome.registermicroservice.contracts.commands.RegisterAccount;
import com.dhome.registermicroservice.contracts.events.AccountRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;


import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Account {
    @AggregateIdentifier
    private String accountId;
    private String username;
    private String password;
    private boolean verify;

    public Account() {
    }

    @CommandHandler
    public Account(RegisterAccount registerAccount){
        Instant now = Instant.now();
        apply(
           new AccountRegistered(
                   registerAccount.getAccountId(),
                   registerAccount.getUsername(),
                   registerAccount.getPassword(),
                   registerAccount.isVerify(),
                   now
           )
        );
    }

    @EventSourcingHandler
    protected void on(AccountRegistered event){
        this.accountId=event.getAccountId();
        this.username=event.getUsername();
        this.password=event.getPassword();
        this.verify=event.isVerify();
    }
}
