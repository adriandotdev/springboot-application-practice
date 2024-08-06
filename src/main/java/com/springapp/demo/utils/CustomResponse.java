package com.springapp.demo.utils;

public record CustomResponse<T>(int status, T data, String message) {
}
