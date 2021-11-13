package com.dreamteam.dhome.register.microservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerViewRepository extends JpaRepository<CustomerView,String> {
    Optional<CustomerView> getByUsername(String username);
    Optional<CustomerView> getCustomerViewByAccountId(String accountId);
}
