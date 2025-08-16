package com.example2.bll.mappers;

import com.example2.bll.dto.ComplexLunchDto;
import com.example2.dal.entities.ComplexLunch;
import java.util.List;
import java.util.stream.Collectors;

public class ComplexLunchMapper {
    
    private ComplexLunchMapper() {}
    
    public static ComplexLunchDto toDto(ComplexLunch entity) {
        if (entity == null) {
            return null;
        }
        
        ComplexLunchDto dto = new ComplexLunchDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setAvailableDate(entity.getAvailableDate());
        dto.setIsAvailable(entity.getIsAvailable());
        
        if (entity.getDishes() != null) {
            dto.setDishes(DishMapper.toDtoList(entity.getDishes()));
        }
        
        return dto;
    }
    
    public static ComplexLunch toEntity(ComplexLunchDto dto) {
        if (dto == null) {
            return null;
        }
        
        ComplexLunch entity = new ComplexLunch();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setAvailableDate(dto.getAvailableDate());
        entity.setIsAvailable(dto.getIsAvailable());
        
        if (dto.getDishes() != null) {
            entity.setDishes(DishMapper.toEntityList(dto.getDishes()));
        }
        
        return entity;
    }
    
    public static List<ComplexLunchDto> toDtoList(List<ComplexLunch> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(ComplexLunchMapper::toDto)
                .collect(Collectors.toList());
    }
}
