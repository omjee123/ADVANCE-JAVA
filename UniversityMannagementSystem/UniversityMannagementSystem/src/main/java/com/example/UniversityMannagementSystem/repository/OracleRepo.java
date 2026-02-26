package com.example.UniversityMannagementSystem.repository;

import com.example.UniversityMannagementSystem.entity.StudentOracle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OracleRepo extends JpaRepository<StudentOracle,Long> {
}
