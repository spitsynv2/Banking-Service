package com.solvd.bankingservice.repo.impl.xml.jaxb;

import com.solvd.bankingservice.repo.impl.xml.wrappers.BankingWrapper;
import com.solvd.bankingservice.util.ConfigLoader;
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
public class BankingXMLImplJAXB {
    private static final Logger log = LogManager.getLogger(BankingXMLImplJAXB.class);
    private static final File READ_FROM_FILE = new File(ConfigLoader.getProperty("XML_FILE_LOCATION"));
    private static final String SCHEMA_FILE_LOCATION = ConfigLoader.getProperty("XML_SCHEMA_FILE_LOCATION");

    public BankingWrapper unmarshalAll() {
        BankingWrapper banking = null;
        try {
            JAXBContext context = JAXBContext.newInstance(BankingWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            banking = (BankingWrapper) unmarshaller.unmarshal(READ_FROM_FILE);
        } catch (JAXBException e) {
            log.error(e);
        }
        return banking;
    }

    public void marshalAll(BankingWrapper banking, String outputFilePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(BankingWrapper.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, SCHEMA_FILE_LOCATION);

            marshaller.marshal(banking, new File(outputFilePath));
        } catch (JAXBException e) {
            log.error(e);
        }
    }
}
