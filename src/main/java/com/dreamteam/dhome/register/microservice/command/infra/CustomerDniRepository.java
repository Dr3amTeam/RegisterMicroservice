package com.dreamteam.dhome.register.microservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDniRepository extends JpaRepository<CustomerDni,String> {
    Optional<CustomerDni> findByDni(String dni);

}
