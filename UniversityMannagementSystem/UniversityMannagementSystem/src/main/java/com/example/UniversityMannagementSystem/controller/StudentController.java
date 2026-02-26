package com.example.UniversityMannagementSystem.controller;

import com.example.UniversityMannagementSystem.Service.StudentImpl;
import com.example.UniversityMannagementSystem.dto.StudentDto;
import com.example.UniversityMannagementSystem.entity.StudentOracle;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentImpl student;

    public StudentController(StudentImpl student) {
        this.student = student;
    }

    @PostMapping("/add")
    public ResponseEntity<StudentOracle> addStudent(
            @Valid @RequestBody StudentDto dto) {

        StudentOracle student = new StudentOracle();
        student.setName(dto.getName());
        student.setBranch(dto.getBranch());
        student.setSemester(dto.getSemester());

        return ResponseEntity.ok(this.student.addStudent(student));
    }

    @GetMapping("/detail/{id}")

    public ResponseEntity<StudentOracle> getById(@Valid @PathVariable Long id) {
        StudentOracle stud = student.findStudentById(id);
        return new ResponseEntity<>(stud, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<StudentOracle>> getAllStudents() {
        List<StudentOracle> students = student.getAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentOracle> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDto dto) {

        StudentOracle updatedStudent = student.updateStudent(id, dto);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        student.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

}
