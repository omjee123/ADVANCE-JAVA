package com.example.BankMannagementSystem.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankEntity {

    @Id
    @SequenceGenerator(name = "seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long accountNo;

    private String name;
    private long mobNo;

    private double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_details_id")
    private AccountDetails accountDetails;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions = new ArrayList<>();
}
