package com.example2.bll.services.interfaces;

import com.example2.bll.dto.ComplexLunchDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IComplexLunchService {
    ComplexLunchDto createComplexLunch(ComplexLunchDto complexLunchDto);
    Optional<ComplexLunchDto> getComplexLunchById(Long id);
    List<ComplexLunchDto> getAllComplexLunches();
    List<ComplexLunchDto> getComplexLunchesByDate(LocalDate date);
    List<ComplexLunchDto> getAvailableComplexLunches();
    List<ComplexLunchDto> searchComplexLunchesByName(String name);
    ComplexLunchDto addDishToComplexLunch(Long complexLunchId, Long dishId);
    ComplexLunchDto removeDishFromComplexLunch(Long complexLunchId, Long dishId);
    ComplexLunchDto updateComplexLunch(ComplexLunchDto complexLunchDto);
    void deleteComplexLunch(Long id);
    void setComplexLunchAvailability(Long id, Boolean isAvailable);
}
