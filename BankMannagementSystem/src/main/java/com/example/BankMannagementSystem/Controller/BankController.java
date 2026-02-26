package com.example.BankMannagementSystem.Controller;
import com.example.BankMannagementSystem.Services.BankService;
import com.example.BankMannagementSystem.Entity.BankEntity;
import com.example.BankMannagementSystem.Entity.TransactionEntity;
import com.example.BankMannagementSystem.dto.CreateAccountDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public BankEntity create(@Valid @RequestBody CreateAccountDTO dto) {
        return service.createAccount(dto);
    }

    @GetMapping("/balance/{id}")
    public double balance(@PathVariable int id) {
        return service.checkBalance((long) id);
    }

    @PutMapping("/deposit/{id}/{amount}")
    public BankEntity deposit(@PathVariable int id, @PathVariable double amount) {
        return service.deposit((long) id, amount);
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public BankEntity withdraw(@PathVariable int id, @PathVariable double amount) {
        return service.withdraw((long) id, amount);
    }

    @GetMapping("/transactions/{id}")
    public List<TransactionEntity> history(@PathVariable int id) {
        return service.history((long) id);
    }

    @DeleteMapping("/close/{id}")
    public String close(@PathVariable int id) {
        return service.close((long) id);
    }
}
