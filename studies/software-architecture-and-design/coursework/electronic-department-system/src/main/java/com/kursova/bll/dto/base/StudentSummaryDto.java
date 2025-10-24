package com.kursova.bll.dto.base;

public class StudentSummaryDto {
    private Long id;
    private Long userId;
    private String name;
    private String studentNumber;

    public StudentSummaryDto() {}

    public StudentSummaryDto(Long id, String name, String studentNumber) {
        this.id = id;
        this.name = name;
        this.studentNumber = studentNumber;
    }

     public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
}
