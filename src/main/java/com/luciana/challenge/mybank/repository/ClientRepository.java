package com.luciana.challenge.mybank.repository;

import com.luciana.challenge.mybank.contact_of_customer.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
