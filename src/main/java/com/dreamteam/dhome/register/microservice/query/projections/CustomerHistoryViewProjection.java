package com.dreamteam.dhome.register.microservice.query.projections;


import com.dhome.registermicroservice.contracts.events.CustomerRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@ProcessingGroup("customer")
public class CustomerHistoryViewProjection {
    private final CustomerHistoryViewRepository accountHistoryViewRepository;

    public CustomerHistoryViewProjection(CustomerHistoryViewRepository accountHistoryViewRepository) {
        this.accountHistoryViewRepository = accountHistoryViewRepository;
    }

    @EventHandler
    public void on(CustomerRegistered event, @Timestamp Instant timestamp){
        CustomerHistoryView accountHistoryView = new CustomerHistoryView(event.getAccountId(),
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
        accountHistoryViewRepository.save(accountHistoryView);
    }
}
