package com.solvd.models.customer;

import com.solvd.models.AuditLog;
import com.solvd.models.ServiceRequest;
import com.solvd.models.account.Account;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class CompanyCustomer extends Customer {
    private String companyName;
    private String taxId;
    private String industry;
    private List<CustomerRepresentative> representatives;

    public CompanyCustomer(){}

    public CompanyCustomer(Long id, String email, String phoneNumber, List<Address> addresses, List<Account> accounts,
                           List<ServiceRequest> serviceRequests, List<AuditLog> auditLogs, String companyName, String taxId, String industry) {
        super(id, email, phoneNumber, addresses, accounts, serviceRequests, auditLogs);
        this.companyName = companyName;
        this.taxId = taxId;
        this.industry = industry;
    }

    public CompanyCustomer(Long id, String email, String phoneNumber, List<Address> addresses, List<Account> accounts,
                           List<ServiceRequest> serviceRequests, List<AuditLog> auditLogs, String companyName, String taxId, String industry, List<CustomerRepresentative> representatives) {
        super(id, email, phoneNumber, addresses, accounts, serviceRequests, auditLogs);
        this.companyName = companyName;
        this.taxId = taxId;
        this.industry = industry;
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

    public List<CustomerRepresentative> getRepresentatives() {
        return representatives;
    }

    public void setRepresentatives(List<CustomerRepresentative> representatives) {
        this.representatives = representatives;
    }
}
