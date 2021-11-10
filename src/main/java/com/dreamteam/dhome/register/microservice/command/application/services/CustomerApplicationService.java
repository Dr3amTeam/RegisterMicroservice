package com.dreamteam.dhome.register.microservice.command.application.services;


import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.registermicroservice.contracts.commands.RegisterCustomer;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterCustomerRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.RegisterCustomerResponse;
import com.dreamteam.dhome.register.microservice.command.application.validators.RegisterCustomerValidator;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDniRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class CustomerApplicationService {
    private final RegisterCustomerValidator registerAccountValidator;
    private final CommandGateway commandGateway;
    private final CustomerDniRepository customerRepository;

    public CustomerApplicationService(RegisterCustomerValidator registerAccountValidator, CommandGateway commandGateway, CustomerDniRepository customerRepository) {
        this.registerAccountValidator = registerAccountValidator;
        this.commandGateway = commandGateway;
        this.customerRepository = customerRepository;
    }

    public Result<RegisterCustomerResponse, Notification> register(RegisterCustomerRequest registerAccountRequest) throws Exception{
        Notification notification = this.registerAccountValidator.validate(registerAccountRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String accountId = UUID.randomUUID().toString();
        RegisterCustomer registerCustomer = new RegisterCustomer(
                accountId,
                registerAccountRequest.getName().trim(),
                registerAccountRequest.getLastname().trim(),
                registerAccountRequest.getAge(),
                registerAccountRequest.getPhone(),
                registerAccountRequest.getDni(),
                registerAccountRequest.getEmail(),
                registerAccountRequest.getPassword(),
                registerAccountRequest.getUsername(),
                registerAccountRequest.getAddress(),
                registerAccountRequest.isVerify(),
                registerAccountRequest.getBalance()
        );
        CompletableFuture<Object> future = commandGateway.send(registerCustomer);
        CompletableFuture<ResultType> futureResult = future.handle((ok,ex)->(ex!=null)?ResultType.FAILURE:ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE){
            throw new Exception();
        }
        RegisterCustomerResponse registerAccountResponse = new RegisterCustomerResponse(
                registerCustomer.getEmail(),
                registerCustomer.getUsername()
        );
        return Result.success(registerAccountResponse);
    }

}
