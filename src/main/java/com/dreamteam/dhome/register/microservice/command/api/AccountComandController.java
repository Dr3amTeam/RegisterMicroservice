package com.dreamteam.dhome.register.microservice.command.api;


import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterAccountRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.RegisterAccountResponse;
import com.dreamteam.dhome.register.microservice.command.application.services.AccountApplicationService;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerRepository;
import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Api(tags = "Accounts")
public class AccountComandController {
    private final AccountApplicationService accountApplicationService;
    private final CommandGateway commandGateway;
    private final CustomerRepository customerRepository;

    public AccountComandController(AccountApplicationService accountApplicationService, CommandGateway commandGateway, CustomerRepository customerRepository) {
        this.accountApplicationService = accountApplicationService;
        this.commandGateway = commandGateway;
        this.customerRepository = customerRepository;
    }
    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterAccountRequest registerAccountRequest) {
        try {
            Result<RegisterAccountResponse, Notification> result = accountApplicationService.register(registerAccountRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }
}
