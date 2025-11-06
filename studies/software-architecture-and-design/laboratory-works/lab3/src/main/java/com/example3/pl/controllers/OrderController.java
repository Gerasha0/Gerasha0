package com.example3.pl.controllers;

import com.example3.bll.services.OrderService;
import com.example3.domain.models.Order;
import com.example3.domain.models.OrderStatus;
import com.example3.pl.dto.*;
import com.example3.pl.mappers.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * WebAPI контролер для роботи із замовленнями
 */
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    
    private final OrderService orderService;
    private final DtoMapper dtoMapper;
    
    @Autowired
    public OrderController(OrderService orderService, DtoMapper dtoMapper) {
        this.orderService = orderService;
        this.dtoMapper = dtoMapper;
    }
    
    /**
     * GET /api/orders - Отримати всі замовлення
     */
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            List<OrderDto> orderDtos = dtoMapper.toOrderDtoList(orders);
            return ResponseEntity.ok(orderDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET /api/orders/{id} - Отримати замовлення за ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        try {
            Optional<Order> orderOpt = orderService.getOrderById(id);
            if (orderOpt.isPresent()) {
                OrderDto orderDto = dtoMapper.toDto(orderOpt.get());
                return ResponseEntity.ok(orderDto);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET /api/orders/by-status/{status} - Отримати замовлення за статусом
     */
    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<OrderDto>> getOrdersByStatus(@PathVariable OrderStatus status) {
        try {
            List<Order> orders = orderService.getOrdersByStatus(status);
            List<OrderDto> orderDtos = dtoMapper.toOrderDtoList(orders);
            return ResponseEntity.ok(orderDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET /api/orders/by-customer?phone={phone} - Отримати замовлення клієнта
     */
    @GetMapping("/by-customer")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerPhone(@RequestParam String phone) {
        try {
            List<Order> orders = orderService.getOrdersByCustomerPhone(phone);
            List<OrderDto> orderDtos = dtoMapper.toOrderDtoList(orders);
            return ResponseEntity.ok(orderDtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * POST /api/orders - Створити нове замовлення
     */
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        try {
            Order order = dtoMapper.toDomain(createOrderDto);
            Order createdOrder = orderService.createOrder(order);
            OrderDto resultDto = dtoMapper.toDto(createdOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * POST /api/orders/complex-lunch - Створити комплексний обід
     */
    @PostMapping("/complex-lunch")
    public ResponseEntity<OrderDto> createComplexLunch(@RequestBody ComplexLunchDto complexLunchDto) {
        try {
            Order order = orderService.createComplexLunch(
                complexLunchDto.getCustomerName(),
                complexLunchDto.getCustomerPhone(),
                complexLunchDto.getDeliveryAddress(),
                complexLunchDto.getFirstCourseId(),
                complexLunchDto.getSecondCourseId(),
                complexLunchDto.getSaladId(),
                complexLunchDto.getDrinkId()
            );
            OrderDto resultDto = dtoMapper.toDto(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * POST /api/orders/{orderId}/dishes/{dishId}?quantity={quantity} - Додати страву до замовлення
     */
    @PostMapping("/{orderId}/dishes/{dishId}")
    public ResponseEntity<OrderDto> addDishToOrder(
            @PathVariable Long orderId, 
            @PathVariable Long dishId, 
            @RequestParam int quantity) {
        try {
            Order order = orderService.addDishToOrder(orderId, dishId, quantity);
            OrderDto resultDto = dtoMapper.toDto(order);
            return ResponseEntity.ok(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * PUT /api/orders/{id}/status/{status} - Оновити статус замовлення
     */
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @PathVariable OrderStatus status) {
        try {
            Order order = orderService.updateOrderStatus(id, status);
            OrderDto resultDto = dtoMapper.toDto(order);
            return ResponseEntity.ok(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * PUT /api/orders/{id}/confirm - Підтвердити замовлення
     */
    @PutMapping("/{id}/confirm")
    public ResponseEntity<OrderDto> confirmOrder(@PathVariable Long id) {
        try {
            Order order = orderService.confirmOrder(id);
            OrderDto resultDto = dtoMapper.toDto(order);
            return ResponseEntity.ok(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * PUT /api/orders/{id}/cancel - Скасувати замовлення
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long id) {
        try {
            Order order = orderService.cancelOrder(id);
            OrderDto resultDto = dtoMapper.toDto(order);
            return ResponseEntity.ok(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * DELETE /api/orders/{orderId}/dishes/{dishId} - Видалити страву з замовлення
     */
    @DeleteMapping("/{orderId}/dishes/{dishId}")
    public ResponseEntity<OrderDto> removeDishFromOrder(
            @PathVariable Long orderId, 
            @PathVariable Long dishId) {
        try {
            Order order = orderService.removeDishFromOrder(orderId, dishId);
            OrderDto resultDto = dtoMapper.toDto(order);
            return ResponseEntity.ok(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * DELETE /api/orders/{id} - Видалити замовлення
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
