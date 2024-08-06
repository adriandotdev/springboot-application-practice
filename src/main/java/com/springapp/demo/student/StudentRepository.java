package com.springapp.demo.student;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class StudentRepository {

    private List<Student> students = new ArrayList<>();

    public List<Student> GetStudents() {
        return this.students;
    }

    public void AddStudent(Student student) {
        this.students.add(student);
    }

    public void DeleteStudentByID(String id) {

        boolean isFound = this.students.removeIf(student -> student.id().equals(id));

        if (!isFound)
            throw new NoSuchElementException("STUDENT_NOT_FOUND");
    }

    public void UpdateStudentByID(String id, Student student) {

        List<Student> newLists = new ArrayList<>();
        Student studentToUpdate = null;

        for (Student student1 : this.students) {

            if (student1.id().equals(id)) {
                studentToUpdate = student1;
                newLists.add(new Student(id, student.givenName(), student.lastName(), student.programName()));
            }
            else {
                newLists.add(student1);
            }
        }

        if (studentToUpdate == null) throw new NoSuchElementException("STUDENT_NOT_FOUND");

        this.students = newLists;
    }
}
