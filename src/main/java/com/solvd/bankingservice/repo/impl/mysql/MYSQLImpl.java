package com.solvd.bankingservice.repo.impl.mysql;

import com.solvd.bankingservice.repo.IDAO;
import com.solvd.bankingservice.utils.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MYSQLImpl<T, ID> implements IDAO<T, ID> {

    private static final Logger log = LogManager.getLogger(MYSQLImpl.class);

    @Override
    public T readById(ID id) {
        Connection connection = null;
        T entity = null;
        String READ_BY_ID = "SELECT * FROM " + getTableName() + " WHERE Id = ?";

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(READ_BY_ID)) {
                stmt.setObject(1, id);
                try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {
                        entity = mapResultSetToEntity(rs);
                    }
                }
            }
        }catch (SQLException | InterruptedException e) {
            log.error("Error in reading by id: {}, from table: {}", id, getTableName(), e);
            return null;
        }finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        log.info("Entity: {} was successfully read from database", entity);
        return entity;
    }

    public List<T> readAllByForeignKeyId(ID foreignKeyId) {
        Connection connection = null;
        List<T> tList = new ArrayList<>();
        String READ_ALL_BY_FOREIGN_KEY_ID = "SELECT * FROM " + getTableName() + " WHERE " + getForeignKeyColumnLabel() + " = ?";

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(READ_ALL_BY_FOREIGN_KEY_ID)) {
                stmt.setObject(1, foreignKeyId);
                try (ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        tList.add(mapResultSetToEntity(rs));
                    }
                }
            }
        }catch (SQLException | InterruptedException e) {
            log.error("Error in reading All by foreignKeyId: {}, from table: {}, from column: {}", foreignKeyId, getTableName(), getForeignKeyColumnLabel(), e);
            return null;
        }finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        if (!tList.isEmpty()) {
            log.info("{}List: {} were successfully readAllByIdentifier from database table: {}",
                    tList.getFirst().getClass().getSimpleName(), tList, getTableName());
        }else {
            log.info("No records found in database table: {}", getTableName());
        }
        return tList;
    }

    @Override
    public void deleteById(ID id){
        Connection connection = null;
        String DELETE_BY_ID = "DELETE FROM "+ getTableName() + " WHERE Id = ?";

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID)) {
                stmt.setObject(1, id);
                stmt.executeUpdate();
            }
        }catch (SQLException | InterruptedException e) {
            log.error("Error in deleting by id {}, from table: {}",id,getTableName(), e);
        }finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        log.info("Entity with id: {} was deleted from database table: {}", id, getTableName());
    }

    @Override
    public abstract void create(T entity);

    @Override
    public abstract void update(T entity);

    protected abstract String getTableName();

    protected abstract String getForeignKeyColumnLabel();

    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
}
