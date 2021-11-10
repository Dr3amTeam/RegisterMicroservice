package com.dreamteam.dhome.register.microservice.command.application.handlers;


import com.dhome.registermicroservice.contracts.events.EmployeeEdited;
import com.dhome.registermicroservice.contracts.events.EmployeeRegistered;
import com.dreamteam.dhome.register.microservice.command.infra.EmployeeDni;
import com.dreamteam.dhome.register.microservice.command.infra.EmployeeDniRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@ProcessingGroup("employeeDni")
public class EmployeeEventHandler {
    private final EmployeeDniRepository employeeDniRepository;

    public EmployeeEventHandler(EmployeeDniRepository employeeDniRepository) {
        this.employeeDniRepository = employeeDniRepository;
    }

    @EventHandler
    public void on(EmployeeRegistered event){
        employeeDniRepository.save(new EmployeeDni(event.getAccountId(),
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
    @EventHandler
    public void on(EmployeeEdited event) {
        Optional<EmployeeDni> EmployeeDniOptional = employeeDniRepository.getDniByEmployeeId(event.getAccountId(), event.getDni());
        EmployeeDniOptional.ifPresent(employeeDniRepository::delete);
        employeeDniRepository.save(new EmployeeDni(
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
                event.isVerify()

        ));
    }
}
