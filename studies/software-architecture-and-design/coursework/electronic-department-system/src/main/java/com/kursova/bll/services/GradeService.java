package com.kursova.bll.services;

import com.kursova.bll.dto.GradeDto;
import com.kursova.dal.entities.GradeType;

import java.time.LocalDateTime;
import java.util.List;

public interface GradeService extends BaseService<GradeDto, Long> {

    List<GradeDto> findByStudentId(Long studentId);

    List<GradeDto> findByTeacherId(Long teacherId);

    List<GradeDto> findBySubjectId(Long subjectId);

    List<GradeDto> findByStudentAndSubject(Long studentId, Long subjectId);

    GradeDto findByStudentSubjectAndType(Long studentId, Long subjectId, GradeType gradeType);

    List<GradeDto> findFinalGradesByStudent(Long studentId);

    List<GradeDto> findByGradeType(GradeType gradeType);

    List<GradeDto> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    Double getAverageGradeForStudentInSubject(Long studentId, Long subjectId);

    Double getOverallAverageGradeForStudent(Long studentId);

    List<Object[]> getTopPerformingStudents();

    Long countGradesByTeacherAndSubject(Long teacherId, Long subjectId);

    GradeDto createGradeWithValidation(Long studentId, Long teacherId, Long subjectId,
                                      Integer gradeValue, GradeType gradeType, String comments);

    GradeDto updateGradeWithValidation(Long gradeId, Integer gradeValue, String comments);

    GradeDto markAsFinal(Long gradeId);
}
