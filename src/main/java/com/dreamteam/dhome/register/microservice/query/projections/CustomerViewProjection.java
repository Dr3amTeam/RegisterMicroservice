package com.dreamteam.dhome.register.microservice.query.projections;


import com.dhome.registermicroservice.contracts.events.CustomerRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@ProcessingGroup("customer")
public class CustomerViewProjection {
    private final CustomerViewRepository customerViewRepository;

    public CustomerViewProjection(CustomerViewRepository customerViewRepository) {
        this.customerViewRepository = customerViewRepository;
    }

    @EventHandler
    public void on(CustomerRegistered event, @Timestamp Instant timestamp){
        CustomerView customerView = new CustomerView(event.getAccountId(),
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
                                                    event.getOccurredOn(),
                                                    timestamp);
        customerViewRepository.save(customerView);
    }

}
