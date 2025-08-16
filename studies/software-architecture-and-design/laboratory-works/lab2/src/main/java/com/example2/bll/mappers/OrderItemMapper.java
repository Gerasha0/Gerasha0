package com.example2.bll.mappers;

import com.example2.bll.dto.OrderItemDto;
import com.example2.dal.entities.OrderItem;

public class OrderItemMapper {
    
    private OrderItemMapper() {}
    
    public static OrderItemDto toDto(OrderItem entity) {
        if (entity == null) {
            return null;
        }
        
        OrderItemDto dto = new OrderItemDto();
        dto.setId(entity.getId());
        dto.setDish(DishMapper.toDto(entity.getDish()));
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        
        return dto;
    }
    
    public static OrderItem toEntity(OrderItemDto dto) {
        if (dto == null) {
            return null;
        }
        
        OrderItem entity = new OrderItem();
        entity.setId(dto.getId());
        entity.setDish(DishMapper.toEntity(dto.getDish()));
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        
        return entity;
    }
}
