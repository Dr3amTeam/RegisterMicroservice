package com.dreamteam.dhome.register.microservice.services.impl;


import com.dreamteam.dhome.register.microservice.entities.Account;
import com.dreamteam.dhome.register.microservice.repositories.IAccountRepository;
import com.dreamteam.dhome.register.microservice.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    @Transactional
    public Account save(Account account) throws Exception {
        return accountRepository.save(account);
    }

    @Override
    public void delete(Long id) throws Exception {
        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> getAll() throws Exception {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getById(Long id) throws Exception {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> findByUsername(String username) throws Exception {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Optional<Account> findByUsernameAndPassword(String username, String password) throws Exception {
        return accountRepository.findByUsernameAndPassword(username,password);
    }
    /*
    @Override
    public Optional<Account> findById(Long id) throws Exception {
        return accountRepository.findById(id);
    }

     */
}
