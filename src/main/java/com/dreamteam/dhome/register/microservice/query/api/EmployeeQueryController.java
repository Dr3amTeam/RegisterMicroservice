package com.dreamteam.dhome.register.microservice.query.api;

import com.dreamteam.dhome.register.microservice.query.projections.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @ApiOperation(value = "Get all employee", response = List.class)
    public ResponseEntity<List<EmployeeView>> getAll() {
        try {
            return new ResponseEntity<List<EmployeeView>>(employeeViewRepository.findAll(), HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("username/{username}")
    @ApiOperation(value = "Get all employee by username", response = List.class)
    public ResponseEntity<List<EmployeeView>> getAllByUsername(@PathVariable("username")String username) {
        try {
            List<EmployeeView> employeeViews = employeeViewRepository.getEmployeeViewsByUsername(username);
            return new ResponseEntity<List<EmployeeView>>(employeeViews, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get by id", response = List.class)
    public ResponseEntity<EmployeeView> getAll(@PathVariable("id") String id) {
        try {
            Optional<EmployeeView> employeeView =employeeViewRepository.findById(id);
            EmployeeView response = employeeView.get();
            return new ResponseEntity<EmployeeView>(response,HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "Get history of employee", response = List.class)
    public ResponseEntity<List<EmployeeHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<EmployeeHistoryView> employees = employeeHistoryViewRepository.getHistoryByAccountId(id);
            return new ResponseEntity<List<EmployeeHistoryView>>(employees, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
