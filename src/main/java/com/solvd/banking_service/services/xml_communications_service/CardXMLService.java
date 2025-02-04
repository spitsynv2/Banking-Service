package com.solvd.banking_service.services.xml_communications_service;

import com.solvd.banking_service.models.account.Card;
import com.solvd.banking_service.services.xml_communications_service.wrappers.AbstractWrapper;
import com.solvd.banking_service.services.xml_communications_service.wrappers.CardsWrapper;

/**
  * @author Vadym Spitsyn
  * @created 2025-02-04
*/
public class CardXMLService extends AbstractXMLService<Card> {
    @Override
    protected Class<? extends AbstractWrapper<Card>> getWrapperClass() {
        return CardsWrapper.class;
    }
}
