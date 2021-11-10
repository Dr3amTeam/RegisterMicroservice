package com.dreamteam.dhome.register.microservice.query.projections;


import com.dhome.registermicroservice.contracts.events.CustomerAccount;
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
                event.getBalance(), event.getOccurredOn(),
                                                    timestamp);
        customerViewRepository.save(customerView);
    }

    @EventHandler
    public void on(CustomerAccount event){
        Optional<CustomerView> optionalCustomerView = customerViewRepository.findById(event.getCustomerId());
        if (optionalCustomerView.isPresent()){
            CustomerView customerView = optionalCustomerView.get();
            customerView.setBalance(customerView.getBalance().subtract(event.getAmount()));
            customerViewRepository.save(customerView);
        }
    }
    @EventHandler
    public void on(FromCustomerAccount event){
        Optional<CustomerView> optionalCustomerView = customerViewRepository.findById(event.getCustomerId());
        if (optionalCustomerView.isPresent()){
            CustomerView customerView = optionalCustomerView.get();
            customerView.setBalance(customerView.getBalance().subtract(event.getAmount()));
            customerViewRepository.save(customerView);
        }
    }
}
