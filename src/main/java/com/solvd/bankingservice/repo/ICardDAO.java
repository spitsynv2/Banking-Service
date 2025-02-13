package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.account.Card;

import java.util.List;


/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public interface ICardDAO extends IDAO<Card,Long> {
    void createWithAccountId(Card card, Long accountId);
    boolean checkCardNumberExists(String cardNumber);
    List<Card> readAllByForeignKeyId(Long id);
}
