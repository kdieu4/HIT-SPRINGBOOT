package com.example.Student_Management_System.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.GenericArrayType;
import java.time.LocalDate;

@Setter
@Getter
public class Student {
    private Long id;
    private String studentCode;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Double gpa;
    private String major;
    private Integer year;

    public Student() {
    }

    public Student(Long id, String studentCode, String name, String email, String phone, LocalDate dateOfBirth, Double gpa, String major, Integer year) {
        this.id = id;
        this.studentCode = studentCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.gpa = gpa;
        this.major = major;
        this.year = year;
    }
}
