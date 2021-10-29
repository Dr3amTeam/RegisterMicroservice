package com.dreamteam.dhome.register.microservice.query.projections;

import com.dhome.registermicroservice.contracts.events.AccountRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@ProcessingGroup("customer")
public class AccountViewProjection {
    private final AccountViewRepository accountViewRepository;

    public AccountViewProjection(AccountViewRepository accountViewRepository) {
        this.accountViewRepository = accountViewRepository;
    }

    @EventHandler
    public void on(AccountRegistered event, @Timestamp Instant timestamp){
        AccountView accountView = new AccountView(event.getAccountId(),event.getUsername(),event.getPassword(),event.isVerify(),event.getOccurredOn());
        accountViewRepository.save(accountView);
    }

}
