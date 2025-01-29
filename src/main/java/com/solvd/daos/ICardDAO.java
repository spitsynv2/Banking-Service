package com.solvd.daos;

import com.solvd.models.account.Card;


/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public interface ICardDAO extends IDAO<Card,Long> {
    void createWithAccountId(Card card, Long accountId);
    boolean checkCardNumberExists(String cardNumber);
}
