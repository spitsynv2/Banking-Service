package com.solvd.banking_service.services.xml_communications_service.wrappers;

import com.solvd.banking_service.models.account.Loan;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
@XmlRootElement(name = "banking_service")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoanWrapper extends AbstractWrapper<Loan> {

    @XmlElementWrapper(name = "loans")
    @XmlElement(name = "loan")
    private List<Loan> loanList = new ArrayList<>();

    public LoanWrapper() {}

    @Override
    public List<Loan> getEntities() {
        return loanList;
    }

    @Override
    public void setEntities(List<Loan> entities) {
        this.loanList.clear();
        this.loanList.addAll(entities);
    }
}
