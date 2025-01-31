package com.solvd.banking_service.models.customer;

import com.solvd.banking_service.models.AuditLog;
import com.solvd.banking_service.models.ServiceRequest;
import com.solvd.banking_service.models.account.Account;

import java.util.Date;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class IndividualCustomer extends Customer {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public IndividualCustomer(){}

    public IndividualCustomer(Long id, String email, String phoneNumber, List<Address> addresses, List<Account> accounts, List<ServiceRequest> serviceRequests, List<AuditLog> auditLogs, String firstName, String lastName, Date dateOfBirth) {
        super(id, email, phoneNumber, addresses, accounts, serviceRequests, auditLogs);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "IndividualCustomer{" +
                "dateOfBirth=" + dateOfBirth +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", auditLogs=" + auditLogs +
                ", serviceRequests=" + serviceRequests +
                ", accounts=" + accounts +
                ", addresses=" + addresses +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
