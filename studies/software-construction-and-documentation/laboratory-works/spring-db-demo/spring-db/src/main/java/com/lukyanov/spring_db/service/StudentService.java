package com.lukyanov.spring_db.service;

import com.lukyanov.spring_db.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
}
