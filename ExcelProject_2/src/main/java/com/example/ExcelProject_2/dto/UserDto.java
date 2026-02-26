package com.example.ExcelProject_2.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserDto {

    @NotBlank(message = "name is required")
    private String username;

    @Positive(message = "length is greater then four digit")
    private int id;

    @Positive(message = "Age is greater then 0")
    private int age;
}
