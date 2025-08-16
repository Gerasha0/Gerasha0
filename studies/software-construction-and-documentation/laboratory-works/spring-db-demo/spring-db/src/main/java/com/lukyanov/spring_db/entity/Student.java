package com.lukyanov.spring_db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    private Integer id;

    private String name;

    @Column(name = "email_address", nullable = false, unique = true, length = 40)
    private String email;
}
