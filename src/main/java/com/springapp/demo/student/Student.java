package com.springapp.demo.student;

public record Student(String id, String givenName, String lastName, String programName) {

    public Student {

        if (givenName.isEmpty()) throw new IllegalArgumentException("Missing required property: givenName");

        if (lastName.isEmpty()) throw new IllegalArgumentException("Missing required property: lastName");

        if (programName.isEmpty()) throw new IllegalArgumentException("Missing required property: programName");
    }
}
