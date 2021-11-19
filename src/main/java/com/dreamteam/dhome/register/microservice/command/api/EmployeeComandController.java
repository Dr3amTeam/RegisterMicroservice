package com.dreamteam.dhome.register.microservice.command.api;


import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.EditCustomerRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.EditEmployeeRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterCustomerRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.request.RegisterEmployeeRequest;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.EditCustomerResponse;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.EditEmployeeResponse;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.RegisterCustomerResponse;
import com.dreamteam.dhome.register.microservice.command.application.dtos.response.RegisterEmployeeResponse;
import com.dreamteam.dhome.register.microservice.command.application.services.EmployeeApplicationService;
import com.dreamteam.dhome.register.microservice.command.infra.CustomerDniRepository;
import com.dreamteam.dhome.register.microservice.command.infra.EmployeeDniRepository;
import com.dreamteam.dhome.register.microservice.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Api(tags = {SwaggerConfig.EMPLOYEES})
public class EmployeeComandController {
    private final EmployeeApplicationService employeeApplicationService;
    private final CommandGateway commandGateway;
    private final EmployeeDniRepository employeeDniRepository;

    public EmployeeComandController(EmployeeApplicationService employeeApplicationService, CommandGateway commandGateway, EmployeeDniRepository employeeDniRepository) {
        this.employeeApplicationService = employeeApplicationService;
        this.commandGateway = commandGateway;
        this.employeeDniRepository = employeeDniRepository;
    }
    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registrar empleado")
    public ResponseEntity<Object> register(@RequestBody RegisterEmployeeRequest registerEmployeeRequest) {
        try {
            Result<RegisterEmployeeResponse, Notification> result = employeeApplicationService.register(registerEmployeeRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }
    @PutMapping("/{employeeId}")
    @ApiOperation(value = "Actualizar datos de empleado")
    public ResponseEntity<Object> edit(@PathVariable("employeeId") String employeeId, @RequestBody EditEmployeeRequest editEmployeeRequest) {
        try {
            editEmployeeRequest.setAccountId(employeeId);
            Result<EditEmployeeResponse, Notification> result = employeeApplicationService.edit(editEmployeeRequest);
            System.out.println("id employee " + employeeId);
            if(result.isSuccess())
                return ApiController.ok(result.getSuccess());
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch (Exception e) {
            System.out.println("error " + e);
            return ApiController.serverError();
        }
    }
}
