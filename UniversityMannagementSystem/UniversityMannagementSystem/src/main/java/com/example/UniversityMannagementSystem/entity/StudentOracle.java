package com.example.UniversityMannagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Stud")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentOracle {
    @Id
    @SequenceGenerator(name = "stud",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqStud")

    private Long id;


    private   String name;
    private  String branch;
    private   int Semester;
    @Entity
    @Table(name = "Stud")
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class studentOracle {

        @Id
        @SequenceGenerator(
                name = "seqStud",
                sequenceName = "seq_stud",   // 👈 must match DB sequence name
                allocationSize = 1
        )
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqStud")
        private Long id;

        private String name;
        private String branch;
        private int semester;
    }
}
