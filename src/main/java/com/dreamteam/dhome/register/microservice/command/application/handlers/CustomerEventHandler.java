package com.dreamteam.dhome.register.microservice.command.application.handlers;


import com.dhome.registermicroservice.contracts.events.CustomerEdited;
import com.dhome.registermicroservice.contracts.events.CustomerRegistered;

import com.dhome.registermicroservice.contracts.events.EmployeeEdited;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDni;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDniRepository;
import com.dreamteam.dhome.register.microservice.command.infra.EmployeeDni;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@ProcessingGroup("customer")
public class CustomerEventHandler {
    private final CustomerDniRepository customerDniRepository;

    public CustomerEventHandler(CustomerDniRepository customerDniRepository) {
        this.customerDniRepository = customerDniRepository;
    }

    @EventHandler
    public void on(CustomerRegistered event){
        customerDniRepository.save(new CustomerDni(event.getAccountId(),
                                                    event.getName(),
                                                    event.getLastname(),
                                                    event.getAge(),
                                                    event.getPhone(),
                                                    event.getDni(),
                                                    event.getEmail(),
                                                    event.getPassword(),
                                                    event.getUsername(),
                                                    event.getAddress(),
                                                    event.isVerify(), event.getBalance()));
    }
    @EventHandler
    public void on(CustomerEdited event) {
        Optional<CustomerDni> CustomerDniOptional = customerDniRepository.getCustomerDniByAccountId(event.getAccountId());
        CustomerDniOptional.ifPresent(customerDniRepository::delete);
        customerDniRepository.save(new CustomerDni(
                event.getAccountId(),
                event.getName(),
                event.getLastname(),
                event.getAge(),
                event.getPhone(),
                event.getDni(),
                event.getEmail(),
                event.getPassword(),
                event.getUsername(),
                event.getAddress(),
                event.isVerify(),
                event.getBalance()
        ));
    }
}
