package com.kursova.bll.services;

import com.kursova.dal.entities.ArchivedGrade;
import com.kursova.dal.entities.ArchivedStudent;
import com.kursova.dal.entities.ArchivedStudentGroup;
import com.kursova.dal.entities.Grade;

import java.time.LocalDateTime;
import java.util.List;

public interface ArchiveService {

    void archiveGrade(Long gradeId, String archivedBy, String reason);

    ArchivedGrade archiveSpecificGrade(Grade grade, String archivedBy, String reason);

    void archiveStudentGroup(Long groupId, String archivedBy, String reason);

    void archiveStudent(Long studentId, String archivedBy, String reason);

    List<ArchivedStudentGroup> getAllArchivedGroups();

    List<ArchivedStudent> getAllArchivedStudents();

    List<ArchivedGrade> getAllArchivedGrades();

    List<ArchivedStudentGroup> searchArchivedGroups(String searchTerm);

    List<ArchivedStudent> searchArchivedStudents(String searchTerm);

    List<ArchivedStudent> getArchivedStudentsByGroupId(Long originalGroupId);

    List<ArchivedGrade> getArchivedGradesByStudentId(Long originalStudentId);

    List<ArchivedStudentGroup> getArchivedGroupsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    ArchiveStatistics getArchiveStatistics();

    void deleteArchivedGroup(Long archivedGroupId);

    void deleteArchivedStudent(Long archivedStudentId);

    void deleteArchivedGrade(Long archivedGradeId);

        record ArchiveStatistics(long totalArchivedGroups, long totalArchivedStudents, long totalArchivedGrades,
                                 LocalDateTime lastArchiveDate) {
    }
}