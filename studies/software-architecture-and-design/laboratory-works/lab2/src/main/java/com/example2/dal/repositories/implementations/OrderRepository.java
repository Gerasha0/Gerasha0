package com.example2.dal.repositories.implementations;

import com.example2.dal.entities.Order;
import com.example2.dal.entities.OrderStatus;
import com.example2.dal.repositories.interfaces.IOrderRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRepository extends GenericRepository<Order, Long> implements IOrderRepository {
    
    public OrderRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public List<Order> findByStatus(OrderStatus status) {
        String jpql = "SELECT o FROM Order o WHERE o.status = :status";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
    
    @Override
    public List<Order> findByCustomerName(String customerName) {
        String jpql = "SELECT o FROM Order o WHERE LOWER(o.customerName) LIKE LOWER(:customerName)";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("customerName", "%" + customerName + "%");
        return query.getResultList();
    }
    
    @Override
    public List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        String jpql = "SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
    
    @Override
    public List<Order> findByIsComplexLunch(Boolean isComplexLunch) {
        String jpql = "SELECT o FROM Order o WHERE o.isComplexLunch = :isComplexLunch";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("isComplexLunch", isComplexLunch);
        return query.getResultList();
    }
}
