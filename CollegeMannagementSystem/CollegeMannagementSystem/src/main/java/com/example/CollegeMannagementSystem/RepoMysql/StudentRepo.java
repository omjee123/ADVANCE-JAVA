package com.example.CollegeMannagementSystem.RepoMysql;

import com.example.CollegeMannagementSystem.EntityMysql.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {

    static List<Student> getAllStudent() {
        return null;
    }
}
