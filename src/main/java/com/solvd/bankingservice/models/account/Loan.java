package com.solvd.bankingservice.models.account;

import com.solvd.bankingservice.models.account.enums.loan_enums.LoanStatus;
import com.solvd.bankingservice.models.account.enums.loan_enums.LoanType;
import com.solvd.bankingservice.utils.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Loan {

    @XmlElement
    private Long id;

    @XmlElement
    private LoanType loanType;

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
    private LocalDate paymentDate;

    @XmlElement
    private LoanStatus loanStatus;

    public Loan() {}

    public Loan(Long id, LoanType loanType, Double amount, Double interestRate, Integer termMonths, LocalDate startDate, LocalDate paymentDate, LoanStatus loanStatus) {
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }
    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loanType=" + loanType +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", termMonths=" + termMonths +
                ", startDate=" + startDate +
                ", paymentDate=" + paymentDate +
                ", loanStatus=" + loanStatus +
                '}';
    }
}
