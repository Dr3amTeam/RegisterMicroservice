package com.dreamteam.dhome.register.microservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.EditCustomerRequest;
import com.dreamteam.dhome.register.microservice.command.domain.Customer;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDniRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Component
public class EditCustomerValidator {
    private final CustomerDniRepository customerDniRepository;
    private final Repository<Customer> customerRepository;

    public EditCustomerValidator(CustomerDniRepository customerDniRepository, Repository<Customer> customerRepository) {
        this.customerDniRepository = customerDniRepository;
        this.customerRepository = customerRepository;
    }

    public Notification validate(EditCustomerRequest editCustomerRequest) {

        Notification notification = new Notification();
        String customerId = editCustomerRequest.getAccountId().trim();
        if(customerId.isEmpty()){
            notification.addError("Customer id is required");
        }
        loadCustomerAggregate(customerId);
        String name = editCustomerRequest.getName().trim();
        if(name.isEmpty()){
            notification.addError("Customer name is required");
        }
        String lastname = editCustomerRequest.getLastname().trim();
        if(lastname.isEmpty()){
            notification.addError("Customer lastname is required");
        }
        String address = editCustomerRequest.getAddress().trim();
        if(address.isEmpty()){
            notification.addError("Customer address is required");
        }
        String age = editCustomerRequest.getAge().trim();
        if(age.isEmpty()){
            notification.addError("Customer age is required");
        }
        String dni = editCustomerRequest.getDni().trim();
        if(dni.isEmpty()){
            notification.addError("Customer dni is required");
        }
        String email = editCustomerRequest.getEmail().trim();
        if(email.isEmpty()){
            notification.addError("Customer email is required");
        }
        String phone = editCustomerRequest.getPhone().trim();
        if(phone.isEmpty()){
            notification.addError("Customer phone is required");
        }
        String username = editCustomerRequest.getUsername().trim();
        if(username.isEmpty()){
            notification.addError("Customer username is required");
        }
        String password = editCustomerRequest.getPassword().trim();
        if(password.isEmpty()){
            notification.addError("Customer password is required");
        }
        return notification;
    }

    private void loadCustomerAggregate(String customerId){
        UnitOfWork unitOfWork = null;

        try{
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            customerRepository.load(customerId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex){
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex){
            if(unitOfWork != null) unitOfWork.rollback();
        }
    }


}
