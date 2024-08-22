package com.springapp.demo.utils;

public record CustomResponse<T>(org.springframework.http.HttpStatus status, T data, String message) {
}
