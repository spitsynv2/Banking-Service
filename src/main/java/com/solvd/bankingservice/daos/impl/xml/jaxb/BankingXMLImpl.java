package com.solvd.bankingservice.daos.impl.xml.jaxb;

import com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers.BankingServiceWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class BankingXMLImpl {
    private static final Logger log = LogManager.getLogger(AbstractXMLImpl.class);
    private static final File READ_FROM_FILE = new File("src/main/resources/xml/banking_service.xml");
    private static final String SCHEMA_LOCATION = "src/main/resources/xml/banking_service.xsd";

    public BankingServiceWrapper unmarshalAll() {
        BankingServiceWrapper banking = null;
        try {
            JAXBContext context = JAXBContext.newInstance(BankingServiceWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            banking = (BankingServiceWrapper) unmarshaller.unmarshal(READ_FROM_FILE);
        } catch (JAXBException e) {
            log.error(e);
        }
        return banking;
    }

    public void marshalAll(BankingServiceWrapper banking, String outputFilePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(BankingServiceWrapper.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, SCHEMA_LOCATION);

            marshaller.marshal(banking, new File(outputFilePath));
            log.info("banking_service was successfully written to XML.");
        } catch (JAXBException e) {
            log.error(e);
        }
    }
}
