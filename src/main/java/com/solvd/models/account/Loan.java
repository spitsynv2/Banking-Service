package com.solvd.models.account;

import com.solvd.models.account.enums.loan_enums.LoanStatus;
import com.solvd.models.account.enums.loan_enums.LoanType;

import java.util.Date;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class Loan {
    private Long id;
    private LoanType loanType;
    private double amount;
    private double interestRate;
    private int termMonths;
    private Date startDate;
    private Date paymentDate;
    private LoanStatus loanStatus;

    public Loan() {}

    public Loan(Long id, LoanType loanType, double amount, double interestRate, int termMonths, Date startDate, Date paymentDate, LoanStatus loanStatus) {
        this.id = id;
        this.loanType = loanType;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.startDate = startDate;
        this.paymentDate = paymentDate;
        this.loanStatus = loanStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }
}
