package com.solvd.bankingservice.models.account;

import com.solvd.bankingservice.models.account.enums.account_enums.AccountStatus;
import com.solvd.bankingservice.models.account.enums.account_enums.AccountType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class Account {
    private Long id;
    private AccountType accountType;
    private Double balance;
    private String currency;
    private LocalDateTime openedDate;
    private AccountStatus accountStatus;
    private List<Transaction> transactions;
    private List<Loan> loans;
    private List<Deposit> deposits;
    private List<Card> cards;

    public Account(){}

    public Account(Long id, AccountType accountType, Double balance, String currency, LocalDateTime openedDate, AccountStatus accountStatus) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.openedDate = openedDate;
        this.accountStatus = accountStatus;
    }

    public Account(Long id, AccountType accountType, Double balance, String currency, LocalDateTime openedDate,
                   AccountStatus accountStatus, List<Transaction> transactions, List<Loan> loans, List<Deposit> deposits, List<Card> cards) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.openedDate = openedDate;
        this.accountStatus = accountStatus;
        this.transactions = transactions;
        this.loans = loans;
        this.deposits = deposits;
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(LocalDateTime openedDate) {
        this.openedDate = openedDate;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", openedDate=" + openedDate +
                ", accountStatus=" + accountStatus +
                ", transactions=" + transactions +
                ", loans=" + loans +
                ", deposits=" + deposits +
                ", cards=" + cards +
                '}';
    }
}
