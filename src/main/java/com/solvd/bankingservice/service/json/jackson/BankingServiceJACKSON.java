package com.solvd.bankingservice.service.json.jackson;

import com.solvd.bankingservice.repo.impl.json.jackson.BankingJSONImpl;
import com.solvd.bankingservice.repo.impl.xml.wrappers.BankingWrapper;
import com.solvd.bankingservice.model.account.Card;
import com.solvd.bankingservice.service.xml.IBankingProcessingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-06
 */
public class BankingServiceJACKSON implements IBankingProcessingService {

    private static final Logger log = LogManager.getLogger(BankingServiceJACKSON.class);
    private static final BankingJSONImpl bankingJsonImpl = new BankingJSONImpl();

    @Override
    public void writeAllToFile(BankingWrapper banking, String outputFilePath) {
        log.info("(Part of) Banking_service was successfully written to JSON file.");
        bankingJsonImpl.writeAllToJson(banking,outputFilePath);
    }

    @Override
    public BankingWrapper readAllFromFile() {
        log.info("Reading all from JSON file.");
        return bankingJsonImpl.readAllFromJson();
    }

    @Override
    public List<Card> readAllCardsFromFile() {
        BankingWrapper wrapper = bankingJsonImpl.readAllFromJson();
        if (wrapper != null){
            return wrapper.getCards();
        }else {
            log.error("Cannot read cards");
            return null;
        }
    }
}
