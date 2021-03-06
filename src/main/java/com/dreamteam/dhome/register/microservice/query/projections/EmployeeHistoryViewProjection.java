package com.dreamteam.dhome.register.microservice.query.projections;


import com.dhome.registermicroservice.contracts.events.*;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@ProcessingGroup("employee")
public class EmployeeHistoryViewProjection {
    private final EmployeeHistoryViewRepository employeeViewRepository;

    public EmployeeHistoryViewProjection(EmployeeHistoryViewRepository employeeViewRepository) {
        this.employeeViewRepository = employeeViewRepository;
    }

    @EventHandler
    public void on(EmployeeRegistered event, @Timestamp Instant timestamp){
        EmployeeHistoryView employeeHistoryView = new EmployeeHistoryView(event.getAccountId(),
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
                                                                        timestamp, event.getOffice().getName(), event.getOffice().getSpeciality());
        employeeViewRepository.save(employeeHistoryView);
    }

    @EventHandler
    public void on(EmployeeEdited event, @Timestamp Instant timestamp){
        Optional<EmployeeHistoryView> employeeHistoryViewOptional = employeeViewRepository.getEmployeeHistoryViewByAccountId(event.getAccountId());
        if (employeeHistoryViewOptional.isPresent()){
            EmployeeHistoryView employeeHistoryView = employeeHistoryViewOptional.get();
            employeeHistoryView = new EmployeeHistoryView(employeeHistoryView);
            employeeHistoryView.setName(event.getName());
            employeeHistoryView.setLastname(event.getLastname());
            employeeHistoryView.setAge(event.getAge());
            employeeHistoryView.setPhone(event.getPhone());
            employeeHistoryView.setDni(event.getDni());
            employeeHistoryView.setEmail(event.getEmail());
            employeeHistoryView.setPassword(event.getPassword());
            employeeHistoryView.setUsername(event.getUsername());
            employeeHistoryView.setAddress(event.getAddress());
            employeeHistoryView.setVerify(event.isVerify());
            employeeHistoryView.setBalance(event.getBalance());
            employeeViewRepository.save(employeeHistoryView);
        }
    }

    @EventHandler
    public void on(EmployeeAccount event){
        Optional<EmployeeHistoryView> optionalEmployeeHistoryView = employeeViewRepository.getEmployeeHistoryViewByAccountId(event.getEmployeeId());
        if (optionalEmployeeHistoryView.isPresent()){
            EmployeeHistoryView employeeView = optionalEmployeeHistoryView.get();
            employeeView.setBalance(employeeView.getBalance().add(event.getAmount()));
            employeeViewRepository.save(employeeView);
        }
    }
    @EventHandler
    public void on(ToEmployeeAccount event){
        Optional<EmployeeHistoryView> optionalEmployeeHistoryView = employeeViewRepository.getEmployeeHistoryViewByAccountId(event.getEmployeeId());
        if (optionalEmployeeHistoryView.isPresent()){
            EmployeeHistoryView employeeView = optionalEmployeeHistoryView.get();
            employeeView.setBalance(employeeView.getBalance().add(event.getAmount()));
            employeeViewRepository.save(employeeView);
        }
    }

    @EventHandler
    public void on(PostValidated event){
        Optional<EmployeeHistoryView> optionalEmployeeHistoryView = employeeViewRepository.getEmployeeHistoryViewByAccountId(event.getEmployeeId());
        if (optionalEmployeeHistoryView.isPresent()){
            EmployeeHistoryView employeeView = optionalEmployeeHistoryView.get();
            employeeViewRepository.save(employeeView);
        }
    }
}
