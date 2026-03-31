package com.example.Student_Management_System.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateStudentRequest {
    @NotBlank(message = "Tên sinh viên không được để trống")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "0\\d{9}", message = "Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0")
    private String phone;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    private LocalDate dateOfBirth;

    @NotNull(message = "GPA không được để trống")
    @Min(value = 0, message = "GPA tối thiểu là 0.0")
    @Max(value = 4, message = "GPA tối đa là 4.0")
    private Double gpa;

    @NotBlank(message = "Ngành học không được để trống")
    private String major;

    @NotNull(message = "Năm học không được để trống")
    @Min(value = 1, message = "Năm học tối thiểu là 1")
    @Max(value = 6, message = "Năm học tối đa là 6")
    private Integer year;
}
