package com.solvd.banking_service.services.xml_communications_service.wrappers;

import com.solvd.banking_service.models.Appointment;
import com.solvd.banking_service.models.account.Card;
import com.solvd.banking_service.models.account.Deposit;
import com.solvd.banking_service.models.account.Loan;
import com.solvd.banking_service.models.account.Transaction;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
@XmlRootElement(name = "banking_service")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"transactions", "loans", "deposits", "cards", "appointments"})
public class BankingServiceWrapper {

    @XmlElementWrapper(name = "transactions")
    @XmlElement(name = "transaction")
    private List<Transaction> transactions;

    @XmlElementWrapper(name = "loans")
    @XmlElement(name = "loan")
    private List<Loan> loans;

    @XmlElementWrapper(name = "deposits")
    @XmlElement(name = "deposit")
    private List<Deposit> deposits;

    @XmlElementWrapper(name = "cards")
    @XmlElement(name = "card")
    private List<Card> cards;

    @XmlElementWrapper(name = "appointments")
    @XmlElement(name = "appointment")
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
