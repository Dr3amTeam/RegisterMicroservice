package com.dreamteam.dhome.register.microservice.command.application.handlers;


import com.dhome.registermicroservice.contracts.events.CustomerRegistered;

import com.dreamteam.dhome.register.microservice.command.infra.CustomerDni;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDniRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;


@Component
@ProcessingGroup("customer")
public class CustomerEventHandler {
    private final CustomerDniRepository customerRepository;

    public CustomerEventHandler(CustomerDniRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @EventHandler
    public void on(CustomerRegistered event){
            customerRepository.save(new CustomerDni(event.getAccountId(),
                                                    event.getName(),
                                                    event.getLastname(),
                                                    event.getAge(),
                                                    event.getPhone(),
                                                    event.getDni(),
                                                    event.getEmail(),
                                                    event.getPassword(),
                                                    event.getUsername(),
                                                    event.getAddress(),
                                                    event.isVerify()));
    }
}
