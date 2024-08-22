package com.springapp.demo.student;

import com.springapp.demo.dto.InputStudentDTO;
import com.springapp.demo.dto.StudentDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentDTO> GetStudents() {
        return this.repository.GetStudents();
    }

    public void AddStudent(@Valid InputStudentDTO student) {

        this.repository.AddStudent(student);
    }
}
