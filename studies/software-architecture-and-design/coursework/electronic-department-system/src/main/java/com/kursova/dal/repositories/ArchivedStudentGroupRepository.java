package com.kursova.dal.repositories;

import com.kursova.dal.entities.ArchivedStudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for ArchivedStudentGroup entities
 */
@Repository
public interface ArchivedStudentGroupRepository extends JpaRepository<ArchivedStudentGroup, Long> {

    List<ArchivedStudentGroup> findByGroupCodeContainingIgnoreCase(String groupCode);

    List<ArchivedStudentGroup> findByGroupNameContainingIgnoreCase(String groupName);

    List<ArchivedStudentGroup> findByOriginalGroupId(Long originalGroupId);

    List<ArchivedStudentGroup> findByArchivedBy(String archivedBy);

    @Query("SELECT ag FROM ArchivedStudentGroup ag WHERE ag.archivedAt BETWEEN :startDate AND :endDate ORDER BY ag.archivedAt DESC")
    List<ArchivedStudentGroup> findByArchivedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<ArchivedStudentGroup> findAllByOrderByArchivedAtDesc();
}
