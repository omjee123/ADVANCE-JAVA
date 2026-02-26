package com.example.LibraryMannagementSystem.EntityOracle;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "userSeq", initialValue = 1,allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userSeq")
    private Long id;

    private String name;
    private String email;
}
