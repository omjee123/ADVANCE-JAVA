package com.example.BankMannagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class CreateAccountDTO {

    @NotBlank
    private String name;

    @NotNull
    private Long mobNo;

    @NotBlank
    private String branch;

    @NotBlank
    private String ifsc;

    @NotBlank
    private String accountType;
}
