package com.solvd.daos.myqsl_impl;

import com.solvd.daos.IDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MYSQLImpl<T, ID> implements IDAO<T, ID> {

    private static final Logger log = LogManager.getLogger(MYSQLImpl.class);
    protected Connection connection;

    public MYSQLImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T readById(ID id) {
        T entity = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                entity = mapResultSetToEntity(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        log.info("Entity: {} was successfully read from database", entity);
        return entity;
    }

    public List<T> readAllByForeignKeyId(Long foreignKeyId) {
        List<T> tList = new ArrayList<>();

        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getForeignKeyColumnLabel() + " = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, foreignKeyId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tList.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!tList.isEmpty()) {
            log.info("{}List: {} were successfully readAllByIdentifier from database table {}",
                    tList.getFirst().getClass().getSimpleName(), tList, getTableName());
        } else {
            log.info("No records found in database table {}", getTableName());
        }
        return tList;
    }

    @Override
    public void deleteById(ID id){
        String sql = "DELETE FROM "+ getTableName() + " WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        log.info("Entity with id: {} was deleted from database table: {}", id, getTableName());
    }

    @Override
    public abstract void create(T entity);

    @Override
    public abstract void update(T entity);

    @Override
    public abstract void delete(T entity);

    protected abstract String getTableName();

    protected abstract String getForeignKeyColumnLabel();

    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
}
