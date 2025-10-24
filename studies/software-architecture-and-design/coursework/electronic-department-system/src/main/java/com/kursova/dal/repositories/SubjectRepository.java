package com.kursova.dal.repositories;

import com.kursova.dal.entities.Subject;
import com.kursova.dal.entities.AssessmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Subject entity
 */
@Repository
public interface SubjectRepository extends BaseRepository<Subject, Long> {

    Optional<Subject> findBySubjectCode(String subjectCode);

    List<Subject> findByIsActiveTrueOrderBySubjectNameAsc();

    @Query("SELECT DISTINCT s FROM Subject s " +
           "LEFT JOIN FETCH s.groups " +
           "LEFT JOIN FETCH s.teachers " +
           "WHERE s.isActive = true " +
           "ORDER BY s.subjectName ASC")
    List<Subject> findActiveSubjectsWithGroups();

    List<Subject> findByAssessmentTypeAndIsActiveTrueOrderBySubjectNameAsc(AssessmentType assessmentType);

    List<Subject> findBySemesterAndIsActiveTrueOrderBySubjectNameAsc(Integer semester);

    List<Subject> findByCreditsAndIsActiveTrueOrderBySubjectNameAsc(Integer credits);

    @Query("SELECT DISTINCT s FROM Subject s JOIN s.teachers t WHERE t.id = :teacherId AND s.isActive = true")
    List<Subject> findByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT DISTINCT s FROM Subject s " +
           "LEFT JOIN FETCH s.groups " +
           "LEFT JOIN FETCH s.teachers t " +
           "WHERE t.id = :teacherId AND s.isActive = true " +
           "ORDER BY s.subjectName ASC")
    List<Subject> findByTeacherIdWithGroups(@Param("teacherId") Long teacherId);

    @Query("SELECT DISTINCT s FROM Subject s " +
           "LEFT JOIN FETCH s.groups g " +
           "LEFT JOIN FETCH s.teachers " +
           "WHERE g.id = :groupId AND s.isActive = true " +
           "ORDER BY s.subjectName ASC")
    List<Subject> findByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT COUNT(g) FROM Subject s JOIN s.groups g WHERE s.id = :subjectId")
    Long countGroupsBySubjectId(@Param("subjectId") Long subjectId);

    @Query("SELECT s FROM Subject s WHERE " +
           "LOWER(s.subjectName) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "AND s.isActive = true ORDER BY s.subjectName")
    List<Subject> searchByName(@Param("name") String name);

    boolean existsBySubjectCode(String subjectCode);

    @Query("SELECT DISTINCT s FROM Subject s JOIN s.grades g WHERE g.student.id = :studentId")
    List<Subject> findSubjectsWithGradesForStudent(@Param("studentId") Long studentId);
}
