package com.mrpz;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private Teacher teacher;
    private List<Student> students = new ArrayList<>();

    public Course(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return name + " (" + teacher + ")";
    }
}
