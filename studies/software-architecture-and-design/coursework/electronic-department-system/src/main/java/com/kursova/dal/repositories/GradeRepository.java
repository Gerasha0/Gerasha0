package com.kursova.dal.repositories;

import com.kursova.dal.entities.Grade;
import com.kursova.dal.entities.GradeType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Grade entity
 */
@Repository
public interface GradeRepository extends BaseRepository<Grade, Long> {

    List<Grade> findByStudentIdOrderByGradeDateDesc(Long studentId);

    List<Grade> findByTeacherIdOrderByGradeDateDesc(Long teacherId);

    List<Grade> findBySubjectIdOrderByGradeDateDesc(Long subjectId);

    List<Grade> findByStudentIdAndSubjectIdOrderByGradeDateDesc(Long studentId, Long subjectId);

    Optional<Grade> findByStudentIdAndSubjectIdAndGradeType(Long studentId, Long subjectId, GradeType gradeType);

    List<Grade> findByStudentIdAndIsFinalTrueOrderBySubjectSubjectNameAsc(Long studentId);

    List<Grade> findByGradeTypeOrderByGradeDateDesc(GradeType gradeType);

    List<Grade> findByGradeDateBetweenOrderByGradeDateDesc(LocalDateTime startDate, LocalDateTime endDate);

    @Query("select g from Grade g " +
        "left join fetch g.student s " +
        "left join fetch s.user su " +
        "left join fetch g.teacher t " +
        "left join fetch t.user tu " +
        "left join fetch g.subject subj " +
        "where g.id = :id")
    Optional<Grade> findByIdWithRelations(@Param("id") Long id);

    @Query("SELECT AVG(g.gradeValue) FROM Grade g WHERE g.student.id = :studentId AND g.subject.id = :subjectId")
    Double getAverageGradeForStudentInSubject(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);

    @Query("SELECT AVG(g.gradeValue) FROM Grade g WHERE g.student.id = :studentId AND g.isFinal = true")
    Double getOverallAverageGradeForStudent(@Param("studentId") Long studentId);

    @Query("SELECT g.student.id, AVG(g.gradeValue) as avgGrade FROM Grade g " +
           "WHERE g.isFinal = true GROUP BY g.student.id ORDER BY avgGrade DESC")
    List<Object[]> findTopPerformingStudents();

    @Query("SELECT COUNT(g) FROM Grade g WHERE g.teacher.id = :teacherId AND g.subject.id = :subjectId")
    Long countGradesByTeacherAndSubject(@Param("teacherId") Long teacherId, @Param("subjectId") Long subjectId);

    @Query("select distinct g from Grade g " +
        "left join fetch g.student s " +
        "left join fetch s.user su " +
        "left join fetch g.teacher t " +
        "left join fetch t.user tu " +
        "left join fetch g.subject subj")
    List<Grade> findAllWithRelations();

    @Query("SELECT g FROM Grade g " +
           "JOIN g.subject s " +
           "JOIN s.teachers t " +
           "WHERE t.id = :teacherId " +
           "ORDER BY g.gradeDate DESC")
    List<Grade> findGradesByTeacherId(@Param("teacherId") Long teacherId);
}
