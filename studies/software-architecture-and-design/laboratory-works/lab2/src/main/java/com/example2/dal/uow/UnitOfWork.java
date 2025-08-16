package com.example2.dal.uow;

import com.example2.dal.repositories.interfaces.IDishRepository;
import com.example2.dal.repositories.interfaces.IOrderRepository;
import com.example2.dal.repositories.interfaces.IComplexLunchRepository;
import com.example2.dal.repositories.implementations.DishRepository;
import com.example2.dal.repositories.implementations.OrderRepository;
import com.example2.dal.repositories.implementations.ComplexLunchRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class UnitOfWork implements IUnitOfWork {
    private EntityManager entityManager;
    private EntityTransaction transaction;
    
    private IDishRepository dishRepository;
    private IOrderRepository orderRepository;
    private IComplexLunchRepository complexLunchRepository;
    
    public UnitOfWork(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
        initializeRepositories();
    }
    
    private void initializeRepositories() {
        this.dishRepository = new DishRepository(entityManager);
        this.orderRepository = new OrderRepository(entityManager);
        this.complexLunchRepository = new ComplexLunchRepository(entityManager);
    }
    
    @Override
    public IDishRepository getDishRepository() {
        return dishRepository;
    }
    
    @Override
    public IOrderRepository getOrderRepository() {
        return orderRepository;
    }
    
    @Override
    public IComplexLunchRepository getComplexLunchRepository() {
        return complexLunchRepository;
    }
    
    @Override
    public void beginTransaction() {
        if (transaction == null || !transaction.isActive()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
        }
    }
    
    @Override
    public void commit() {
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
        }
    }
    
    @Override
    public void rollback() {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }
    
    @Override
    public void save() {
        if (transaction != null && transaction.isActive()) {
            entityManager.flush();
        }
    }
    
    @Override
    public void close() {
        if (transaction != null && transaction.isActive()) {
            rollback();
        }
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
