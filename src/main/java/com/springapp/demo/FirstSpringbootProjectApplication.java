package com.springapp.demo;

import com.springapp.demo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FirstSpringbootProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringbootProjectApplication.class, args);
	}
}
