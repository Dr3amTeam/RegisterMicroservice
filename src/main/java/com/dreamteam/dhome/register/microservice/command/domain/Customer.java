package com.dreamteam.dhome.register.microservice.command.domain;

import com.dhome.registermicroservice.contracts.commands.AccountFromCustomer;
import com.dhome.registermicroservice.contracts.commands.EditCustomer;
import com.dhome.registermicroservice.contracts.commands.EditEmployee;
import com.dhome.registermicroservice.contracts.commands.RegisterCustomer;
import com.dhome.registermicroservice.contracts.events.CustomerEdited;
import com.dhome.registermicroservice.contracts.events.CustomerRegistered;
import com.dhome.registermicroservice.contracts.events.FromAccountNotFound;
import com.dhome.registermicroservice.contracts.events.FromCustomerAccount;
import com.dhome.registermicroservice.contracts.events.EmployeeEdited;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateNotFoundException;
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

    @CommandHandler
    public void accountFromCustomer(AccountFromCustomer accountFromCustomer) throws Exception{
        Instant now = Instant.now();
        try{
            apply(new FromCustomerAccount(accountId,accountFromCustomer.getPaymentId(),accountFromCustomer.getAmount(),now));
        }catch (AggregateNotFoundException ex){
            apply(new FromAccountNotFound(accountFromCustomer.getCustomerId(),accountFromCustomer.getPaymentId(),now));
            throw ex;
        } catch (Exception ex){
            throw ex;
        }
    }

    @CommandHandler
    public void handle(EditCustomer command) {
        Instant now = Instant.now();
        apply(
                new CustomerEdited(
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
    protected void on(CustomerEdited event) {
        accountId = event.getAccountId();
        name = event.getName();
        lastname = event.getLastname();
        age = event.getAge();
        phone = event.getPhone();
        dni = event.getDni();
        email = event.getEmail();
        password = event.getPassword();
        username = event.getUsername();
        verify = event.isVerify();
        balance = event.getBalance();
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

    @EventSourcingHandler
    public void on(FromCustomerAccount event){
        balance = balance.subtract(event.getAmount());
    }


}
