package com.springapp.demo.dto;


public record StudentDTO(
        String id,
        String given_name,
        String last_name,
        String program_name,
        String date_created,
        String date_modified
) {


}
