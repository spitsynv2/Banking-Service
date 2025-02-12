package com.solvd.bankingservice.repo.impl.xml.jaxb;

import com.solvd.bankingservice.models.account.Card;
import com.solvd.bankingservice.repo.impl.xml.wrappers.AbstractWrapper;
import com.solvd.bankingservice.repo.impl.xml.wrappers.CardsWrapper;

/**
  * @author Vadym Spitsyn
  * @created 2025-02-04
*/
public class CardXMLImpl extends AbstractXMLImpl<Card> {
    @Override
    protected Class<? extends AbstractWrapper<Card>> getWrapperClass() {
        return CardsWrapper.class;
    }
}
