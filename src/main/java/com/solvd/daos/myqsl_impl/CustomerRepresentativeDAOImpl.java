package com.solvd.daos.myqsl_impl;

import com.solvd.daos.ICustomerRepresentativeDAO;
import com.solvd.models.customer.CustomerRepresentative;
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
public class CustomerRepresentativeDAOImpl extends MYSQLImpl<CustomerRepresentative, Long> implements ICustomerRepresentativeDAO {

    private static final Logger log = LogManager.getLogger(CustomerRepresentativeDAOImpl.class);

    public CustomerRepresentativeDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void createWithCompanyId(CustomerRepresentative representative,Long companyId) {
        String sql = "INSERT INTO " + getTableName() + " (company_id, first_name, last_name, email, phone_number, position, is_primary_contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, companyId);
            stmt.setString(2, representative.getFirstName());
            stmt.setString(3, representative.getLastName());
            stmt.setString(4, representative.getEmail());
            stmt.setString(5, representative.getPhoneNumber());
            stmt.setString(6, representative.getPosition());
            stmt.setInt(7, representative.isPrimaryContact() ? 1 : 0);
            stmt.executeUpdate();
            log.info("CustomerRepresentative was inserted/created successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(CustomerRepresentative entity) {
        throw new UnsupportedOperationException("Method not implemented in CustomerRepresentativeDAOImpl, Use --- createWithCompanyId");
    }

    @Override
    public void update(CustomerRepresentative representative) {
        String sql = "UPDATE " + getTableName() + " SET first_name = ?, last_name = ?, email = ?, phone_number = ?, position = ?, is_primary_contact = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, representative.getFirstName());
            stmt.setString(2, representative.getLastName());
            stmt.setString(3, representative.getEmail());
            stmt.setString(4, representative.getPhoneNumber());
            stmt.setString(5, representative.getPosition());
            stmt.setInt(6, representative.isPrimaryContact() ? 1 : 0);
            stmt.setLong(7, representative.getId());
            stmt.executeUpdate();
            log.info("CustomerRepresentative was updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(CustomerRepresentative customerRepresentative) {
        String sql = "DELETE FROM " + getTableName() + " WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, customerRepresentative.getId());
            stmt.executeUpdate();
            log.info("CustomerRepresentative was deleted from database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected CustomerRepresentative mapResultSetToEntity(ResultSet rs) {
        CustomerRepresentative customerRepresentative = new CustomerRepresentative();
        try {
            customerRepresentative.setId(rs.getLong("Id"));
            customerRepresentative.setFirstName(rs.getString("first_name"));
            customerRepresentative.setLastName(rs.getString("last_name"));
            customerRepresentative.setPosition(rs.getString("position"));
            customerRepresentative.setEmail(rs.getString("email"));
            customerRepresentative.setPhoneNumber(rs.getString("phone_number"));
            customerRepresentative.setPrimaryContact(rs.getBoolean("is_primary_contact"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerRepresentative;
    }

    @Override
    public boolean checkEmailExists(String cardNumber) {
        String sql = "SELECT COUNT(*) FROM " + getTableName() + " WHERE email = ?";
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
    protected String getForeignKeyColumnLabel(){
        return "company_id";
    }

    @Override
    protected String getTableName() {
        return "customer_representatives";
    }

}
