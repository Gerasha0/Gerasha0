package com.kursova.bll.services;

import com.kursova.bll.dto.StudentDto;
import java.util.List;

public interface StudentService extends BaseService<StudentDto, Long> {

    List<StudentDto> findActiveStudents();

    StudentDto findByUserId(Long userId);

    List<StudentDto> searchByName(String name);

    List<StudentDto> findByGroup(Long groupId);

    List<StudentDto> findStudentsWithoutGroup();

    List<StudentDto> findByEnrollmentYear(Integer year);

    Double calculateAverageGrade(Long studentId);

    StudentDto findByIdWithCalculatedData(Long id);

    List<StudentDto> findByGroupId(Long groupId);

    StudentDto activateStudent(Long studentId);

    StudentDto deactivateStudent(Long studentId);

    StudentDto assignToGroup(Long studentId, Long groupId);

    StudentDto removeFromGroup(Long studentId);

    List<Object> searchStudentsForGroup(String query, Long groupId);

    StudentDto findByEmail(String username); // Parameter name kept as 'email' for API compatibility
}
