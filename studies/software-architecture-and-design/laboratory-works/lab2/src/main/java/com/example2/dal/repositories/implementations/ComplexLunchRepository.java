package com.example2.dal.repositories.implementations;

import com.example2.dal.entities.ComplexLunch;
import com.example2.dal.repositories.interfaces.IComplexLunchRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class ComplexLunchRepository extends GenericRepository<ComplexLunch, Long> implements IComplexLunchRepository {
    
    public ComplexLunchRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public List<ComplexLunch> findByAvailableDate(LocalDate date) {
        String jpql = "SELECT cl FROM ComplexLunch cl WHERE cl.availableDate = :date";
        TypedQuery<ComplexLunch> query = entityManager.createQuery(jpql, ComplexLunch.class);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @Override
    public List<ComplexLunch> findAvailableComplexLunches() {
        String jpql = "SELECT cl FROM ComplexLunch cl WHERE cl.isAvailable = true";
        TypedQuery<ComplexLunch> query = entityManager.createQuery(jpql, ComplexLunch.class);
        return query.getResultList();
    }
    
    @Override
    public List<ComplexLunch> findByNameContainingIgnoreCase(String name) {
        String jpql = "SELECT cl FROM ComplexLunch cl WHERE LOWER(cl.name) LIKE LOWER(:name)";
        TypedQuery<ComplexLunch> query = entityManager.createQuery(jpql, ComplexLunch.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
