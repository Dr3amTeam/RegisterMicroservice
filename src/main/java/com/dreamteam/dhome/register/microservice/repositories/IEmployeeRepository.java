package com.dreamteam.dhome.register.microservice.repositories;


import com.dreamteam.dhome.register.microservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByDni(String dni);

    public List<Employee> findByLastName(String lastname);

    public List<Employee> findByLastNameAndFirstName(String lastname, String firstname);

    public List<Employee> findByFirstName(String firstname);

    public Optional<Employee> findByEmail(String email);

    public Optional<Employee> findByAccount_Id(Long id);
}

