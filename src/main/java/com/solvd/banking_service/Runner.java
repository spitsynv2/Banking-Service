package com.solvd.banking_service;

import com.solvd.banking_service.models.customer.Customer;
import com.solvd.banking_service.services.CustomerAccountService;
import com.solvd.banking_service.services.database_connection.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner
{
    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main(String[] args )
    {
        Customer customer = CustomerAccountService.getCustomerFromDataBase(1L);
        log.info(customer);
        MyConnectionPool.closeAllConnections();

        //TODO Finish All DAOS and SERVICES
        //TODO XML FILE
        //TODO JAXB MARSHAL UNMARSHAL
    }
}
