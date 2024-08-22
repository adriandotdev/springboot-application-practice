package com.springapp.demo.student;

import com.springapp.demo.dto.InputStudentDTO;
import com.springapp.demo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate template;

    public List<StudentDTO> GetStudents() {

        var query = "SELECT * FROM students";

        RowMapper<StudentDTO> rowMapper = (ResultSet rs, int rowNum) ->
                new StudentDTO(
                        rs.getString("id"),
                        rs.getString("given_name"),
                        rs.getString("last_name"),
                        rs.getString("program_name"),
                        rs.getString("date_created"),
                        rs.getString("date_modified")
                );
        return this.template.query(query, rowMapper);
    }

    public void AddStudent(InputStudentDTO student) {

        this.template.update("INSERT INTO students (given_name, last_name, program_name, date_created, date_modified) VALUES (?,?,?,NOW(),NOW())", student.given_name(), student.last_name(), student.program_name());
    }
}
