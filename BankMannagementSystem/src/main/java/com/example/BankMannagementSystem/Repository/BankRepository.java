package com.example.BankMannagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.BankMannagementSystem.Entity.BankEntity;

public interface BankRepository extends JpaRepository<BankEntity, Long> {
}

