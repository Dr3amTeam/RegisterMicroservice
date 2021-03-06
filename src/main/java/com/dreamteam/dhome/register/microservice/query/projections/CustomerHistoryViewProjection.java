package com.dreamteam.dhome.register.microservice.query.projections;


import com.dhome.registermicroservice.contracts.events.CustomerAccount;
import com.dhome.registermicroservice.contracts.events.CustomerEdited;
import com.dhome.registermicroservice.contracts.events.CustomerRegistered;
import com.dhome.registermicroservice.contracts.events.FromCustomerAccount;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

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
                event.getBalance(), event.getOccurredOn(),
                                                                        timestamp);
        accountHistoryViewRepository.save(accountHistoryView);
    }

    @EventHandler
    public void on(CustomerAccount event){
        Optional<CustomerHistoryView> optionalCustomerHistoryView = accountHistoryViewRepository.getCustomerHistoryViewByAccountId(event.getCustomerId());
        if (optionalCustomerHistoryView.isPresent()){
            CustomerHistoryView customerHistoryView = optionalCustomerHistoryView.get();
            customerHistoryView.setBalance(customerHistoryView.getBalance().subtract(event.getAmount()));
            accountHistoryViewRepository.save(customerHistoryView);
        }
    }

    @EventHandler
    public void on(CustomerEdited event){
        Optional<CustomerHistoryView> customerHistoryViewOptional = accountHistoryViewRepository.getCustomerHistoryViewByAccountId(event.getAccountId());
        if (customerHistoryViewOptional.isPresent()){
            CustomerHistoryView customerHistoryView = customerHistoryViewOptional.get();
            customerHistoryView.setName(event.getName());
            customerHistoryView.setLastname(event.getLastname());
            customerHistoryView.setAge(event.getAge());
            customerHistoryView.setPhone(event.getPhone());
            customerHistoryView.setDni(event.getDni());
            customerHistoryView.setEmail(event.getEmail());
            customerHistoryView.setPassword(event.getPassword());
            customerHistoryView.setUsername(event.getUsername());
            customerHistoryView.setAddress(event.getAddress());
            customerHistoryView.setVerify(event.isVerify());
            customerHistoryView.setBalance(event.getBalance());
            accountHistoryViewRepository.save(customerHistoryView);
        }
    }
    @EventHandler
    public void on(FromCustomerAccount event){
        Optional<CustomerHistoryView> optionalCustomerHistoryView = accountHistoryViewRepository.getCustomerHistoryViewByAccountId(event.getCustomerId());
        if (optionalCustomerHistoryView.isPresent()){
            CustomerHistoryView customerHistoryView = optionalCustomerHistoryView.get();
            customerHistoryView.setBalance(customerHistoryView.getBalance().subtract(event.getAmount()));
            accountHistoryViewRepository.save(customerHistoryView);
        }
    }
}
