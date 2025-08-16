package com.example2.bll.services.implementations;

import com.example2.bll.dto.DishDto;
import com.example2.bll.mappers.DishMapper;
import com.example2.bll.services.interfaces.IDishService;
import com.example2.dal.entities.Dish;
import com.example2.dal.entities.DishType;
import com.example2.dal.uow.IUnitOfWork;
import com.example2.dal.config.DatabaseConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DishService implements IDishService {
    
    @Override
    public DishDto createDish(DishDto dishDto) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Dish dish = DishMapper.toEntity(dishDto);
            Dish savedDish = unitOfWork.getDishRepository().save(dish);
            
            unitOfWork.commit();
            return DishMapper.toDto(savedDish);
        }
    }
    
    @Override
    public Optional<DishDto> getDishById(Long id) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            Optional<Dish> dish = unitOfWork.getDishRepository().findById(id);
            return dish.map(DishMapper::toDto);
        }
    }
    
    @Override
    public List<DishDto> getAllDishes() {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Dish> dishes = unitOfWork.getDishRepository().findAll();
            return DishMapper.toDtoList(dishes);
        }
    }
    
    @Override
    public List<DishDto> getDishesByType(DishType dishType) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Dish> dishes = unitOfWork.getDishRepository().findByDishType(dishType);
            return DishMapper.toDtoList(dishes);
        }
    }
    
    @Override
    public List<DishDto> getDishesByDate(LocalDate date) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Dish> dishes = unitOfWork.getDishRepository().findByAvailableDate(date);
            return DishMapper.toDtoList(dishes);
        }
    }
    
    @Override
    public List<DishDto> getDishesByTypeAndDate(DishType dishType, LocalDate date) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Dish> dishes = unitOfWork.getDishRepository().findByDishTypeAndAvailableDate(dishType, date);
            return DishMapper.toDtoList(dishes);
        }
    }
    
    @Override
    public List<DishDto> getAvailableDishes() {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Dish> dishes = unitOfWork.getDishRepository().findAvailableDishes();
            return DishMapper.toDtoList(dishes);
        }
    }
    
    @Override
    public List<DishDto> searchDishesByName(String name) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Dish> dishes = unitOfWork.getDishRepository().findByNameContainingIgnoreCase(name);
            return DishMapper.toDtoList(dishes);
        }
    }
    
    @Override
    public List<DishDto> getDishesByPriceRange(Double minPrice, Double maxPrice) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Dish> dishes = unitOfWork.getDishRepository().findByPriceBetween(minPrice, maxPrice);
            return DishMapper.toDtoList(dishes);
        }
    }
    
    @Override
    public DishDto updateDish(DishDto dishDto) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Dish dish = DishMapper.toEntity(dishDto);
            Dish updatedDish = unitOfWork.getDishRepository().save(dish);
            
            unitOfWork.commit();
            return DishMapper.toDto(updatedDish);
        }
    }
    
    @Override
    public void deleteDish(Long id) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            unitOfWork.getDishRepository().deleteById(id);
            unitOfWork.commit();
        }
    }
    
    @Override
    public void setDishAvailability(Long id, Boolean isAvailable) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Optional<Dish> dishOpt = unitOfWork.getDishRepository().findById(id);
            if (dishOpt.isPresent()) {
                Dish dish = dishOpt.get();
                dish.setIsAvailable(isAvailable);
                unitOfWork.getDishRepository().save(dish);
                unitOfWork.commit();
            }
        }
    }
}
