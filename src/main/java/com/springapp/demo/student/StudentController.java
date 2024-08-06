package com.springapp.demo.student;

import com.springapp.demo.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Student> GetStudents() {
        return this.service.GetStudents();
    }

    @PostMapping
    public CustomResponse<?> AddStudent(@RequestBody Student student) {

        this.service.AddStudent(student);

        return new CustomResponse<>(200, null, "Success");
    }

    @DeleteMapping(path = "{student_id}")
    public CustomResponse<?> DeleteStudent(@PathVariable("student_id") String id) {

        this.service.DeleteStudentByID(id);

        return new CustomResponse<>(200, null, "Success");
    }

    @PutMapping(path = "{student_id}")
    public CustomResponse<?> UpdateStudentByID(@PathVariable("student_id") String id, @RequestBody Student student) {

        this.service.UpdateStudentByID(id, student);

        return new CustomResponse<>(200, null,  "Success");
    }
}
