package com.solvd.bankingservice.models.customer;

import com.solvd.bankingservice.models.AuditLog;
import com.solvd.bankingservice.models.ServiceRequest;
import com.solvd.bankingservice.models.account.Account;

import java.util.Date;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class CompanyCustomer extends Customer {
    private String companyName;
    private String taxId;
    private String industry;
    private Date registrationDate;
    private List<CustomerRepresentative> representatives;

    public CompanyCustomer(){}

    public CompanyCustomer(Long id, String email, String phoneNumber, List<Address> addresses, List<Account> accounts, List<ServiceRequest> serviceRequests, List<AuditLog> auditLogs, String companyName, String taxId, String industry, Date registrationDate) {
        super(id, email, phoneNumber, addresses, accounts, serviceRequests, auditLogs);
        this.companyName = companyName;
        this.taxId = taxId;
        this.industry = industry;
        this.registrationDate = registrationDate;
    }

    public CompanyCustomer(Long id, String email, String phoneNumber, List<Address> addresses, List<Account> accounts, List<ServiceRequest> serviceRequests, List<AuditLog> auditLogs, String companyName, String taxId, String industry, Date registrationDate, List<CustomerRepresentative> representatives) {
        super(id, email, phoneNumber, addresses, accounts, serviceRequests, auditLogs);
        this.companyName = companyName;
        this.taxId = taxId;
        this.industry = industry;
        this.registrationDate = registrationDate;
        this.representatives = representatives;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<CustomerRepresentative> getRepresentatives() {
        return representatives;
    }

    public void setRepresentatives(List<CustomerRepresentative> representatives) {
        this.representatives = representatives;
    }

    @Override
    public String toString() {
        return "CompanyCustomer{" +
                "companyName='" + companyName + '\'' +
                ", taxId='" + taxId + '\'' +
                ", industry='" + industry + '\'' +
                ", registrationDate=" + registrationDate +
                ", representatives=" + representatives +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", addresses=" + addresses +
                ", accounts=" + accounts +
                ", serviceRequests=" + serviceRequests +
                ", auditLogs=" + auditLogs +
                '}';
    }
}
