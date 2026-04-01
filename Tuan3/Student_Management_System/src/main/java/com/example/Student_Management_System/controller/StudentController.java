package com.example.Student_Management_System.controller;

import com.example.Student_Management_System.dto.ApiResponse;
import com.example.Student_Management_System.dto.CreateStudentRequest;
import com.example.Student_Management_System.dto.UpdateStudentRequest;
import com.example.Student_Management_System.model.Student;
import com.example.Student_Management_System.service.impl.StudentServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    // GET /api/students — lấy tất cả
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        return ResponseEntity.ok(ApiResponse.success(studentService.getAllStudents()));
    }

    // GET /api/students/{id} — lấy theo id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@Valid @PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(ApiResponse.success(student));
    }

    // POST /api/students — tạo mới
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@Valid @RequestBody CreateStudentRequest request) {
        Student student = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(student));
    }

    // PUT /api/students/{id} — cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable Long id,
            @Valid @RequestBody UpdateStudentRequest request) {
        Student student = studentService.updateStudent(id, request);
        return ResponseEntity.ok(ApiResponse.success(student));
    }

    // DELETE /api/students/{id} — xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Xoá sinh viên thành công", null));
    }

    // GET /api/students/major/{major} — lọc theo ngành
    @GetMapping("/major/{major}")
    public ResponseEntity<ApiResponse<List<Student>>> getStudentByMajor(@PathVariable @NotBlank String major) {
        List<Student> filteredStudent = studentService.getStudentsByMajor(major);
        return ResponseEntity.ok(ApiResponse.success(filteredStudent));
    }

    // GET /api/students/honors — danh sách sinh viên xuất sắc
    @GetMapping("/honors")
    public ResponseEntity<ApiResponse<List<Student>>> getHonorStudents() {
        List<Student> filteredStudent = studentService.getHonorStudents();
        return ResponseEntity.ok(ApiResponse.success(filteredStudent));
    }
}
