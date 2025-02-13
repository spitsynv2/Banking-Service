package com.solvd.bankingservice.repo.impl.xml.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.bankingservice.model.Appointment;
import com.solvd.bankingservice.model.account.Card;
import com.solvd.bankingservice.model.account.Deposit;
import com.solvd.bankingservice.model.account.Loan;
import com.solvd.bankingservice.model.account.Transaction;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
@XmlRootElement(name = "banking_service")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"transactions", "loans", "deposits", "cards", "appointments"})
@JsonRootName("banking_service")
public class BankingWrapper {

    @XmlElementWrapper(name = "transactions")
    @XmlElement(name = "transaction")
    @JsonProperty("transactions")
    private List<Transaction> transactions;

    @XmlElementWrapper(name = "loans")
    @XmlElement(name = "loan")
    @JsonProperty("loans")
    private List<Loan> loans;

    @XmlElementWrapper(name = "deposits")
    @XmlElement(name = "deposit")
    @JsonProperty("deposits")
    private List<Deposit> deposits;

    @XmlElementWrapper(name = "cards")
    @XmlElement(name = "card")
    @JsonProperty("cards")
    private List<Card> cards;

    @XmlElementWrapper(name = "appointments")
    @XmlElement(name = "appointment")
    @JsonProperty("appointments")
    private List<Appointment> appointments;

    // Getters and Setters
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
    public List<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "BankingServiceWrapper {" + System.lineSeparator() +
                "transactions=" + transactions + System.lineSeparator() +
                "loans=" + loans + System.lineSeparator() +
                "deposits=" + deposits + System.lineSeparator() +
                "cards=" + cards + System.lineSeparator() +
                "appointments=" + appointments +
                '}';
    }
}
