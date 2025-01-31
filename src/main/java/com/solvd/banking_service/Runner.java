package com.solvd.banking_service;

import com.solvd.banking_service.models.customer.Customer;
import com.solvd.banking_service.services.CustomerAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner
{
    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main(String[] args )
    {
        Customer customer = CustomerAccountService.getCustomerFromDataBase(1L);
        log.info(customer);
    }
}
