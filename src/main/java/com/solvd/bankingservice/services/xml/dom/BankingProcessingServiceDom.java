package com.solvd.bankingservice.services.xml.dom;

import com.solvd.bankingservice.daos.impl.xml.dom.BankingXMLImplDom;
import com.solvd.bankingservice.daos.impl.xml.wrappers.BankingServiceWrapper;
import com.solvd.bankingservice.models.account.Card;
import com.solvd.bankingservice.services.xml.IBankingProcessingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-06
 */
public class BankingProcessingServiceDom implements IBankingProcessingService {
    private static final Logger log = LogManager.getLogger(BankingProcessingServiceDom.class);
    private static final BankingXMLImplDom BANKING_XML_IMPL = new BankingXMLImplDom();

    @Override
    public void writeAllToXML(BankingServiceWrapper banking, String outputFilePath) {
        log.error("writeAllToXML is not implemented, use BankingProcessingServiceJaxB");
    }

    @Override
    public BankingServiceWrapper readAllFromXml() {
        log.info("Reading all from XML.");
        return BANKING_XML_IMPL.readAllFromXmlViaDom();
    }

    @Override
    public List<Card> readAllCardsFromXml() {
        BankingServiceWrapper wrapper = BANKING_XML_IMPL.readAllFromXmlViaDom();
        if (wrapper != null){
            return wrapper.getCards();
        }else {
            log.error("Cannot read cards");
            return null;
        }
    }
}
