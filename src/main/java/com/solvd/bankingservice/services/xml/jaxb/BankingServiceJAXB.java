package com.solvd.bankingservice.services.xml.jaxb;

import com.solvd.bankingservice.daos.impl.xml.jaxb.BankingXMLImplJAXB;
import com.solvd.bankingservice.daos.impl.xml.wrappers.BankingWrapper;
import com.solvd.bankingservice.models.account.Card;
import com.solvd.bankingservice.services.xml.IBankingProcessingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-06
 */
public class BankingServiceJAXB implements IBankingProcessingService {

    private static final Logger log = LogManager.getLogger(BankingServiceJAXB.class);

    private static final BankingXMLImplJAXB BANKING_XML_IMPL = new BankingXMLImplJAXB();

    @Override
    public void writeAllToFile(BankingWrapper banking, String outputFilePath) {
        log.info("(Part of) Banking_service was successfully written to XML file.");
        BANKING_XML_IMPL.marshalAll(banking,"output.xml");
    }

    @Override
    public BankingWrapper readAllFromFile() {
        log.info("Reading all from XML file.");
        return BANKING_XML_IMPL.unmarshalAll();
    }

    @Override
    public List<Card> readAllCardsFromFile() {
        log.info("Reading all Cards from XML file.");
        BankingWrapper wrapper = BANKING_XML_IMPL.unmarshalAll();
        if (wrapper != null){
            return wrapper.getCards();
        }else {
            log.error("Cannot read cards");
            return null;
        }
    }
}
