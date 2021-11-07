package com.dreamteam.dhome.register.microservice.query.projections;


import com.dhome.registermicroservice.contracts.events.EmployeeRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@ProcessingGroup("employee")
public class EmployeeViewProjection {
    private final EmployeeViewRepository employeeViewRepository;

    public EmployeeViewProjection(EmployeeViewRepository employeeViewRepository) {
        this.employeeViewRepository = employeeViewRepository;
    }

    @EventHandler
    public void on(EmployeeRegistered event, @Timestamp Instant timestamp){
        EmployeeView employeeView = new EmployeeView(event.getAccountId(),
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
                                                    timestamp,
                                                    event.getOffice().getName(), event.getOffice().getSpeciality());
        employeeViewRepository.save(employeeView);
    }

}
