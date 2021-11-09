package com.dreamteam.dhome.register.microservice.command.application.services;


import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.registermicroservice.contracts.commands.RegisterAccount;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterAccountRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.RegisterAccountResponse;
import com.dreamteam.dhome.register.microservice.command.application.validators.RegisterAccountValidator;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class AccountApplicationService {
    private final RegisterAccountValidator registerAccountValidator;
    private final CommandGateway commandGateway;
    private final CustomerRepository customerRepository;

    public AccountApplicationService(RegisterAccountValidator registerAccountValidator, CommandGateway commandGateway, CustomerRepository customerRepository) {
        this.registerAccountValidator = registerAccountValidator;
        this.commandGateway = commandGateway;
        this.customerRepository = customerRepository;
    }

    public Result<RegisterAccountResponse, Notification> register(RegisterAccountRequest registerAccountRequest) throws Exception {
        Notification notification = this.registerAccountValidator.validate(registerAccountRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String accountId = UUID.randomUUID().toString();
        RegisterAccount registerAccount = new RegisterAccount(
                accountId,
                registerAccountRequest.getUsername().trim(),
                registerAccountRequest.getPassword().trim(),
                registerAccountRequest.isVerify()
        );
        CompletableFuture<Object> future = commandGateway.send(registerAccount);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterAccountResponse registerAccountResponse = new RegisterAccountResponse(
                registerAccount.getAccountId(),
                registerAccount.getUsername(),
                registerAccount.getPassword(),
                registerAccount.isVerify()
        );
        return Result.success(registerAccountResponse);
    }

}
