package com.example.BankMannagementSystem.Services;

import com.example.BankMannagementSystem.Entity.AccountDetails;
import com.example.BankMannagementSystem.Entity.BankEntity;
import com.example.BankMannagementSystem.Entity.TransactionEntity;
import com.example.BankMannagementSystem.Repository.BankRepository;
import com.example.BankMannagementSystem.Repository.TransactionRepository;
import com.example.BankMannagementSystem.dto.CreateAccountDTO;
import com.example.BankMannagementSystem.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankService {

    private final BankRepository bankRepo;
    private final TransactionRepository txRepo;

    public BankService(BankRepository bankRepo, TransactionRepository txRepo) {
        this.bankRepo = bankRepo;
        this.txRepo = txRepo;
    }

    // CREATE ACCOUNT
    public BankEntity createAccount(CreateAccountDTO dto) {
        BankEntity bank = new BankEntity();
        bank.setName(dto.getName());
        bank.setMobNo(dto.getMobNo());
        bank.setBalance(0);

        AccountDetails details = new AccountDetails();
        details.setBranch(dto.getBranch());
        details.setIfsc(dto.getIfsc());
        details.setAccountType(dto.getAccountType());

        bank.setAccountDetails(details);
        return bankRepo.save(bank);
    }

    // CHECK BALANCE
    public double checkBalance(Long accNo) {
        return bankRepo.findById(accNo)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"))
                .getBalance();
    }

    // DEPOSIT
    public BankEntity deposit(Long accNo, double amount) {
        BankEntity bank = bankRepo.findById(accNo)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        bank.setBalance(bank.getBalance() + amount);

        TransactionEntity tx = new TransactionEntity();
        tx.setAmount(amount);
        tx.setType("CREDIT");
        tx.setTransactionDate(LocalDateTime.now());
        tx.setBank(bank);

        bank.getTransactions().add(tx);
        return bankRepo.save(bank);
    }

    // WITHDRAW
    public BankEntity withdraw(Long accNo, double amount) {
        BankEntity bank = bankRepo.findById(accNo)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (bank.getBalance() - amount < 1000) {
            throw new RuntimeException("Minimum balance ₹1000 required");
        }

        bank.setBalance(bank.getBalance() - amount);

        TransactionEntity tx = new TransactionEntity();
        tx.setAmount(amount);
        tx.setType("DEBIT");
        tx.setTransactionDate(LocalDateTime.now());
        tx.setBank(bank);

        bank.getTransactions().add(tx);
        return bankRepo.save(bank);
    }

    // TRANSACTION HISTORY
    public List<TransactionEntity> history(Long accNo) {
        return txRepo.findByBankAccountNo(Math.toIntExact(accNo));
    }

    // CLOSE ACCOUNT
    public String close(Long accNo) {
        BankEntity bank = bankRepo.findById(accNo)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (bank.getBalance() > 0)
            throw new RuntimeException("Balance must be zero");

        bankRepo.delete(bank);
        return "Account closed successfully";
    }
}
