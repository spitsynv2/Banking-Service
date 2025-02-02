package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.IAddressDAO;
import com.solvd.banking_service.models.customer.Address;
import com.solvd.banking_service.models.customer.enums.AddressType;
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
public class AddressDAOImpl extends MYSQLImpl<Address,Long> implements IAddressDAO {

    private static final Logger log = LogManager.getLogger(AddressDAOImpl.class);

    public AddressDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void createWithCustomerId(Address address, Long customerId) {
        String sql = "INSERT INTO " + getTableName() + " (customer_id, type, street, city, country, postal_code) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, customerId);
            stmt.setString(2, address.getAddressType().toString().toUpperCase());
            stmt.setString(3, address.getStreet());
            stmt.setString(4, address.getCity());
            stmt.setString(5, address.getCountry());
            stmt.setString(6, address.getPostalCode());
            stmt.executeUpdate();
            log.info("Address was created/inserted successfully.");
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void create(Address entity) {
        throw new UnsupportedOperationException("Method not implemented in AddressDAOImpl, Use --- createWithCustomerId");
    }

    @Override
    public void update(Address address) {
        String sql = "UPDATE " + getTableName() + " SET type = ?, street = ?, city = ?, country = ?, postal_code = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, address.getAddressType().toString().toUpperCase());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getCountry());
            stmt.setString(5, address.getPostalCode());
            stmt.setLong(6, address.getId());
            stmt.executeUpdate();
            log.info("Address was updated successfully.");
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Address entity) {
        throw new UnsupportedOperationException("Method not implemented in AddressDAOImpl, Use --- deleteById(ID id)");
    }

    @Override
    protected String getTableName() {
        return "addresses";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "customer_id";
    }

    @Override
    protected Address mapResultSetToEntity(ResultSet rs) {
        Address address = new Address();
        try {
            address.setId(rs.getLong("Id"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setCountry(rs.getString("country"));
            address.setPostalCode(rs.getString("postal_code"));

            AddressType addressType = AddressType.valueOf(rs.getString("type").toUpperCase());
            address.setAddressType(addressType);
        } catch (IllegalArgumentException | SQLException e) {
            log.error(e);
            return null;
        }
        return address;
    }

}
