package com.example.CollegeMannagementSystem.Service;


import com.example.CollegeMannagementSystem.EntityMysql.Student;
import com.example.CollegeMannagementSystem.RepoMysql.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service

public class StudentService {

    @Autowired
    public StudentRepo studentRepo;

    public Student saveStudent(Student student){
        return studentRepo.save(student);
    }

    public List<Student> getAllAtudent(){
        return  StudentRepo.getAllStudent();
    }
}
