package com.dreamteam.dhome.register.microservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerHistoryViewRepository extends JpaRepository<CustomerHistoryView,String> {
    @Query(value = "SELECT * FROM customer_history_view WHERE account_id= :accountId ORDER BY created_at",nativeQuery = true)
    List<CustomerHistoryView> getHistoryByAccountId(String accountId);
}
