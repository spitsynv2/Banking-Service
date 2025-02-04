package com.solvd.bankingservice.daos.impl.xml.jaxb;

import com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers.AbstractWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public abstract class AbstractXMLImpl<T> {
    private static final Logger log = LogManager.getLogger(AbstractXMLImpl.class);
    private static final File READ_FROM_FILE = new File("src/main/resources/xml/banking_service.xml");

    protected abstract Class<? extends AbstractWrapper<T>> getWrapperClass();

    public List<T> getAllEntitys() {
        List<T> tList = new ArrayList<>();
        try {
            Class<? extends AbstractWrapper<T>> wrapperClass = getWrapperClass();

            JAXBContext context = JAXBContext.newInstance(wrapperClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            AbstractWrapper<T> wrapper = (AbstractWrapper<T>) unmarshaller.unmarshal(READ_FROM_FILE);
            tList = wrapper.getEntities();
        } catch (JAXBException e) {
            log.error("Error unmarshaling XML: ", e);
        }
        return tList;
    }

    public void writeEntitiesToXML(List<T> entities, String outputFilePath) {
        try {
            Class<? extends AbstractWrapper<T>> wrapperClass = getWrapperClass();

            AbstractWrapper<T> wrapper = wrapperClass.getDeclaredConstructor().newInstance();
            wrapper.setEntities(entities);

            JAXBContext context = JAXBContext.newInstance(wrapperClass);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(wrapper, new File(outputFilePath));

            log.info("Entities have been successfully written to XML.");
        } catch (Exception e) {
            log.error("Error writing entities to XML: ", e);
        }
    }
}
