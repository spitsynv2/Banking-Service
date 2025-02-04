package com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers;

import com.solvd.bankingservice.models.account.Transaction;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
@XmlRootElement(name = "banking_service")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionsWrapper extends AbstractWrapper<Transaction> {

    @XmlElementWrapper(name = "transactions")
    @XmlElement(name = "transaction")
    private List<Transaction> transactionList = new ArrayList<>();

    public TransactionsWrapper(){}

    @Override
    public List<Transaction> getEntities() {
        return transactionList;
    }

    @Override
    public void setEntities(List<Transaction> entities) {
        this.transactionList.clear();
        this.transactionList.addAll(entities);
    }
}
