package com.solvd.banking_service.services.xml_communications_service;

import com.solvd.banking_service.models.account.Transaction;
import com.solvd.banking_service.services.xml_communications_service.wrappers.AbstractWrapper;
import com.solvd.banking_service.services.xml_communications_service.wrappers.TransactionsWrapper;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class TransactionXMLService extends AbstractXMLService<Transaction>{
    @Override
    protected Class<? extends AbstractWrapper<Transaction>> getWrapperClass() {
        return TransactionsWrapper.class;
    }
}
