package com.solvd.bankingservice.service;

import com.solvd.bankingservice.repo.IDAO;

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