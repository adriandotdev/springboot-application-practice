package com.springapp.demo.student;

import com.springapp.demo.customexceptions.ResourceNotFoundException;
import com.springapp.demo.dto.InputStudentDTO;
import com.springapp.demo.dto.StudentDTO;
import com.springapp.demo.dto.UpdateStudentDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void UpdateStudentByID(int id, UpdateStudentDTO updateStudentDTO) {

        this.repository.isStudentExists(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id of %d not found", id)));

        this.repository.UpdateStudentByID(id, updateStudentDTO);
    }

    public void DeleteStudentByID(int id) {

        this.repository.isStudentExists(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id of %d not found", id)));

        this.repository.DeleteStudentByID(id);
    }

    public List<StudentDTO> GetStudentsByProgramName(String programName) {

        return this.repository.GetStudentByProgramName(programName);
    }
}
