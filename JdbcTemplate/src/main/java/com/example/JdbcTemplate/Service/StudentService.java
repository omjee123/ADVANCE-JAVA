package com.example.JdbcTemplate.Service;


import com.example.JdbcTemplate.Entity.Student;
import com.example.JdbcTemplate.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
            public Student addStudent(Student student){
        return studentRepo.Save(student);
            }

            public void DelleteStudent(int Id){
        studentRepo.DeleteStudent(Id);
            }

            public Student GetByID(int Id){
        return  studentRepo.getById(Id);
            }


    public List<Student> getAllStudents() {
        return studentRepo.getAllStudent();
    }
}
