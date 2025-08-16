package com.example2.bll.mappers;

import com.example2.bll.dto.OrderDto;
import com.example2.bll.dto.OrderItemDto;
import com.example2.dal.entities.Order;
import com.example2.dal.entities.OrderItem;
import java.util.List;
import java.util.Objects;
import java.util.Collections;

public class OrderMapper {
    
    private OrderMapper() {}
    
    public static OrderDto toDto(Order entity) {
        if (entity == null) {
            return null;
        }
        
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setCustomerAddress(entity.getCustomerAddress());
        dto.setCustomerPhone(entity.getCustomerPhone());
        dto.setOrderDate(entity.getOrderDate());
        dto.setStatus(entity.getStatus());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setIsComplexLunch(entity.getIsComplexLunch());
        
        if (entity.getOrderItems() != null) {
            List<OrderItemDto> orderItemDtos = entity.getOrderItems().stream()
                    .filter(Objects::nonNull)
                    .map(OrderItemMapper::toDto)
                    .filter(Objects::nonNull)
                    .toList();
            dto.setOrderItems(orderItemDtos);
        }
        
        return dto;
    }
    
    public static Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setCustomerName(dto.getCustomerName());
        entity.setCustomerAddress(dto.getCustomerAddress());
        entity.setCustomerPhone(dto.getCustomerPhone());
        entity.setOrderDate(dto.getOrderDate());
        entity.setStatus(dto.getStatus());
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setIsComplexLunch(dto.getIsComplexLunch());
        
        if (dto.getOrderItems() != null) {
            List<OrderItem> orderItems = dto.getOrderItems().stream()
                    .map(orderItemDto -> {
                        OrderItem orderItem = OrderItemMapper.toEntity(orderItemDto);
                        orderItem.setOrder(entity);
                        return orderItem;
                    })
                    .toList();
            entity.setOrderItems(orderItems);
        }
        
        return entity;
    }
    
    public static List<OrderDto> toDtoList(List<Order> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        
        return entities.stream()
                .map(OrderMapper::toDto)
                .toList();
    }
}
