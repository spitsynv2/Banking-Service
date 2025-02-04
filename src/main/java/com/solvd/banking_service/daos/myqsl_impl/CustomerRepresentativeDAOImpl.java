package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.ICustomerRepresentativeDAO;
import com.solvd.banking_service.models.customer.CustomerRepresentative;
import com.solvd.banking_service.utils.database_connection.MySQLConnectionPool;
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

    private static final String CREATE_WITH_COMPANY_ID =
            "INSERT INTO " + "customer_representatives" + " (company_id, first_name, last_name, email, phone_number, " +
                    "position, is_primary_contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE " + "customer_representatives" + " SET first_name = ?, last_name = ?, " +
            "email = ?, phone_number = ?, position = ?, is_primary_contact = ? WHERE Id = ?";
    private static final String DELETE = "DELETE FROM " + "customer_representatives" + " WHERE Id = ?";
    private static final String CHECK_EMAIL_EXISTS  = "SELECT COUNT(*) FROM " + "customer_representatives" + " WHERE email = ?";


    @Override
    public void createWithCompanyId(CustomerRepresentative representative,Long companyId) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_COMPANY_ID)) {
                stmt.setLong(1, companyId);
                stmt.setString(2, representative.getFirstName());
                stmt.setString(3, representative.getLastName());
                stmt.setString(4, representative.getEmail());
                stmt.setString(5, representative.getPhoneNumber());
                stmt.setString(6, representative.getPosition());
                stmt.setInt(7, representative.isPrimaryContact() ? 1 : 0);
                stmt.executeUpdate();
                log.info("CustomerRepresentative was inserted/created successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void create(CustomerRepresentative entity) {
        log.error("Method is not implemented in CustomerRepresentativeDAOImpl, Use ---> createWithCompanyId");
    }

    @Override
    public void update(CustomerRepresentative representative) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setString(1, representative.getFirstName());
                stmt.setString(2, representative.getLastName());
                stmt.setString(3, representative.getEmail());
                stmt.setString(4, representative.getPhoneNumber());
                stmt.setString(5, representative.getPosition());
                stmt.setInt(6, representative.isPrimaryContact() ? 1 : 0);
                stmt.setLong(7, representative.getId());
                stmt.executeUpdate();
                log.info("CustomerRepresentative was updated successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(CustomerRepresentative customerRepresentative) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(DELETE)) {
                stmt.setLong(1, customerRepresentative.getId());
                stmt.executeUpdate();
                log.info("CustomerRepresentative was deleted from database.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public boolean checkEmailExists(String cardNumber) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(CHECK_EMAIL_EXISTS)) {
                statement.setString(1, cardNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
            return false;
        }finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }
        return false;
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
            log.error(e);
            return null;
        }
        return customerRepresentative;
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
