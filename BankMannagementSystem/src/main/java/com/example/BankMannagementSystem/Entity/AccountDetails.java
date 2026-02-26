package com.example.BankMannagementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccountDetails {

    @Id
    @SequenceGenerator(
            name = "acc_det_seq",
            sequenceName = "ACC_DET_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_det_seq")
    private Long id;

    private String accountType;
    private String branch;
    private String ifsc;
}

