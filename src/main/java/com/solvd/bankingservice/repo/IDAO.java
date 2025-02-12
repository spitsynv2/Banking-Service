package com.solvd.bankingservice.repo;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-28
 */
public interface IDAO<T, ID> {
    T readById(ID id);
    void deleteById(ID id);
    void create(T entity);
    void update(T entity);
}
