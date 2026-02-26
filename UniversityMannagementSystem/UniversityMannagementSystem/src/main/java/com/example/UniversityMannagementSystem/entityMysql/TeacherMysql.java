package com.example.UniversityMannagementSystem.entityMysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Teach")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class TeacherMysql {

    @Id
 @SequenceGenerator(name = "tech",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tech")

    private Long id;

    private String name ;
    private  Long salary;

}
