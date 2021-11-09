package com.dreamteam.dhome.register.microservice.command.application.handlers;

import com.dhome.registermicroservice.contracts.events.AccountRegistered;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterAccountRequest;
import com.dreamteam.dhome.register.microservice.command.infra.Customer;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;


@Component
@ProcessingGroup("customer")
public class AccountEventHandler {
    private final CustomerRepository customerRepository;
    private RegisterAccountRequest registerAccountRequest;

    public AccountEventHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @EventHandler
    public void on(AccountRegistered event) {
        customerRepository.save(new Customer(event.getAccountId(), registerAccountRequest.getName(), registerAccountRequest.getLastname(), registerAccountRequest.getAge(),
                registerAccountRequest.getPhone(), registerAccountRequest.getDNI(), registerAccountRequest.getEmail(), event.getPassword(), event.getUsername(), registerAccountRequest.getAddress()));
    }
}
