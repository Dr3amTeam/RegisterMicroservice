package com.dreamteam.dhome.register.microservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.EditCustomerRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.EditEmployeeRequest;
import com.dreamteam.dhome.register.microservice.command.domain.Customer;
import com.dreamteam.dhome.register.microservice.command.domain.Employee;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDniRepository;
import com.dreamteam.dhome.register.microservice.command.infra.EmployeeDniRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Component
public class EditEmployeeValidator {

    private final Repository<Employee> employeeRepository;
    private final EmployeeDniRepository employeeDniRepository;

    public EditEmployeeValidator(EmployeeDniRepository employeeDniRepository, Repository<Employee> employeeRepository) {
        this.employeeDniRepository = employeeDniRepository;
        this.employeeRepository = employeeRepository;
    }

    public Notification validate(EditEmployeeRequest editEmployeeRequest) {

        Notification notification = new Notification();
        String employeeId = editEmployeeRequest.getAccountId().trim();
        if(employeeId.isEmpty()){
            notification.addError("Employee id is required");
        }
        loadEmployeeAggregate(employeeId);
        String name = editEmployeeRequest.getName().trim();
        if(name.isEmpty()){
            notification.addError("Employee name is required");
        }
        String lastname = editEmployeeRequest.getLastname().trim();
        if(lastname.isEmpty()){
            notification.addError("Employee lastname is required");
        }
        String address = editEmployeeRequest.getAddress().trim();
        if(address.isEmpty()){
            notification.addError("Employee address is required");
        }
        String age = editEmployeeRequest.getAge().trim();
        if(age.isEmpty()){
            notification.addError("Employee age is required");
        }
        String dni = editEmployeeRequest.getDni().trim();
        if(dni.isEmpty()){
            notification.addError("Employee dni is required");
        }
        String email = editEmployeeRequest.getEmail().trim();
        if(email.isEmpty()){
            notification.addError("Employee email is required");
        }
        String phone = editEmployeeRequest.getPhone().trim();
        if(phone.isEmpty()){
            notification.addError("Employee phone is required");
        }
        String username = editEmployeeRequest.getUsername().trim();
        if(username.isEmpty()){
            notification.addError("Employee username is required");
        }
        String password = editEmployeeRequest.getPassword().trim();
        if(password.isEmpty()){
            notification.addError("Employee password is required");
        }
        return notification;
    }

    private void loadEmployeeAggregate(String employeeId){
        UnitOfWork unitOfWork = null;

        try{
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            employeeRepository.load(employeeId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex){
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex){
            if(unitOfWork != null) unitOfWork.rollback();
        }
    }



}
