package com.example2.dal.repositories.implementations;

import com.example2.dal.entities.Dish;
import com.example2.dal.entities.DishType;
import com.example2.dal.repositories.interfaces.IDishRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class DishRepository extends GenericRepository<Dish, Long> implements IDishRepository {
    
    public DishRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public List<Dish> findByDishType(DishType dishType) {
        String jpql = "SELECT d FROM Dish d WHERE d.dishType = :dishType";
        TypedQuery<Dish> query = entityManager.createQuery(jpql, Dish.class);
        query.setParameter("dishType", dishType);
        return query.getResultList();
    }
    
    @Override
    public List<Dish> findByAvailableDate(LocalDate date) {
        String jpql = "SELECT d FROM Dish d WHERE d.availableDate = :date";
        TypedQuery<Dish> query = entityManager.createQuery(jpql, Dish.class);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @Override
    public List<Dish> findByDishTypeAndAvailableDate(DishType dishType, LocalDate date) {
        String jpql = "SELECT d FROM Dish d WHERE d.dishType = :dishType AND d.availableDate = :date";
        TypedQuery<Dish> query = entityManager.createQuery(jpql, Dish.class);
        query.setParameter("dishType", dishType);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @Override
    public List<Dish> findAvailableDishes() {
        String jpql = "SELECT d FROM Dish d WHERE d.isAvailable = true";
        TypedQuery<Dish> query = entityManager.createQuery(jpql, Dish.class);
        return query.getResultList();
    }
    
    @Override
    public List<Dish> findByNameContainingIgnoreCase(String name) {
        String jpql = "SELECT d FROM Dish d WHERE LOWER(d.name) LIKE LOWER(:name)";
        TypedQuery<Dish> query = entityManager.createQuery(jpql, Dish.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
    
    @Override
    public List<Dish> findByPriceBetween(Double minPrice, Double maxPrice) {
        String jpql = "SELECT d FROM Dish d WHERE d.price BETWEEN :minPrice AND :maxPrice";
        TypedQuery<Dish> query = entityManager.createQuery(jpql, Dish.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }
}
