package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.ICardDAO;
import com.solvd.banking_service.models.account.Card;
import com.solvd.banking_service.models.account.enums.CardType;
import com.solvd.banking_service.services.database_connection.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public class CardDAOImpl extends MYSQLImpl<Card,Long> implements ICardDAO {

    private static final Logger log = LogManager.getLogger(CardDAOImpl.class);

    private static final String CREATE_WITH_ACCOUNT_ID =
            "INSERT INTO " + "cards"+ " (account_id, card_number, card_type, expiry_date, cvv, is_active) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE " + "cards" + " SET card_number = ?, card_type = ?, expiry_date = ?, cvv = ?, is_active = ? WHERE Id = ?";
    private static final String CHECK_CARD_NUMBER_EXISTS =
            "SELECT COUNT(*) FROM " + "cards" + " WHERE card_number = ?";

    @Override
    public void createWithAccountId(Card card, Long accountId) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_ACCOUNT_ID)) {
                stmt.setLong(1, accountId);
                stmt.setString(2, card.getCardNumber());
                stmt.setString(3, card.getCardType().toString().toUpperCase());
                stmt.setDate(4, new java.sql.Date(card.getExpiryDate().getTime()));
                stmt.setString(5, card.getCvv());
                stmt.setInt(6, card.isActive() ? 1 : 0);
                stmt.executeUpdate();
                log.info("Card was created/inserted successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void create(Card entity) {
        log.error("Method is not implemented in CardDAOImpl, Use ---> createWithAccountId");
    }

    @Override
    public void update(Card card) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setString(1, card.getCardNumber());
                stmt.setString(2, card.getCardType().toString().toUpperCase());
                stmt.setDate(3, new java.sql.Date(card.getExpiryDate().getTime()));
                stmt.setString(4, card.getCvv());
                stmt.setInt(5, card.isActive() ? 1 : 0);
                stmt.setLong(6,card.getId());
                stmt.executeUpdate();
                log.info("Card was updated successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(Card entity) {
        log.error("Method is not implemented in CardDAOImpl, Use ---> deleteById(ID id)");
    }

    @Override
    public boolean checkCardNumberExists(String cardNumber) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(CHECK_CARD_NUMBER_EXISTS)) {
                statement.setString(1, cardNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
        return false;
    }

    @Override
    protected String getTableName() {
        return "cards";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "account_id";
    }

    @Override
    protected Card mapResultSetToEntity(ResultSet rs) {
        Card card = new Card();
        try {
            card.setId(rs.getLong("Id"));
            card.setCardNumber(rs.getString("card_number"));
            card.setExpiryDate(rs.getDate("expiry_date"));
            card.setCvv(rs.getString("cvv"));
            card.setActive(rs.getBoolean("is_active"));

            CardType cardType = CardType.valueOf(rs.getString("card_type").toUpperCase());
            card.setCardType(cardType);
        } catch (IllegalArgumentException | SQLException e) {
            log.error(e);
            return null;
        }
        return card;
    }
}
