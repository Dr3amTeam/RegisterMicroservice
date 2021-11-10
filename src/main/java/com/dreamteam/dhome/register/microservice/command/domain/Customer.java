package com.dreamteam.dhome.register.microservice.command.domain;

import com.dhome.registermicroservice.contracts.commands.RegisterCustomer;
import com.dhome.registermicroservice.contracts.events.CustomerRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.time.Instant;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Customer{
    @AggregateIdentifier
    private String accountId;
    private String name;
    private String lastname;
    private String age;
    private String phone;
    private String dni;
    private String email;
    private String password;
    private String username;
    private String address;
    private boolean verify;
    private BigDecimal balance;
    public Customer() {
    }

    @CommandHandler
    public Customer(RegisterCustomer command){
        Instant now = Instant.now();
        apply(
           new CustomerRegistered(
                   command.getAccountId(),
                   command.getName(),
                   command.getLastname(),
                   command.getAge(),
                   command.getPhone(),
                   command.getDni(),
                   command.getEmail(),
                   command.getPassword(),
                   command.getUsername(),
                   command.getAddress(),
                   command.isVerify(),
                   command.getBalance(),
                   now
           )
        );
    }

    @EventSourcingHandler
    protected void on(CustomerRegistered event){
        this.accountId=event.getAccountId();
        this.name=event.getName();
        this.lastname=event.getLastname();
        this.age=event.getAge();
        this.phone=event.getPhone();
        this.dni=event.getDni();
        this.email=event.getEmail();
        this.username=event.getUsername();
        this.password=event.getPassword();
        this.address=event.getAddress();
        this.verify=event.isVerify();
        this.balance=event.getBalance();
    }
}
