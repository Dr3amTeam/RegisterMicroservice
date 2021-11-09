package com.dreamteam.dhome.register.microservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountHistoryViewRepository extends JpaRepository<AccountHistoryView, String> {
    @Query(value = "SELECT * FROM account_history_view WHERE account_id= :accountId ORDER BY created_at", nativeQuery = true)
    List<AccountHistoryView> getHistoryByAccountId(String accountId);
}
