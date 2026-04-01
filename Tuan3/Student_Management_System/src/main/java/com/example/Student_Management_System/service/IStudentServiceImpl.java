package com.example.Student_Management_System.service;

import java.util.List;

import com.example.Student_Management_System.dto.CreateStudentRequest;
import com.example.Student_Management_System.dto.UpdateStudentRequest;
import com.example.Student_Management_System.model.Student;

public interface IStudentServiceImpl {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    public Student createStudent(CreateStudentRequest request);
    public Student updateStudent(Long id, UpdateStudentRequest request);
    public void deleteStudent(Long id);
    public List<Student> getStudentsByMajor(String major);
    public List<Student> getHonorStudents();
}