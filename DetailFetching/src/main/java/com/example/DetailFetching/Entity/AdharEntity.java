package com.example.DetailFetching.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AdtharData")
public class AdharEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "adh_seq",initialValue = 1,allocationSize = 1)
    private  long id;

    @Column(name = "Adharno",nullable = false,unique = true)
    private Long AdharNO;


    @Column(name = "name")
    private String name;


    @Column(name = "Gender")
    private String Gender;

    @Column(name = "dob")
    private  long dob;

}
