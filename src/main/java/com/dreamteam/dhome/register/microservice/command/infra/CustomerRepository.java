package com.dreamteam.dhome.register.microservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
