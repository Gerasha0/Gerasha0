package com.kursova.bll.services;

import com.kursova.bll.dto.GradeDto;
import com.kursova.bll.dto.StudentDto;
import com.kursova.bll.dto.SubjectDto;
import com.kursova.bll.dto.TeacherDto;
import com.kursova.bll.dto.UserDto;

import java.util.List;

/**
 * Service interface for Teacher operations
 */
public interface TeacherService extends BaseService<TeacherDto, Long> {

    TeacherDto findByUserId(Long userId);

    TeacherDto findByUsername(String username);

    List<TeacherDto> findActiveTeachers();

    List<TeacherDto> searchByName(String name);

    List<TeacherDto> findByAcademicTitle(String academicTitle);

    List<TeacherDto> findByDepartmentPosition(String position);

    List<TeacherDto> findBySubjectId(Long subjectId);

    List<StudentDto> findStudentsByTeacherId(Long teacherId);

    List<SubjectDto> findSubjectsByTeacherId(Long teacherId);

    List<GradeDto> findGradesByTeacherId(Long teacherId);

    TeacherDto createWithUser(UserDto userDto, String password, TeacherDto teacherDto);

    TeacherDto assignSubject(Long teacherId, Long subjectId);

    TeacherDto removeSubject(Long teacherId, Long subjectId);

    TeacherDto activateTeacher(Long teacherId);

    TeacherDto deactivateTeacher(Long teacherId);
}
