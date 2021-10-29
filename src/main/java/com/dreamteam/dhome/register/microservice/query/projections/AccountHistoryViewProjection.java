package com.dreamteam.dhome.register.microservice.query.projections;

import com.dhome.registermicroservice.contracts.events.AccountRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@ProcessingGroup("customer")
@Component
public class AccountHistoryViewProjection {
    private final AccountHistoryViewRepository accountHistoryViewRepository;

    public AccountHistoryViewProjection(AccountHistoryViewRepository accountHistoryViewRepository) {
        this.accountHistoryViewRepository = accountHistoryViewRepository;
    }

    @EventHandler
    public void on(AccountRegistered event, @Timestamp Instant timestamp){
        AccountHistoryView accountHistoryView = new AccountHistoryView(event.getAccountId(),event.getUsername(),event.getPassword(),event.isVerify(),event.getOccurredOn());
        accountHistoryViewRepository.save(accountHistoryView);
    }
}
