package com.example.Employee.Repository;

import com.example.Employee.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employee, Integer> {
    Object findByid(int id);
}
