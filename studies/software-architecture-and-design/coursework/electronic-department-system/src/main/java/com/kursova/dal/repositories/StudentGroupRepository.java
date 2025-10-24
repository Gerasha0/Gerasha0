package com.kursova.dal.repositories;

import com.kursova.dal.entities.StudentGroup;
import com.kursova.dal.entities.StudyForm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for StudentGroup entity
 */
@Repository
public interface StudentGroupRepository extends BaseRepository<StudentGroup, Long> {

    Optional<StudentGroup> findByGroupName(String groupName);

    List<StudentGroup> findByIsActiveTrueOrderByGroupNameAsc();

    List<StudentGroup> findByCourseYearAndIsActiveTrueOrderByGroupNameAsc(Integer courseYear);

    List<StudentGroup> findByStudyFormAndIsActiveTrueOrderByGroupNameAsc(StudyForm studyForm);

    List<StudentGroup> findByStartYearAndIsActiveTrueOrderByGroupNameAsc(Integer startYear);

    List<StudentGroup> findBySpecializationContainingIgnoreCaseAndIsActiveTrueOrderByGroupNameAsc(String specialization);

    boolean existsByGroupName(String groupName);

    @Query("SELECT g FROM StudentGroup g WHERE " +
           "LOWER(g.groupName) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "AND g.isActive = true ORDER BY g.groupName")
    List<StudentGroup> searchByName(@Param("name") String name);

    @Query("SELECT g FROM StudentGroup g WHERE " +
           "g.isActive = true AND " +
           "(g.maxStudents IS NULL OR SIZE(g.students) < g.maxStudents) " +
           "ORDER BY g.groupName")
    List<StudentGroup> findGroupsWithAvailableSlots();

    Optional<StudentGroup> findByGroupCode(String groupCode);

    boolean existsByGroupCode(String groupCode);

    List<StudentGroup> findByEnrollmentYear(Integer enrollmentYear);

    List<StudentGroup> findByIsActiveTrue();

    List<StudentGroup> findByGroupNameContainingIgnoreCase(String name);

    List<StudentGroup> findByGroupNameContainingIgnoreCaseOrGroupCodeContainingIgnoreCase(String name, String code);

    @Query("SELECT DISTINCT g FROM StudentGroup g JOIN g.students s WHERE g.isActive = true ORDER BY g.groupName")
    List<StudentGroup> findGroupsWithStudents();

    @Query("SELECT DISTINCT g FROM StudentGroup g " +
           "JOIN g.subjects s " +
           "JOIN s.teachers t " +
           "WHERE t.id = :teacherId AND g.isActive = true " +
           "ORDER BY g.groupName")
    List<StudentGroup> findGroupsByTeacherId(@Param("teacherId") Long teacherId);
}
