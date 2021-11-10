package com.dreamteam.dhome.register.microservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeDniRepository extends JpaRepository<EmployeeDni,String> {
//    Optional<EmployeeDni> findByDni(String dni);


    @Query(value = "SELECT * FROM employee_dni WHERE account_id = :employeeId AND dni = :dni LIMIT 1", nativeQuery = true)
    Optional<EmployeeDni> getDniByEmployeeId(String employeeId, String dni);
//    Optional<EmployeeDni> getByDniForDistinctEmployeeId(String dni, String employeeId);

}
