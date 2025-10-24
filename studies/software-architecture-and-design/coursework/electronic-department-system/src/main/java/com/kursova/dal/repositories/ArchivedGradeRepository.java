package com.kursova.dal.repositories;

import com.kursova.dal.entities.ArchivedGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for ArchivedGrade entities
 */
@Repository
public interface ArchivedGradeRepository extends JpaRepository<ArchivedGrade, Long> {

    List<ArchivedGrade> findByOriginalStudentId(Long originalStudentId);

    List<ArchivedGrade> findByStudentNumberContainingIgnoreCase(String studentNumber);

    List<ArchivedGrade> findByOriginalGroupId(Long originalGroupId);

    List<ArchivedGrade> findByGroupCodeContainingIgnoreCase(String groupCode);

    List<ArchivedGrade> findBySubjectNameContainingIgnoreCase(String subjectName);

    List<ArchivedGrade> findByArchivedBy(String archivedBy);

    @Query("SELECT ag FROM ArchivedGrade ag WHERE ag.archivedAt BETWEEN :startDate AND :endDate ORDER BY ag.archivedAt DESC")
    List<ArchivedGrade> findByArchivedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<ArchivedGrade> findAllByOrderByArchivedAtDesc();

    @Query("SELECT AVG(ag.gradeValue) FROM ArchivedGrade ag WHERE ag.originalStudentId = :studentId")
    Double getAverageGradeForArchivedStudent(@Param("studentId") Long studentId);
}
