package com.dreamteam.dhome.register.microservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterCustomerRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterEmployeeRequest;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDni;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDniRepository;
import com.dreamteam.dhome.register.microservice.command.infra.EmployeeDni;
import com.dreamteam.dhome.register.microservice.command.infra.EmployeeDniRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegisterEmployeeValidator {
    private final EmployeeDniRepository employeeDniRepository;
    public RegisterEmployeeValidator(EmployeeDniRepository employeeDniRepository) {
        this.employeeDniRepository = employeeDniRepository;
    }
    public Notification validate(RegisterEmployeeRequest registerEmployeeRequest){
        Notification notification = new Notification();
        String username = registerEmployeeRequest.getUsername().trim();
        if (username.isEmpty()){
            notification.addError("Employee username is required");
        }
        String lastname = registerEmployeeRequest.getLastname().trim();
        if (lastname.isEmpty()){
            notification.addError("Employee lastname is required");
        }
        String name = registerEmployeeRequest.getName().trim();
        if (name.isEmpty()){
            notification.addError("Employee name is required");
        }
        String password = registerEmployeeRequest.getPassword().trim();
        if (password.isEmpty()){
            notification.addError("Employee password is required");
        }
        String email = registerEmployeeRequest.getEmail().trim();
        if (email.isEmpty()){
            notification.addError("Employee email is required");
        }
        String DNI = registerEmployeeRequest.getDni();
        if (DNI.isEmpty()){
            notification.addError("Employee dni is required");
        }
        if (notification.hasErrors()){
            return notification;
        }
        Optional<EmployeeDni> customerOptional = employeeDniRepository.findByDni(DNI);
        if (customerOptional.isPresent()){
            notification.addError("Employee dni is taken");
        }
        return notification;
    }
}
