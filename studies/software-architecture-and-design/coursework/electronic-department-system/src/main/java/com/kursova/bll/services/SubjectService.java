package com.kursova.bll.services;

import com.kursova.bll.dto.SubjectDto;
import com.kursova.dal.entities.AssessmentType;

import java.util.List;

/**
 * Service interface for Subject operations
 */
public interface SubjectService extends BaseService<SubjectDto, Long> {

    SubjectDto findBySubjectCode(String subjectCode);

    List<SubjectDto> findActiveSubjects();

    List<SubjectDto> searchByName(String name);

    List<SubjectDto> findByAssessmentType(AssessmentType assessmentType);

    List<SubjectDto> findBySemester(Integer semester);

    List<SubjectDto> findByCredits(Integer credits);

    List<SubjectDto> findByTeacherId(Long teacherId);

    List<SubjectDto> findByGroupId(Long groupId);

    boolean existsBySubjectCode(String subjectCode);

    List<SubjectDto> findSubjectsWithGradesForStudent(Long studentId);

    void assignTeacher(Long subjectId, Long teacherId);

    void removeTeacher(Long subjectId, Long teacherId);

    SubjectDto activateSubject(Long subjectId);

    SubjectDto deactivateSubject(Long subjectId);

    List<Object> getAssignedGroups(Long subjectId);

    List<Object> getAvailableGroups(Long subjectId);

    void addGroupToSubject(Long subjectId, Long groupId);

    void removeGroupFromSubject(Long subjectId, Long groupId);

    List<Object> getAssignedTeachers(Long subjectId);

    List<Object> getAvailableTeachers(Long subjectId);
}
