package com.solvd.bankingservice.repo;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-13
 */
public interface IDAOCommonUtility<T,ID> {
    List<T> readAllByForeignKeyId(ID id);
}
