package com.dreamteam.dhome.register.microservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeHistoryViewRepository extends JpaRepository<EmployeeHistoryView,String> {
    @Query(value = "SELECT * FROM employee_history_view WHERE account_id= :accountId ORDER BY created_at",nativeQuery = true)
    List<EmployeeHistoryView> getHistoryByAccountId(String accountId);

    Optional<EmployeeHistoryView> getEmployeeHistoryViewByAccountId(String accountId);
}
