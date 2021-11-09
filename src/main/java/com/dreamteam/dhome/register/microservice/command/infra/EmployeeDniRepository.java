package com.dreamteam.dhome.register.microservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeDniRepository extends JpaRepository<EmployeeDni,String> {
    Optional<EmployeeDni> findByDni(String dni);

}
