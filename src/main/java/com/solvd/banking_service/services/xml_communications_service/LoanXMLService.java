package com.solvd.banking_service.services.xml_communications_service;

import com.solvd.banking_service.models.account.Loan;
import com.solvd.banking_service.services.xml_communications_service.wrappers.AbstractWrapper;
import com.solvd.banking_service.services.xml_communications_service.wrappers.LoanWrapper;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class LoanXMLService extends AbstractXMLService<Loan> {
    @Override
    protected Class<? extends AbstractWrapper<Loan>> getWrapperClass() {
        return LoanWrapper.class;
    }
}
