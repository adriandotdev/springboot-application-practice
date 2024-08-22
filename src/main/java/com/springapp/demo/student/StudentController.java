package com.springapp.demo.student;

import com.springapp.demo.dto.InputStudentDTO;
import com.springapp.demo.dto.StudentDTO;
import com.springapp.demo.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public CustomResponse<List<StudentDTO>> GetStudents() {
        return new CustomResponse<>(HttpStatus.OK, this.service.GetStudents(), "Success");
    }

    @PostMapping
    public CustomResponse<?> AddStudent(@Valid @RequestBody InputStudentDTO student) {

        this.service.AddStudent(student);

        return new CustomResponse<>(HttpStatus.OK, null, "Success");
    }
}
