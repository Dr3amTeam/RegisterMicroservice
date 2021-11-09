package com.dreamteam.dhome.register.microservice.query.api;

import com.dreamteam.dhome.register.microservice.query.projections.AccountHistoryView;
import com.dreamteam.dhome.register.microservice.query.projections.AccountHistoryViewRepository;
import com.dreamteam.dhome.register.microservice.query.projections.AccountView;
import com.dreamteam.dhome.register.microservice.query.projections.AccountViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Api(tags = "Accounts")
public class AccountQueryController {
    private final AccountViewRepository accountViewRepository;
    private final AccountHistoryViewRepository accountHistoryViewRepository;

    public AccountQueryController(AccountViewRepository accountViewRepository, AccountHistoryViewRepository accountHistoryViewRepository) {
        this.accountViewRepository = accountViewRepository;
        this.accountHistoryViewRepository = accountHistoryViewRepository;
    }

    @GetMapping("")
    @ApiOperation(value = "Get all accounts", response = List.class)
    public ResponseEntity<List<AccountView>> getAll() {
        try {
            return new ResponseEntity<List<AccountView>>(accountViewRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "Get customer history", response = List.class)
    public ResponseEntity<List<AccountHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<AccountHistoryView> customers = accountHistoryViewRepository.getHistoryByAccountId(id);
            return new ResponseEntity<List<AccountHistoryView>>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
