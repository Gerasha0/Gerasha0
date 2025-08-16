package com.example2.bll.services.implementations;

import com.example2.bll.dto.ComplexLunchDto;
import com.example2.bll.mappers.ComplexLunchMapper;
import com.example2.bll.services.interfaces.IComplexLunchService;
import com.example2.dal.entities.ComplexLunch;
import com.example2.dal.entities.Dish;
import com.example2.dal.uow.IUnitOfWork;
import com.example2.dal.config.DatabaseConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ComplexLunchService implements IComplexLunchService {
    
    @Override
    public ComplexLunchDto createComplexLunch(ComplexLunchDto complexLunchDto) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            ComplexLunch complexLunch = ComplexLunchMapper.toEntity(complexLunchDto);
            ComplexLunch savedComplexLunch = unitOfWork.getComplexLunchRepository().save(complexLunch);
            
            unitOfWork.commit();
            return ComplexLunchMapper.toDto(savedComplexLunch);
        }
    }
    
    @Override
    public Optional<ComplexLunchDto> getComplexLunchById(Long id) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            Optional<ComplexLunch> complexLunch = unitOfWork.getComplexLunchRepository().findById(id);
            return complexLunch.map(ComplexLunchMapper::toDto);
        }
    }
    
    @Override
    public List<ComplexLunchDto> getAllComplexLunches() {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<ComplexLunch> complexLunches = unitOfWork.getComplexLunchRepository().findAll();
            return ComplexLunchMapper.toDtoList(complexLunches);
        }
    }
    
    @Override
    public List<ComplexLunchDto> getComplexLunchesByDate(LocalDate date) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<ComplexLunch> complexLunches = unitOfWork.getComplexLunchRepository().findByAvailableDate(date);
            return ComplexLunchMapper.toDtoList(complexLunches);
        }
    }
    
    @Override
    public List<ComplexLunchDto> getAvailableComplexLunches() {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<ComplexLunch> complexLunches = unitOfWork.getComplexLunchRepository().findAvailableComplexLunches();
            return ComplexLunchMapper.toDtoList(complexLunches);
        }
    }
    
    @Override
    public List<ComplexLunchDto> searchComplexLunchesByName(String name) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<ComplexLunch> complexLunches = unitOfWork.getComplexLunchRepository().findByNameContainingIgnoreCase(name);
            return ComplexLunchMapper.toDtoList(complexLunches);
        }
    }
    
    @Override
    public ComplexLunchDto addDishToComplexLunch(Long complexLunchId, Long dishId) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Optional<ComplexLunch> complexLunchOpt = unitOfWork.getComplexLunchRepository().findById(complexLunchId);
            Optional<Dish> dishOpt = unitOfWork.getDishRepository().findById(dishId);
            
            if (!complexLunchOpt.isPresent()) {
                throw new IllegalArgumentException("Complex lunch not found with id: " + complexLunchId);
            }
            if (!dishOpt.isPresent()) {
                throw new IllegalArgumentException("Dish not found with id: " + dishId);
            }
            
            ComplexLunch complexLunch = complexLunchOpt.get();
            Dish dish = dishOpt.get();
            
            complexLunch.addDish(dish);
            ComplexLunch savedComplexLunch = unitOfWork.getComplexLunchRepository().save(complexLunch);
            
            unitOfWork.commit();
            return ComplexLunchMapper.toDto(savedComplexLunch);
        }
    }
    
    @Override
    public ComplexLunchDto removeDishFromComplexLunch(Long complexLunchId, Long dishId) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Optional<ComplexLunch> complexLunchOpt = unitOfWork.getComplexLunchRepository().findById(complexLunchId);
            if (!complexLunchOpt.isPresent()) {
                throw new IllegalArgumentException("Complex lunch not found with id: " + complexLunchId);
            }
            
            ComplexLunch complexLunch = complexLunchOpt.get();
            complexLunch.getDishes().removeIf(dish -> dish.getId().equals(dishId));
            
            ComplexLunch savedComplexLunch = unitOfWork.getComplexLunchRepository().save(complexLunch);
            
            unitOfWork.commit();
            return ComplexLunchMapper.toDto(savedComplexLunch);
        }
    }
    
    @Override
    public ComplexLunchDto updateComplexLunch(ComplexLunchDto complexLunchDto) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            ComplexLunch complexLunch = ComplexLunchMapper.toEntity(complexLunchDto);
            ComplexLunch updatedComplexLunch = unitOfWork.getComplexLunchRepository().save(complexLunch);
            
            unitOfWork.commit();
            return ComplexLunchMapper.toDto(updatedComplexLunch);
        }
    }
    
    @Override
    public void deleteComplexLunch(Long id) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            unitOfWork.getComplexLunchRepository().deleteById(id);
            unitOfWork.commit();
        }
    }
    
    @Override
    public void setComplexLunchAvailability(Long id, Boolean isAvailable) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Optional<ComplexLunch> complexLunchOpt = unitOfWork.getComplexLunchRepository().findById(id);
            if (complexLunchOpt.isPresent()) {
                ComplexLunch complexLunch = complexLunchOpt.get();
                complexLunch.setIsAvailable(isAvailable);
                unitOfWork.getComplexLunchRepository().save(complexLunch);
                unitOfWork.commit();
            }
        }
    }
}
