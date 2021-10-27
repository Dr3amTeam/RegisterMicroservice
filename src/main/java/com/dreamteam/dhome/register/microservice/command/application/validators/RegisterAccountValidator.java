package com.dreamteam.dhome.register.microservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterAccountRequest;
import com.dreamteam.dhome.register.microservice.command.infra.Customer;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegisterAccountValidator {
    private final CustomerRepository customerRepository;
    public RegisterAccountValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Notification validate(RegisterAccountRequest registerAccountRequest){
        Notification notification = new Notification();
        String username = registerAccountRequest.getUsername().trim();
        if (username.isEmpty()){
            notification.addError("Account username is required");
        }
        String lastname = registerAccountRequest.getLastname().trim();
        if (lastname.isEmpty()){
            notification.addError("Account lastname is required");
        }
        String name = registerAccountRequest.getName().trim();
        if (name.isEmpty()){
            notification.addError("Account name is required");
        }
        String password = registerAccountRequest.getPassword().trim();
        if (password.isEmpty()){
            notification.addError("Account password is required");
        }
        String email = registerAccountRequest.getEmail().trim();
        if (email.isEmpty()){
            notification.addError("Account email is required");
        }
        String DNI = registerAccountRequest.getDNI();
        if (DNI.isEmpty()){
            notification.addError("Account dni is required");
        }
        if (notification.hasErrors()){
            return notification;
        }
        Optional<Customer> customerOptional = customerRepository.findById(DNI);
        if (customerOptional.isPresent()){
            notification.addError("Account dni is taken");
        }
        return notification;
    }
}
