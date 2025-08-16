package com.mrpz;

import java.util.HashMap;
import java.util.Map;

public class Archive {
    // Map<Course, Map<Student, Integer>>
    private Map<Course, Map<Student, Integer>> archive = new HashMap<>();

    public void addGrade(Course course, Student student, int grade) {
        archive.computeIfAbsent(course, k -> new HashMap<>()).put(student, grade);
    }

    public Integer getGrade(Course course, Student student) {
        Map<Student, Integer> grades = archive.get(course);
        if (grades != null) {
            return grades.get(student);
        }
        return null;
    }

    public Map<Course, Map<Student, Integer>> getAllGrades() {
        return archive;
    }
}
