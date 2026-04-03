package com.example.Student_Management_System.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private LocalDateTime timeStamp;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(200, "Thành công", data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<T>(201, "Tạo thành công", data);
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<T>(code, message);
    }
}
