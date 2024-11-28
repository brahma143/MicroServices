package com.microservices.Repository;

import com.microservices.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

   // @Override
    Optional<Customer> findByMobileNumber(String mobileNumber);

}
