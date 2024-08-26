package com.springapp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FirstSpringbootProjectApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext  = SpringApplication.run(FirstSpringbootProjectApplication.class, args);

		String[] beans = applicationContext.getBeanDefinitionNames();
		for(String bean : beans) {
			System.out.println("BEAN: " + bean);
		}
	}
}
