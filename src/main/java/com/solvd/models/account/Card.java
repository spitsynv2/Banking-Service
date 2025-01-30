package com.solvd.models.account;

import com.solvd.models.account.enums.CardType;

import java.util.Date;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class Card {
    private Long id;
    private String cardNumber;
    private CardType cardType;
    private Date expiryDate;
    private String cvv;
    private Boolean isActive;

    public Card() {}

    public Card(Long id, String cardNumber, CardType cardType, Date expiryDate, String cvv, Boolean isActive) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType=" + cardType +
                ", expiryDate=" + expiryDate +
                ", cvv='" + cvv + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
