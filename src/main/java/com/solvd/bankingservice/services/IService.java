package com.solvd.bankingservice.services;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IService <T, ID> {
    T readById(ID id);
    void deleteById(ID id);
    void create(T entity);
    void update(T entity);
}
