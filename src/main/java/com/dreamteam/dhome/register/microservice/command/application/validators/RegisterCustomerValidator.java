package com.dreamteam.dhome.register.microservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterCustomerRequest;
import com.dreamteam.dhome.register.microservice.command.domain.Customer;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDni;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDniRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegisterCustomerValidator {
    private final CustomerDniRepository customerDniRepository;
    public RegisterCustomerValidator(CustomerDniRepository customerDniRepository) {
        this.customerDniRepository = customerDniRepository;
    }
    public Notification validate(RegisterCustomerRequest registerCustomerRequest){
        Notification notification = new Notification();
        String username = registerCustomerRequest.getUsername().trim();
        if (username.isEmpty()){
            notification.addError("Customer username is required");
        }
        String lastname = registerCustomerRequest.getLastname().trim();
        if (lastname.isEmpty()){
            notification.addError("Customer lastname is required");
        }
        String name = registerCustomerRequest.getName().trim();
        if (name.isEmpty()){
            notification.addError("Customer name is required");
        }
        String password = registerCustomerRequest.getPassword().trim();
        if (password.isEmpty()){
            notification.addError("Customer password is required");
        }
        String email = registerCustomerRequest.getEmail().trim();
        if (email.isEmpty()){
            notification.addError("Customer email is required");
        }
        String DNI = registerCustomerRequest.getDni();
        if (DNI.isEmpty()){
            notification.addError("Customer dni is required");
        }
        if (notification.hasErrors()){
            return notification;
        }
        Optional<CustomerDni> customerOptional = customerDniRepository.findById(DNI);
        if (customerOptional.isPresent()){
            notification.addError("Account dni is taken");
        }
        return notification;
    }
}
