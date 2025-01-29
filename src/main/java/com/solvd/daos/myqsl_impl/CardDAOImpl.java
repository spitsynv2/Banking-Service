package com.solvd.daos.myqsl_impl;

import com.solvd.daos.ICardDAO;
import com.solvd.models.account.Card;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public class CardDAOImpl extends MYSQLImpl<Card,Long> implements ICardDAO {

    public CardDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Card> readAllByAccountId(Long AccountId) {
        return List.of();
    }

    @Override
    public void createWithAccountId(Card card, Long AccountId) {

    }

    @Override
    public void create(Card entity) {
        throw new UnsupportedOperationException("Method not implemented in CardDAOImpl");
    }

    @Override
    public void update(Card entity) {

    }

    @Override
    public void delete(Card entity) {
        throw new UnsupportedOperationException("Method not implemented in CardDAOImpl");
    }

    @Override
    protected String getTableName() {
        return "cards";
    }

    @Override
    protected Card mapResultSetToEntity(ResultSet rs) {
        return null;
    }
}
