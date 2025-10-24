package com.kursova.bll.services;

import com.kursova.bll.dto.StudentGroupDto;
import java.util.List;

public interface StudentGroupService extends BaseService<StudentGroupDto, Long> {

    List<StudentGroupDto> findActiveGroups();

    List<StudentGroupDto> searchByName(String name);

    List<StudentGroupDto> searchByNameOrCode(String searchTerm);

    StudentGroupDto findByGroupCode(String groupCode);

    boolean existsByGroupCode(String groupCode);

    List<StudentGroupDto> findByEnrollmentYear(Integer year);

    StudentGroupDto activateGroup(Long groupId);

    StudentGroupDto deactivateGroup(Long groupId);

    StudentGroupDto findByIdWithStudentCount(Long id);

    List<StudentGroupDto> findGroupsWithStudents();

    List<StudentGroupDto> findGroupsByTeacherId(Long teacherId);
}
