package com.solvd.bankingservice.daos.impl.xml.jaxb;

import com.solvd.bankingservice.models.account.Card;
import com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers.AbstractWrapper;
import com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers.CardsWrapper;

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
