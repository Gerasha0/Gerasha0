package com.lukyanov.spring_db.controller;

import com.lukyanov.spring_db.entity.Student;
import com.lukyanov.spring_db.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping(path = "/students")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }
}
