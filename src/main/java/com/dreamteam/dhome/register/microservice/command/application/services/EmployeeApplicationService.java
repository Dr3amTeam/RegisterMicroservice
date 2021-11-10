package com.dreamteam.dhome.register.microservice.command.application.services;


import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.registermicroservice.contracts.commands.EditCustomer;
import com.dhome.registermicroservice.contracts.commands.EditEmployee;
import com.dhome.registermicroservice.contracts.commands.RegisterEmployee;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.EditCustomerRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.EditEmployeeRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterEmployeeRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.EditCustomerResponse;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.EditEmployeeResponse;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.RegisterEmployeeResponse;
import com.dreamteam.dhome.register.microservice.command.application.validators.EditEmployeeValidator;
import com.dreamteam.dhome.register.microservice.command.application.validators.RegisterEmployeeValidator;
import com.dreamteam.dhome.register.microservice.command.infra.EmployeeDniRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class EmployeeApplicationService {
    private final RegisterEmployeeValidator registerEmployeeValidator;
    private final CommandGateway commandGateway;
    private final EmployeeDniRepository employeeDniRepository;
    private final EditEmployeeValidator editEmployeeValidator;

    public EmployeeApplicationService( RegisterEmployeeValidator registerEmployeeValidator, EditEmployeeValidator editEmployeeValidator, CommandGateway commandGateway, EmployeeDniRepository employeeDniRepository) {
        this.registerEmployeeValidator = registerEmployeeValidator;
        this.commandGateway = commandGateway;
        this.employeeDniRepository = employeeDniRepository;
        this.editEmployeeValidator = editEmployeeValidator;
    }

    public Result<RegisterEmployeeResponse, Notification> register(RegisterEmployeeRequest registerEmployeeRequest) throws Exception{
        Notification notification = this.registerEmployeeValidator.validate(registerEmployeeRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String accountId = UUID.randomUUID().toString();
        RegisterEmployee registerEmployee = new RegisterEmployee(
                accountId,
                registerEmployeeRequest.getName().trim(),
                registerEmployeeRequest.getLastname().trim(),
                registerEmployeeRequest.getAge(),
                registerEmployeeRequest.getPhone(),
                registerEmployeeRequest.getDni(),
                registerEmployeeRequest.getEmail(),
                registerEmployeeRequest.getPassword(),
                registerEmployeeRequest.getUsername(),
                registerEmployeeRequest.getAddress(),
                registerEmployeeRequest.isVerify(),
                registerEmployeeRequest.getBalance(),
                registerEmployeeRequest.getOffice()
        );
        CompletableFuture<Object> future = commandGateway.send(registerEmployee);
        CompletableFuture<ResultType> futureResult = future.handle((ok,ex)->(ex!=null)?ResultType.FAILURE:ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE){
            throw new Exception();
        }
        RegisterEmployeeResponse registerEmployeeResponse = new RegisterEmployeeResponse(
                registerEmployee.getEmail(),
                registerEmployee.getUsername(),
                registerEmployee.getOffice()
        );
        return Result.success(registerEmployeeResponse);
    }

    public Result<EditEmployeeResponse, Notification> edit (EditEmployeeRequest editEmployeeRequest) throws Exception {
        Notification notification = this.editEmployeeValidator.validate(editEmployeeRequest);
        if(notification.hasErrors()){
            return Result.failure(notification);
        }

        EditEmployee editEmployee = new EditEmployee(
                editEmployeeRequest.getAccountId().trim(),
                editEmployeeRequest.getName().trim(),
                editEmployeeRequest.getLastname().trim(),
                editEmployeeRequest.getAge().trim(),
                editEmployeeRequest.getPhone().trim(),
                editEmployeeRequest.getDni().trim(),
                editEmployeeRequest.getEmail().trim(),
                editEmployeeRequest.getPassword().trim(),
                editEmployeeRequest.getUsername().trim(),
                editEmployeeRequest.getAddress().trim(),
                editEmployeeRequest.isVerify()
        );
        CompletableFuture<Object> future = commandGateway.send(editEmployee);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }
        EditEmployeeResponse editEmployeeResponse = new EditEmployeeResponse(
                editEmployee.getAccountId(),
                editEmployee.getName(),
                editEmployee.getLastname(),
                editEmployee.getAge(),
                editEmployee.getPhone(),
                editEmployee.getDni(),
                editEmployee.getEmail(),
                editEmployee.getPassword(),
                editEmployee.getUsername(),
                editEmployee.getAddress(),
                editEmployee.isVerify()
        );
        return Result.success(editEmployeeResponse);

    }

}
