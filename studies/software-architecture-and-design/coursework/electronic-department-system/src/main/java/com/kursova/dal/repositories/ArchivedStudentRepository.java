package com.kursova.dal.repositories;

import com.kursova.dal.entities.ArchivedStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for ArchivedStudent entities
 */
@Repository
public interface ArchivedStudentRepository extends JpaRepository<ArchivedStudent, Long> {

    List<ArchivedStudent> findByStudentNumberContainingIgnoreCase(String studentNumber);

    List<ArchivedStudent> findByOriginalStudentId(Long originalStudentId);

    List<ArchivedStudent> findByOriginalGroupId(Long originalGroupId);

    List<ArchivedStudent> findByGroupCodeContainingIgnoreCase(String groupCode);

    List<ArchivedStudent> findByArchivedBy(String archivedBy);

    @Query("SELECT as FROM ArchivedStudent as WHERE as.archivedAt BETWEEN :startDate AND :endDate ORDER BY as.archivedAt DESC")
    List<ArchivedStudent> findByArchivedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<ArchivedStudent> findAllByOrderByArchivedAtDesc();
}
