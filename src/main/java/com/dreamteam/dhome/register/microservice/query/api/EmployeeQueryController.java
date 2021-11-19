package com.dreamteam.dhome.register.microservice.query.api;

import com.dreamteam.dhome.register.microservice.query.projections.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Api(tags = "Employee")
public class EmployeeQueryController {
    private final EmployeeViewRepository employeeViewRepository;
    private final EmployeeHistoryViewRepository employeeHistoryViewRepository;

    public EmployeeQueryController(EmployeeViewRepository employeeViewRepository, EmployeeHistoryViewRepository employeeHistoryViewRepository) {
        this.employeeViewRepository = employeeViewRepository;
        this.employeeHistoryViewRepository = employeeHistoryViewRepository;
    }

    @GetMapping("")
    @ApiOperation(value = "Obtener todos los empleados", response = List.class)
    public ResponseEntity<List<EmployeeView>> getAll() {
        try {
            return new ResponseEntity<List<EmployeeView>>(employeeViewRepository.findAll(), HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("username/{username}")
    @ApiOperation(value = "Get all employee", response = List.class)
    public ResponseEntity<List<EmployeeView>> getAllByUsername(@PathVariable("username")String username) {
        try {
            List<EmployeeView> employeeViews = employeeViewRepository.getEmployeeViewsByUsername(username);
            return new ResponseEntity<List<EmployeeView>>(employeeViews, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "Obtener historial del empleado", response = List.class)
    public ResponseEntity<List<EmployeeHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<EmployeeHistoryView> employees = employeeHistoryViewRepository.getHistoryByAccountId(id);
            return new ResponseEntity<List<EmployeeHistoryView>>(employees, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
