package com.solvd.bankingservice.models.account;

import com.solvd.bankingservice.models.account.enums.CardType;
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
public class Card {

    @XmlElement
    private Long id;

    @XmlElement
    private String cardNumber;

    @XmlElement(name = "cardType")
    private CardType cardType;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate expiryDate;

    @XmlElement
    private String cvv;

    @XmlElement
    private Boolean isActive;

    public Card() {}

    public Card(Long id, String cardNumber, CardType cardType, LocalDate expiryDate, String cvv, Boolean isActive) {
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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
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
