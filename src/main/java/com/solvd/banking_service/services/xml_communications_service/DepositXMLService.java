package com.solvd.banking_service.services.xml_communications_service;

import com.solvd.banking_service.models.account.Deposit;
import com.solvd.banking_service.services.xml_communications_service.wrappers.AbstractWrapper;
import com.solvd.banking_service.services.xml_communications_service.wrappers.DepositWrapper;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class DepositXMLService extends AbstractXMLService<Deposit> {

    @Override
    protected Class<? extends AbstractWrapper<Deposit>> getWrapperClass() {
        return DepositWrapper.class;
    }
}
