package com.solvd.models.customer;

import com.solvd.models.account.Account;
import com.solvd.models.AuditLog;
import com.solvd.models.ServiceRequest;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public abstract class Customer {
    protected Long id;
    protected String email;
    protected String phoneNumber;
    protected List<Address> addresses;
    protected List<Account> accounts;
    protected List<ServiceRequest> serviceRequests;
    protected List<AuditLog> auditLogs;

    public Customer() {}

    public Customer(Long id, String email, String phoneNumber, List<Address> addresses, List<Account> accounts,
                    List<ServiceRequest> serviceRequests, List<AuditLog> auditLogs) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        this.accounts = accounts;
        this.serviceRequests = serviceRequests;
        this.auditLogs = auditLogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<ServiceRequest> getServiceRequests() {
        return serviceRequests;
    }

    public void setServiceRequests(List<ServiceRequest> serviceRequests) {
        this.serviceRequests = serviceRequests;
    }

    public List<AuditLog> getAuditLogs() {
        return auditLogs;
    }

    public void setAuditLogs(List<AuditLog> auditLogs) {
        this.auditLogs = auditLogs;
    }
}
