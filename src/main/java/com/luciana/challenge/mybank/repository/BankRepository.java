package com.luciana.challenge.mybank.repository;

import com.luciana.challenge.mybank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByName(String name);
}
