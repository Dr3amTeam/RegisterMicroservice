package com.dreamteam.dhome.register.microservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeViewRepository extends JpaRepository<EmployeeView,String> {
    Optional<EmployeeView> getEmployeeViewByAccountId(String accountId);
    List<EmployeeView> getEmployeeViewsByUsername(String username);
}
