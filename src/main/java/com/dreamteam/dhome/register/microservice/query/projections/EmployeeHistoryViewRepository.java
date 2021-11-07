package com.dreamteam.dhome.register.microservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeHistoryViewRepository extends JpaRepository<EmployeeHistoryView,String> {
    @Query(value = "SELECT * FROM employee_history_view WHERE account_id= :accountId ORDER BY created_at",nativeQuery = true)
    List<EmployeeHistoryView> getHistoryByAccountId(String accountId);
}
