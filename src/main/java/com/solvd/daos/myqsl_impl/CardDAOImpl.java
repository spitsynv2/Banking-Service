package com.solvd.daos.myqsl_impl;

import com.solvd.daos.ICardDAO;
import com.solvd.models.account.Card;
import com.solvd.models.account.enums.CardType;
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

    public CardDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void createWithAccountId(Card card, Long accountId) {
        String sql = "INSERT INTO " + getTableName() + " (account_id, card_number, card_type, expiry_date, cvv, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, accountId);
            stmt.setString(2, card.getCardNumber());
            stmt.setString(3, card.getCardType().toString().toUpperCase());
            stmt.setDate(4, new java.sql.Date(card.getExpiryDate().getTime()));
            stmt.setString(5, card.getCvv());
            stmt.setInt(6, card.isActive() ? 1 : 0);
            stmt.executeUpdate();
            log.info("Address was created/inserted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Card entity) {
        throw new UnsupportedOperationException("Method not implemented in CardDAOImpl, Use --- createWithAccountId");
    }

    @Override
    public void update(Card card) {
        String sql = "UPDATE " + getTableName() + " SET card_number = ?, card_type = ?, expiry_date = ?, cvv = ?, is_active = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, card.getCardNumber());
            stmt.setString(2, card.getCardType().toString().toUpperCase());
            stmt.setDate(3, new java.sql.Date(card.getExpiryDate().getTime()));
            stmt.setString(4, card.getCvv());
            stmt.setInt(5, card.isActive() ? 1 : 0);
            stmt.setLong(6,card.getId());
            stmt.executeUpdate();
            log.info("Address was updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Card entity) {
        throw new UnsupportedOperationException("Method not implemented in CardDAOImpl, Use --- deleteById(ID id)");
    }

    @Override
    public boolean checkCardNumberExists(String cardNumber) {
        String sql = "SELECT COUNT(*) FROM " + getTableName() + " WHERE card_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cardNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
        return card;
    }
}
