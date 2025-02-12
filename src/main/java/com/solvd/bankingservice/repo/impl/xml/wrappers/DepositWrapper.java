package com.solvd.bankingservice.repo.impl.xml.wrappers;

import com.solvd.bankingservice.models.account.Deposit;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
@XmlRootElement(name = "banking_service")
@XmlAccessorType(XmlAccessType.FIELD)
public class DepositWrapper extends AbstractWrapper<Deposit> {

    @XmlElementWrapper(name = "deposits")
    @XmlElement(name = "deposit")
    private List<Deposit> depositList = new ArrayList<>();

    public DepositWrapper() {}

    @Override
    public List<Deposit> getEntities() {
        return depositList;
    }

    @Override
    public void setEntities(List<Deposit> entities) {
        this.depositList.clear();
        this.depositList.addAll(entities);
    }

}
