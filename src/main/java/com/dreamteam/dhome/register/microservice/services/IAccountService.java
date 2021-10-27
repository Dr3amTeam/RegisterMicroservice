package com.dreamteam.dhome.register.microservice.services;
import com.dreamteam.dhome.register.microservice.entities.Account;

import java.util.Optional;

public interface IAccountService extends CrudService<Account>{
    public Optional<Account> findByUsername(String username) throws Exception;
    public Optional<Account> findByUsernameAndPassword(String username, String password) throws Exception;
}
