package com.solvd.banking_service.models.account;

import com.solvd.banking_service.models.account.enums.deposit_enums.DepositStatus;
import com.solvd.banking_service.models.account.enums.deposit_enums.DepositType;

import java.util.Date;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class Deposit {
    private Long id;
    private DepositType depositType;
    private Double amount;
    private Double interestRate;
    private Integer termMonths;
    private Date startDate;
    private Date maturityDate;
    private DepositStatus depositStatus;

    public Deposit() {
    }

    public Deposit(Long id, DepositType depositType, Double amount, Double interestRate, Integer termMonths,
                   Date startDate, Date maturityDate, DepositStatus depositStatus) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
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
