package com.solvd.bankingservice.daos.impl.xml.jaxb;

import com.solvd.bankingservice.models.account.Deposit;
import com.solvd.bankingservice.daos.impl.xml.wrappers.AbstractWrapper;
import com.solvd.bankingservice.daos.impl.xml.wrappers.DepositWrapper;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class DepositXMLImpl extends AbstractXMLImpl<Deposit> {

    @Override
    protected Class<? extends AbstractWrapper<Deposit>> getWrapperClass() {
        return DepositWrapper.class;
    }
}
