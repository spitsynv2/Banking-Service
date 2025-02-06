package com.solvd.bankingservice.services.xml;

import com.solvd.bankingservice.daos.impl.xml.wrappers.BankingServiceWrapper;
import com.solvd.bankingservice.models.account.Card;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-06
 */
public interface IBankingProcessingService {
    void writeAllToXML (BankingServiceWrapper banking, String outputFilePath);
    BankingServiceWrapper readAllFromXml ();
    List<Card> readAllCardsFromXml ();
}
