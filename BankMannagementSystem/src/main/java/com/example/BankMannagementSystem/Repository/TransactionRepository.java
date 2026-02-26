package com.example.BankMannagementSystem.Repository;

import com.example.BankMannagementSystem.Entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository
        extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByBankAccountNo(int accountNo);
}
