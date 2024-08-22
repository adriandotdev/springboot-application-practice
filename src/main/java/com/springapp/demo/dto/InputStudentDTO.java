package com.springapp.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record InputStudentDTO(

        @NotBlank(message = "given_name is required")
        String given_name,

        @NotBlank(message = "last_name is required")
        String last_name,

        @NotBlank(message = "program_name is required")
        String program_name
) {
}
