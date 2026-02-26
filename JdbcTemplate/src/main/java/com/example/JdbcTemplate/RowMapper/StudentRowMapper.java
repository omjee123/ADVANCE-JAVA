package com.example.JdbcTemplate.RowMapper;

import com.example.JdbcTemplate.Entity.Student;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs,int rowNum) throws SQLException{
        Student s=new Student();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        return s;
    }
}

