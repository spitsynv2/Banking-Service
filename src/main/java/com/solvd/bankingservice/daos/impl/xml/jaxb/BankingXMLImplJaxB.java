package com.solvd.bankingservice.daos.impl.xml.jaxb;

import com.solvd.bankingservice.daos.impl.xml.wrappers.BankingServiceWrapper;
import io.github.cdimascio.dotenv.Dotenv;
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
public class BankingXMLImplJaxB {
    private static final Dotenv dotenv = Dotenv.configure().directory("src/main/resources/sql").filename("database_config.env").load();
    private static final Logger log = LogManager.getLogger(BankingXMLImplJaxB.class);
    private static final File READ_FROM_FILE = new File(dotenv.get("XM_FILE_LOCATION"));
    private static final String SCHEMA_FILE_LOCATION = dotenv.get("SCHEMA_FILE_LOCATION");

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
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, SCHEMA_FILE_LOCATION);

            marshaller.marshal(banking, new File(outputFilePath));
        } catch (JAXBException e) {
            log.error(e);
        }
    }
}
