package com.example2.dal.uow;

import com.example2.dal.repositories.interfaces.IDishRepository;
import com.example2.dal.repositories.interfaces.IOrderRepository;
import com.example2.dal.repositories.interfaces.IComplexLunchRepository;

public interface IUnitOfWork extends AutoCloseable {
    IDishRepository getDishRepository();
    IOrderRepository getOrderRepository();
    IComplexLunchRepository getComplexLunchRepository();
    
    void beginTransaction();
    void commit();
    void rollback();
    void save();
    
    @Override
    void close();
}
