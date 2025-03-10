package com.solvd.bankingservice.service.xml;

import com.solvd.bankingservice.repo.impl.xml.wrappers.BankingWrapper;
import com.solvd.bankingservice.model.account.Card;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-06
 */
public interface IBankingProcessingService {
    void writeAllToFile (BankingWrapper banking, String outputFilePath);
    BankingWrapper readAllFromFile();
    List<Card> readAllCardsFromFile ();
}
