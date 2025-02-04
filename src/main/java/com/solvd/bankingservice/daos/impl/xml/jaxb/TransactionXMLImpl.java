package com.solvd.bankingservice.daos.impl.xml.jaxb;

import com.solvd.bankingservice.models.account.Transaction;
import com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers.AbstractWrapper;
import com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers.TransactionsWrapper;

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
