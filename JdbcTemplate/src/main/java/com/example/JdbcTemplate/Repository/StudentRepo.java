package com.example.JdbcTemplate.Repository;

import com.example.JdbcTemplate.Entity.Student;
import com.example.JdbcTemplate.RowMapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepo {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    //Insert

    public int SaveStudent(Student student){
        String sql="INSERT INTO student(name,email)VALUES(?,?)";
        return jdbcTemplate.update(sql,student.getName(),student.getEmail());
    }

    //Fetch Data

    public List<Student> getAllStudent(){
        String sql="SELECT * FROM Student";
        return jdbcTemplate.query(sql,new StudentRowMapper());
    }

    //GetById

    public Student getById(int Id){
        String sql="Select * from Student where Id=?";
        return  jdbcTemplate.queryForObject(sql,new StudentRowMapper());
    }

    //UPDATE

    public int UpdateStudent(int Id,String name){
        String sql="Update Student Set name=? where id=?";
        return jdbcTemplate.update(sql,name,Id);
    }

    //Delete

    public int DeleteStudent(int Id){
        String sql="DELETE Student where Id=?";
        return jdbcTemplate.update(sql,Id);
    }

    public Student Save(Student student) {
        return student;
    }
}
