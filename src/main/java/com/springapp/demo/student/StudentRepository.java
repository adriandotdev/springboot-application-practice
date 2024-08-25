package com.springapp.demo.student;

import com.springapp.demo.dto.InputStudentDTO;
import com.springapp.demo.dto.StudentDTO;
import com.springapp.demo.dto.UpdateStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

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

    public Optional<StudentDTO> isStudentExists(int id) {

        RowMapper<StudentDTO> rowMapper = (ResultSet rs, int rowNum) ->
                new StudentDTO(
                        rs.getString("id"),
                        rs.getString("given_name"),
                        rs.getString("last_name"),
                        rs.getString("program_name"),
                        rs.getString("date_created"),
                        rs.getString("date_modified")
                );
        try {
            StudentDTO student = this.template.queryForObject("SELECT * FROM students WHERE id = ?", rowMapper, id);

            return Optional.ofNullable(student);
        }
        catch(EmptyResultDataAccessException err) {
            return Optional.empty();
        }
    }

    public void UpdateStudentByID(int id, UpdateStudentDTO dto) {

        this.template.update("UPDATE students SET given_name = ?, last_name = ?, date_modified = NOW() WHERE id = ?", dto.given_name(), dto.last_name(), id);
    }

    public void DeleteStudentByID(int id) {

        this.template.update("DELETE FROM students WHERE id = ?", id);
    }

    public List<StudentDTO> GetStudentByProgramName(String programName) {

        RowMapper<StudentDTO> rowMapper = (ResultSet rs, int rowNum) ->
                new StudentDTO(
                        rs.getString("id"),
                        rs.getString("given_name"),
                        rs.getString("last_name"),
                        rs.getString("program_name"),
                        rs.getString("date_created"),
                        rs.getString("date_modified")
                );

        try {

            return this.template.query("SELECT * FROM students WHERE program_name = ?", rowMapper, programName);
        }
        catch(DataAccessException err) {
            return List.of();
        }
    }
}
