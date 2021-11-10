package com.dreamteam.dhome.register.microservice.command.application.services;


import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.registermicroservice.contracts.commands.RegisterEmployee;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterEmployeeRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.RegisterEmployeeResponse;
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

    public EmployeeApplicationService( RegisterEmployeeValidator registerEmployeeValidator, CommandGateway commandGateway, EmployeeDniRepository employeeDniRepository) {
        this.registerEmployeeValidator = registerEmployeeValidator;
        this.commandGateway = commandGateway;
        this.employeeDniRepository = employeeDniRepository;
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

}
