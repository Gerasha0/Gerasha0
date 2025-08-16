package com.lukyanov.spring_db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "musical_instrument")
public class MusicalInstrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    private Double price;

    private String material;

    @Column(name = "string_count")
    private Integer stringCount;
}
