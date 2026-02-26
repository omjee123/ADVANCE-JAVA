package com.example.UniversityMannagementSystem.Service;



import com.example.UniversityMannagementSystem.dto.TeacherDto;
import com.example.UniversityMannagementSystem.entityMysql.TeacherMysql;
import com.example.UniversityMannagementSystem.repositoryMySql.MySqlRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherImpl {

    private final MySqlRepo repo;

    public TeacherImpl(MySqlRepo repo) {
        this.repo = repo;
    }

    // CREATE
    public TeacherDto saveTeacher(TeacherDto dto) {

        TeacherMysql teacher = new TeacherMysql();
        teacher.setName(dto.getName());
        teacher.setSalary(dto.getSalary());

        TeacherMysql saved = repo.save(teacher);

        return new TeacherDto(saved.getName(), saved.getSalary());
    }

    // READ ALL
    public List<TeacherDto> getAllTeachers() {

        return repo.findAll()
                .stream()
                .map(t -> new TeacherDto(t.getName(), t.getSalary()))
                .collect(Collectors.toList());
    }

    // READ BY ID
    public TeacherDto getTeacherById(Long id) {

        TeacherMysql teacher = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        return new TeacherDto(teacher.getName(), teacher.getSalary());
    }

    // UPDATE
    public TeacherDto updateTeacher(Long id, TeacherDto dto) {

        TeacherMysql teacher = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        teacher.setName(dto.getName());
        teacher.setSalary(dto.getSalary());

        TeacherMysql updated = repo.save(teacher);

        return new TeacherDto(updated.getName(), updated.getSalary());
    }

    // DELETE
    public void deleteTeacher(Long id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }

        repo.deleteById(id);
    }
}