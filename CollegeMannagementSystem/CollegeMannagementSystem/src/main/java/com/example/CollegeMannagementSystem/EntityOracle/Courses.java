package com.example.CollegeMannagementSystem.EntityOracle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courses {
    @Id
    @SequenceGenerator(name = "stud_seq",initialValue = 1,allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    private String Courses;
private  Long fees;
}
