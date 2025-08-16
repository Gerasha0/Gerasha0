package com.example2.dal.repositories.interfaces;

import com.example2.dal.entities.ComplexLunch;
import java.time.LocalDate;
import java.util.List;

public interface IComplexLunchRepository extends IGenericRepository<ComplexLunch, Long> {
    List<ComplexLunch> findByAvailableDate(LocalDate date);
    List<ComplexLunch> findAvailableComplexLunches();
    List<ComplexLunch> findByNameContainingIgnoreCase(String name);
}
