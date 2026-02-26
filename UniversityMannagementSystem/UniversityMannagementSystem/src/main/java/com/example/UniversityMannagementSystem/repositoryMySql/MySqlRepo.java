package com.example.UniversityMannagementSystem.repositoryMySql;


import com.example.UniversityMannagementSystem.entityMysql.TeacherMysql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySqlRepo extends JpaRepository<TeacherMysql,Long> {
}
