package com.dreamteam.dhome.register.microservice.command.api;


import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterCustomerRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.RegisterCustomerResponse;
import com.dreamteam.dhome.register.microservice.command.application.services.CustomerApplicationService;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDniRepository;
import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@Api(tags = "Customer")
public class CustomerComandController {
    private final CustomerApplicationService customerApplicationService;
    private final CommandGateway commandGateway;
    private final CustomerDniRepository customerDniRepository;

    public CustomerComandController(CustomerApplicationService customerApplicationService, CommandGateway commandGateway, CustomerDniRepository customerDniRepository) {
        this.customerApplicationService = customerApplicationService;
        this.commandGateway = commandGateway;
        this.customerDniRepository = customerDniRepository;
    }
    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterCustomerRequest registerCustomerRequest) {
        try {
            Result<RegisterCustomerResponse, Notification> result = customerApplicationService.register(registerCustomerRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }
}
