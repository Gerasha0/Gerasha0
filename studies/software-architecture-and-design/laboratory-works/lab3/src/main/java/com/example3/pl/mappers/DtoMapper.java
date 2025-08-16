package com.example3.pl.mappers;

import com.example3.domain.models.*;
import com.example3.pl.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Маппер для конвертації між доменними моделями та DTO
 */
@Component
public class DtoMapper {
    
    /**
     * Конвертація Dish в DishDto
     */
    public DishDto toDto(Dish dish) {
        if (dish == null) {
            return null;
        }
        
        return new DishDto(
            dish.getId(),
            dish.getName(),
            dish.getDescription(),
            dish.getPrice(),
            dish.getDishType(),
            dish.isAvailable()
        );
    }
    
    /**
     * Конвертація DishDto в Dish
     */
    public Dish toDomain(DishDto dishDto) {
        if (dishDto == null) {
            return null;
        }
        
        Dish dish = new Dish();
        dish.setId(dishDto.getId());
        dish.setName(dishDto.getName());
        dish.setDescription(dishDto.getDescription());
        dish.setPrice(dishDto.getPrice());
        dish.setDishType(dishDto.getDishType());
        dish.setAvailable(dishDto.isAvailable());
        
        return dish;
    }
    
    /**
     * Конвертація OrderItem в OrderItemDto
     */
    public OrderItemDto toDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        
        OrderItemDto dto = new OrderItemDto();
        dto.setId(orderItem.getId());
        dto.setDish(toDto(orderItem.getDish()));
        dto.setQuantity(orderItem.getQuantity());
        dto.setUnitPrice(orderItem.getUnitPrice());
        dto.setSubtotal(orderItem.getSubtotal());
        
        return dto;
    }
    
    /**
     * Конвертація Order в OrderDto
     */
    public OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }
        
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setCustomerPhone(order.getCustomerPhone());
        dto.setDeliveryAddress(order.getDeliveryAddress());
        dto.setOrderDateTime(order.getOrderDateTime());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderType(order.getOrderType());
        
        // Конвертуємо позиції замовлення
        if (order.getOrderItems() != null) {
            dto.setOrderItems(order.getOrderItems().stream()
                    .map(this::toDto)
                    .toList());
        }
        
        return dto;
    }
    
    /**
     * Конвертація CreateOrderDto в Order
     */
    public Order toDomain(CreateOrderDto createOrderDto) {
        if (createOrderDto == null) {
            return null;
        }
        
        return new Order(
            createOrderDto.getCustomerName(),
            createOrderDto.getCustomerPhone(),
            createOrderDto.getDeliveryAddress(),
            createOrderDto.getOrderType()
        );
    }
    
    /**
     * Конвертація списку Dish в список DishDto
     */
    public List<DishDto> toDishDtoList(List<Dish> dishes) {
        if (dishes == null) {
            return null;
        }
        
        return dishes.stream()
                .map(this::toDto)
                .toList();
    }
    
    /**
     * Конвертація списку Order в список OrderDto
     */
    public List<OrderDto> toOrderDtoList(List<Order> orders) {
        if (orders == null) {
            return null;
        }
        
        return orders.stream()
                .map(this::toDto)
                .toList();
    }
}
