package com.springapp.demo.student;

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

    public List<Student> GetStudents() {
        return this.repository.GetStudents();
    }

    public void AddStudent(Student student) {

        UUID uuid = UUID.randomUUID();

        this.repository.AddStudent(new Student(uuid.toString(), student.givenName(), student.lastName(), student.programName()));
    }

    public void DeleteStudentByID(String id) {

        this.repository.DeleteStudentByID(id);
    }

    public void UpdateStudentByID(String id, Student student) {

        this.repository.UpdateStudentByID(id, student);
    }
}
