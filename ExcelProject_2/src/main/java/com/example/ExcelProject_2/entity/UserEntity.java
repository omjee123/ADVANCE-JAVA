package com.example.ExcelProject_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    private String username;
    private int id;
    private String password;
    private int age;
}
