package com.springapp.demo.student;

import com.springapp.demo.dto.InputStudentDTO;
import com.springapp.demo.dto.StudentDTO;
import com.springapp.demo.dto.UpdateStudentDTO;
import com.springapp.demo.utils.CustomResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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

    @PutMapping("/{id}")
    public CustomResponse<?> UpdateUser(@PathVariable("id") int id, @Valid @RequestBody UpdateStudentDTO updateStudentDTO) {

        this.service.UpdateStudentByID(id, updateStudentDTO);

        return new CustomResponse<>(HttpStatus.OK, null, "Success");
    }

    @DeleteMapping("/{id}")
    public CustomResponse<?> DeleteStudentByID(@PathVariable("id") int id) {

        this.service.DeleteStudentByID(id);

        return new CustomResponse<>(HttpStatus.OK, null, "Success");
    }

    @GetMapping("/search")
    public CustomResponse<List<StudentDTO>> GetStudentsByProgramName(@RequestParam("program_name") String programName) {

        List<StudentDTO> students = this.service.GetStudentsByProgramName(programName);

        return new CustomResponse<>(HttpStatus.OK, students, "Success");
    }
}
