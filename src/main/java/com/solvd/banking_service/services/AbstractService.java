package com.solvd.banking_service.services;

import com.solvd.banking_service.daos.IDAO;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public abstract class AbstractService<T, ID> implements IService<T, ID> {

    protected abstract IDAO<T, ID> getDao();

    @Override
    public T readById(ID id){
        return getDao().readById(id);
    }

    @Override
    public void create(T entity) {
        getDao().create(entity);
    }

    @Override
    public void update(T entity) {
        getDao().update(entity);
    }

    @Override
    public void deleteById(ID id) {
        getDao().deleteById(id);
    }

}