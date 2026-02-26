package com.example.Employee.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq")
    @SequenceGenerator(name = "sq",allocationSize = 50,initialValue = 1)
    private int id;

    private String name;

    private String department;
    private int salary;
}
