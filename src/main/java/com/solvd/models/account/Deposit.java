package com.solvd.models.account;

import com.solvd.models.account.enums.deposit_enums.DepositStatus;
import com.solvd.models.account.enums.deposit_enums.DepositType;

import java.util.Date;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class Deposit {
    private Long id;
    private DepositType depositType;
    private double amount;
    private double interestRate;
    private int termMonths;
    private Date startDate;
    private Date maturityDate;
    private DepositStatus depositStatus;

    public Deposit() {
    }

    public Deposit(Long id, DepositType depositType, double amount, double interestRate, int termMonths,
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
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
}
