package com.example.UniversityMannagementSystem.Service;

import com.example.UniversityMannagementSystem.dto.StudentDto;
import com.example.UniversityMannagementSystem.entity.StudentOracle;
import com.example.UniversityMannagementSystem.repository.OracleRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentImpl {

    private final OracleRepo repo;

    public StudentImpl(OracleRepo repo) {
        this.repo = repo;
    }

    public StudentOracle addStudent(StudentOracle student){
        return repo.save(student);
    }

    public List<StudentOracle> getAll(){
        return repo.findAll();
    }

    public StudentOracle findStudentById(long id){
        return repo.findById(id).orElseThrow(()->new RuntimeException("Student Not Found"));
    }

    public StudentOracle updateStudent(Long id, StudentDto dto) {
        StudentOracle student = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(dto.getName());
        student.setBranch(dto.getBranch());
        student.setSemester(dto.getSemester());

        return repo.save(student);
    }

    public void deleteStudent(Long id) {
        StudentOracle student = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        repo.delete(student);
    }
}
