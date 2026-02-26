package com.example.UniversityMannagementSystem.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Branch is mandatory")
    @Size(min = 2, max = 10, message = "Branch must be between 2 and 10 characters")
    private String branch;

    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester must not exceed 8")
    private int semester;
}
