package com.solvd.bankingservice.services.xml.jaxb;

import com.solvd.bankingservice.daos.impl.xml.jaxb.BankingXMLImplJaxB;
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
public class BankingProcessingServiceJaxB implements IBankingProcessingService {

    private static final Logger log = LogManager.getLogger(BankingProcessingServiceJaxB.class);

    private static final BankingXMLImplJaxB BANKING_XML_IMPL = new BankingXMLImplJaxB();

    @Override
    public void writeAllToXML(BankingServiceWrapper banking, String outputFilePath) {
        log.info("(Part of) Banking_service was successfully written to XML.");
        BANKING_XML_IMPL.marshalAll(banking,"output.xml");
    }

    @Override
    public BankingServiceWrapper readAllFromXml() {
        log.info("Reading all from XML");
        return BANKING_XML_IMPL.unmarshalAll();
    }

    @Override
    public List<Card> readAllCardsFromXml() {
        BankingServiceWrapper wrapper = BANKING_XML_IMPL.unmarshalAll();
        if (wrapper != null){
            return wrapper.getCards();
        }else {
            log.error("Cannot read cards");
            return null;
        }
    }
}
