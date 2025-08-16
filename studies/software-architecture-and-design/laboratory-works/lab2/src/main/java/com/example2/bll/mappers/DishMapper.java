package com.example2.bll.mappers;

import com.example2.bll.dto.DishDto;
import com.example2.dal.entities.Dish;
import java.util.List;
import java.util.stream.Collectors;

public class DishMapper {
    
    private DishMapper() {}
    
    public static DishDto toDto(Dish entity) {
        if (entity == null) {
            return null;
        }
        
        DishDto dto = new DishDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setDishType(entity.getDishType());
        dto.setAvailableDate(entity.getAvailableDate());
        dto.setIsAvailable(entity.getIsAvailable());
        
        return dto;
    }
    
    public static Dish toEntity(DishDto dto) {
        if (dto == null) {
            return null;
        }
        
        Dish entity = new Dish();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setDishType(dto.getDishType());
        entity.setAvailableDate(dto.getAvailableDate());
        entity.setIsAvailable(dto.getIsAvailable());
        
        return entity;
    }
    
    public static List<DishDto> toDtoList(List<Dish> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(DishMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public static List<Dish> toEntityList(List<DishDto> dtos) {
        if (dtos == null) {
            return null;
        }
        
        return dtos.stream()
                .map(DishMapper::toEntity)
                .collect(Collectors.toList());
    }
}
