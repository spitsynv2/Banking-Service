package com.solvd.bankingservice.daos.impl.xml.jaxb;

import com.solvd.bankingservice.models.account.Loan;
import com.solvd.bankingservice.daos.impl.xml.wrappers.AbstractWrapper;
import com.solvd.bankingservice.daos.impl.xml.wrappers.LoanWrapper;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class LoanXMLImpl extends AbstractXMLImpl<Loan> {
    @Override
    protected Class<? extends AbstractWrapper<Loan>> getWrapperClass() {
        return LoanWrapper.class;
    }
}
