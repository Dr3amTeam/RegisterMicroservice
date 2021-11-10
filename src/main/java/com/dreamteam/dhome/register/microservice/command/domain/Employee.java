package com.dreamteam.dhome.register.microservice.command.domain;

import com.dhome.registermicroservice.contracts.commands.AccountToEmployee;
import com.dhome.registermicroservice.contracts.commands.EditEmployee;
import com.dhome.registermicroservice.contracts.commands.RegisterEmployee;
import com.dhome.registermicroservice.contracts.events.EmployeeEdited;
import com.dhome.registermicroservice.contracts.events.EmployeeRegistered;
import com.dhome.registermicroservice.contracts.events.ToAccountNotFound;
import com.dhome.registermicroservice.contracts.events.ToEmployeeAccount;
import com.dhome.registermicroservice.contracts.others.Office;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Embedded;
import java.math.BigDecimal;
import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Employee {
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
    @Embedded
    private Office office;


    public Employee() {
    }

    @CommandHandler
    public Employee(RegisterEmployee command){
        Instant now = Instant.now();
        apply(
                new EmployeeRegistered(
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
                        now,
                        command.getOffice()
                )
        );
    }

    @CommandHandler
    public void accountToEmployee(AccountToEmployee accountToEmployee) throws Exception{
        Instant now = Instant.now();
        try{
            apply(new ToEmployeeAccount(accountToEmployee.getEmployeeId(),accountToEmployee.getPaymentId(),accountToEmployee.getAmount(),now));
        }catch (AggregateNotFoundException ex){
            apply(new ToAccountNotFound(accountToEmployee.getEmployeeId(),accountToEmployee.getPaymentId(),now));
            throw ex;
        }catch (Exception ex){
            throw ex;
        }
    }

    @EventSourcingHandler
    protected void on(EmployeeRegistered event){
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
        this.office=event.getOffice();
        this.balance=event.getBalance();
    }

    @EventSourcingHandler
    public void on(ToEmployeeAccount event){
        balance = balance.add(event.getAmount());
    }

    @CommandHandler
    public void handle(EditEmployee command) {
        Instant now = Instant.now();
        apply(
                new EmployeeEdited(
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
    protected void on(EmployeeEdited event) {
        name = event.getName();
        lastname = event.getLastname();
        age = event.getAge();
        phone = event.getPhone();
        dni = event.getDni();
        email = event.getEmail();
        password = event.getPassword();
        username = event.getUsername();
        verify = event.isVerify();
    }
}
