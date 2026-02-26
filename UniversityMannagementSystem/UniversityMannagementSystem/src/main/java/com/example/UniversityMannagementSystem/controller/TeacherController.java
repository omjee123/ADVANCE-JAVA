package com.example.UniversityMannagementSystem.controller;


import com.example.UniversityMannagementSystem.Service.TeacherImpl;
import com.example.UniversityMannagementSystem.dto.TeacherDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherImpl service;

    public TeacherController(TeacherImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TeacherDto> save(@Valid @RequestBody TeacherDto dto) {
        return ResponseEntity.ok(service.saveTeacher(dto));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(service.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTeacherById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable Long id,
                                             @Valid @RequestBody TeacherDto dto) {
        return ResponseEntity.ok(service.updateTeacher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }
}