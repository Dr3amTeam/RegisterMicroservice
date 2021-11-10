package com.dreamteam.dhome.register.microservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDniRepository extends JpaRepository<CustomerDni,String> {
    Optional<CustomerDni> getCustomerDniByAccountId(String accountId);
    /*@Query(value = "SELECT * FROM customer_dni WHERE account_id  :customerId AND dni = :dni LIMIT 1", nativeQuery = true)
    Optional<CustomerDni> getDniByCustomerId(String customerId, String dni);
*/
}
