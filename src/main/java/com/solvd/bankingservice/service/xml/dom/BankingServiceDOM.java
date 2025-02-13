package com.solvd.bankingservice.service.xml.dom;

import com.solvd.bankingservice.repo.impl.xml.dom.BankingXMLImplDOM;
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
public class BankingServiceDOM implements IBankingProcessingService {
    private static final Logger log = LogManager.getLogger(BankingServiceDOM.class);
    private static final BankingXMLImplDOM BANKING_XML_IMPL = new BankingXMLImplDOM();

    @Override
    public void writeAllToFile(BankingWrapper banking, String outputFilePath) {
        log.error("writeAllToXML is not implemented, use BankingProcessingServiceJaxB");
    }

    @Override
    public BankingWrapper readAllFromFile() {
        log.info("Reading all from XML.");
        return BANKING_XML_IMPL.readAllFromXmlViaDom();
    }

    @Override
    public List<Card> readAllCardsFromFile() {
        BankingWrapper wrapper = BANKING_XML_IMPL.readAllFromXmlViaDom();
        if (wrapper != null){
            return wrapper.getCards();
        }else {
            log.error("Cannot read cards");
            return null;
        }
    }
}
