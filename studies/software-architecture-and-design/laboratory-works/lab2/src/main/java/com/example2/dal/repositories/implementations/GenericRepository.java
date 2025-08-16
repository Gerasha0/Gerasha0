package com.example2.dal.repositories.implementations;

import com.example2.dal.repositories.interfaces.IGenericRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class GenericRepository<T, K> implements IGenericRepository<T, K> {
    protected EntityManager entityManager;
    protected Class<T> entityClass;
    
    @SuppressWarnings("unchecked")
    protected GenericRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @Override
    public T save(T entity) {
        if (entityManager.contains(entity)) {
            return entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
            return entity;
        }
    }
    
    @Override
    public Optional<T> findById(K id) {
        T entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }
    
    @Override
    public List<T> findAll() {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
        return query.getResultList();
    }
    
    @Override
    public void delete(T entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            entityManager.remove(entityManager.merge(entity));
        }
    }
    
    @Override
    public void deleteById(K id) {
        Optional<T> entity = findById(id);
        entity.ifPresent(this::delete);
    }
    
    @Override
    public boolean existsById(K id) {
        return findById(id).isPresent();
    }
    
    @Override
    public long count() {
        String jpql = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        return query.getSingleResult();
    }
}
