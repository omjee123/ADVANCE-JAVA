package com.example.UniversityMannagementSystem.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDto {

    @NotBlank(message ="name is mandatory")
    private String name;

    @Positive(message = "salary >20000")
    private Long Salary;
}
