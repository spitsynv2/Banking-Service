package com.solvd.bankingservice.repo.impl.xml.jaxb;

import com.solvd.bankingservice.model.account.Transaction;
import com.solvd.bankingservice.repo.impl.xml.wrappers.AbstractWrapper;
import com.solvd.bankingservice.repo.impl.xml.wrappers.TransactionsWrapper;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class TransactionXMLImpl extends AbstractXMLImpl<Transaction> {
    @Override
    protected Class<? extends AbstractWrapper<Transaction>> getWrapperClass() {
        return TransactionsWrapper.class;
    }
}
