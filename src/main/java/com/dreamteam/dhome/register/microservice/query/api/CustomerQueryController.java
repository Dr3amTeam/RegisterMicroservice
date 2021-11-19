package com.dreamteam.dhome.register.microservice.query.api;

import com.dreamteam.dhome.register.microservice.query.projections.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Api(tags = "Customer")
@CrossOrigin(origins = "https://gateway-service.mybluemix.net")
public class CustomerQueryController {
    private final CustomerViewRepository customerViewRepository;
    private final CustomerHistoryViewRepository customerHistoryViewRepository;

    public CustomerQueryController(CustomerViewRepository customerViewRepository, CustomerHistoryViewRepository customerHistoryViewRepository) {
        this.customerViewRepository = customerViewRepository;
        this.customerHistoryViewRepository = customerHistoryViewRepository;
    }

    @GetMapping("")
    @ApiOperation(value = "Obtener todos los clientes", response = List.class)
    public ResponseEntity<List<CustomerView>> getAll() {
        try {
            return new ResponseEntity<List<CustomerView>>(customerViewRepository.findAll(), HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "Obtener historial del cliente", response = List.class)
    public ResponseEntity<List<CustomerHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<CustomerHistoryView> customers = customerHistoryViewRepository.getHistoryByAccountId(id);
            return new ResponseEntity<List<CustomerHistoryView>>(customers, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
