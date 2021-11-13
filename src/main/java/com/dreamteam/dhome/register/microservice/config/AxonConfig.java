package com.dreamteam.dhome.register.microservice.config;

import
com.dreamteam.dhome.register.microservice.command.domain.Customer;
import com.dreamteam.dhome.register.microservice.command.domain.Employee;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AxonConfig {
    @Bean
    public Repository<Customer> eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Customer.class)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Repository<Employee> eventSourcingRepository2(EventStore eventStore){
        return EventSourcingRepository.builder(Employee.class)
                .eventStore(eventStore)
                .build();
    }
}
