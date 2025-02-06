package com.solvd.bankingservice.daos.impl.json.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.bankingservice.daos.impl.xml.wrappers.BankingWrapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-06
 */
public class BankingJSONImpl {
    private static final Logger log = LogManager.getLogger(BankingJSONImpl.class);
    private static final Dotenv dotenv = Dotenv.configure().directory("src/main/resources/sql").filename("database_config.env").load();
    private static final File READ_FROM_FILE = new File(dotenv.get("JSON_FILE_LOCATION"));

    public void writeAllToJson(BankingWrapper banking, String outputFilePath){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFilePath), banking);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public BankingWrapper readAllFromJson(){
        BankingWrapper bankingWrapper = new BankingWrapper();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
            mapper.registerModule(new JavaTimeModule());
            bankingWrapper = mapper.readValue(READ_FROM_FILE, BankingWrapper.class);
        } catch (IOException e) {
            log.error(e);
            return null;
        }
        return bankingWrapper;
    }
}
