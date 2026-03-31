package com.example.Student_Management_System.service;

import com.example.Student_Management_System.dto.CreateStudentRequest;
import com.example.Student_Management_System.dto.UpdateStudentRequest;
import com.example.Student_Management_System.exception.DuplicateResourceException;
import com.example.Student_Management_System.exception.ResourceNotFoundException;
import com.example.Student_Management_System.model.Student;
import org.springframework.boot.micrometer.metrics.autoconfigure.MetricsProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    public List<Student> getAllStudents() {
        return students;
    }

    // Khi tìm/sửa/xóa theo id: throw ResourceNotFoundException nếu không tồn tại
    public Student getStudentById(Long id) {
        return students.stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
    }

    // Khi tạo: kiểm tra trùng studentCode và trùng email -> throw DuplicateResourceException
    public Student createStudent(CreateStudentRequest request) {
        // 1. kiem tra trung studentCode
        boolean studentCodeExists = students.stream()
                .anyMatch(s -> s.getStudentCode().equals(request.getStudentCode()));
        // neu trung -> throw DuplicateResourceException
        if (studentCodeExists) {
            throw new DuplicateResourceException("Student", "studentCode", request.getStudentCode());
        }

        // 2. kiem tra trung email
        boolean emailExists = students.stream()
                .anyMatch(s -> s.getEmail().equals(request.getEmail()));
        // neu trung -> throw DuplicateResourceException
        if (emailExists) {
            throw new DuplicateResourceException("Student", "email", request.getEmail());
        }

        // 3. Khoi tao student, set thuoc tinh
        Student newStudent = new Student();
        newStudent.setId(nextId++);
        newStudent.setStudentCode(request.getStudentCode());
        newStudent.setName(request.getName());
        newStudent.setEmail(request.getEmail());
        newStudent.setPhone(request.getPhone());
        newStudent.setDateOfBirth(request.getDateOfBirth());
        newStudent.setGpa(request.getGpa());
        newStudent.setMajor(request.getMajor());
        newStudent.setYear(request.getYear());

        // 4. Them vao danh sach
        students.add(newStudent);
        return newStudent;
    }

    // Khi sửa: kiểm tra email mới có bị trùng với sinh viên khác không
    // Khi tìm/sửa/xóa theo id: throw ResourceNotFoundException nếu không tồn tại
    public Student updateStudent(Long id, UpdateStudentRequest request) {
        // 1. Tim SV cu, neu ko bao loi
        Student existingStudent = students.stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        // 2. Kiem tra email trung
        boolean emailExists = students.stream().anyMatch(s -> s.getEmail().equals(request.getEmail()) && !s.getId().equals(id));
        if (emailExists) {
            throw new DuplicateResourceException("Student", "email", request.getEmail());
        }

        // 3. Cap nhat thong tin
        existingStudent.setName(request.getName());
        existingStudent.setEmail(request.getEmail());
        existingStudent.setPhone(request.getPhone());
        existingStudent.setDateOfBirth(request.getDateOfBirth());
        existingStudent.setGpa(request.getGpa());
        existingStudent.setMajor(request.getMajor());
        existingStudent.setYear(request.getYear());

        return existingStudent;
    }

    // Khi tìm/sửa/xóa theo id: throw ResourceNotFoundException nếu không tồn tại
    public void deleteStudent(Long id) {
        // 1. Co gang xoa
        boolean removed = students.removeIf(s -> s.getId().equals(id));

        // 2. Neu ko xoa duoc
        if (!removed) {
            throw new ResourceNotFoundException("Student", "id", id);
        }
    }

    // Lọc sinh viên theo ngành học (major)
    public List<Student> getStudentsByMajor(String major) {
        // Có thể trả về danh sách rỗng?
        return students.stream().filter(s -> s.getMajor().equals(major)).toList();
    }

    // Lấy danh sách sinh viên xuất sắc (GPA ≥ 3.6)
    public List<Student> getHonorStudents() {
        return students.stream().filter(s -> s.getGpa() >= 3.6).toList();
    }
}
