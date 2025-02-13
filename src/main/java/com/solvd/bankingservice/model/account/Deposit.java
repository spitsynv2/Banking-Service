package com.solvd.bankingservice.model.account;

import com.solvd.bankingservice.model.account.enums.deposit_enums.DepositStatus;
import com.solvd.bankingservice.model.account.enums.deposit_enums.DepositType;
import com.solvd.bankingservice.util.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Deposit {

    @XmlElement
    private Long id;

    @XmlElement
    private DepositType depositType;

    @XmlElement
    private Double amount;

    @XmlElement
    private Double interestRate;

    @XmlElement
    private Integer termMonths;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate maturityDate;

    @XmlElement
    private DepositStatus depositStatus;

    public Deposit() {}

    public Deposit(Long id, DepositType depositType, Double amount, Double interestRate, Integer termMonths,
                   LocalDate startDate, LocalDate maturityDate, DepositStatus depositStatus) {
        this.id = id;
        this.depositType = depositType;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.depositStatus = depositStatus;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public DepositType getDepositType() {
        return depositType;
    }
    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTermMonths() {
        return termMonths;
    }
    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }
    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public DepositStatus getDepositStatus() {
        return depositStatus;
    }
    public void setDepositStatus(DepositStatus depositStatus) {
        this.depositStatus = depositStatus;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", depositType=" + depositType +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", termMonths=" + termMonths +
                ", startDate=" + startDate +
                ", maturityDate=" + maturityDate +
                ", depositStatus=" + depositStatus +
                '}';
    }
}
