package com.solvd.banking_service.models.account;

import com.solvd.banking_service.models.account.enums.transaction_enums.TransactionStatus;
import com.solvd.banking_service.models.account.enums.transaction_enums.TransactionType;

import java.time.LocalDateTime;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class Transaction {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private TransactionType transactionType;
    private Double amount;
    private LocalDateTime transactionDate;
    private String description;
    private TransactionStatus transactionStatus;

    public Transaction() {
    }

    public Transaction(Long id, Long fromAccountId, Long toAccountId, TransactionType transactionType,
                       Double amount, LocalDateTime transactionDate, String description, TransactionStatus transactionStatus) {
        this.id = id;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.description = description;
        this.transactionStatus = transactionStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", description='" + description + '\'' +
                ", transactionStatus=" + transactionStatus +
                '}';
    }
}
