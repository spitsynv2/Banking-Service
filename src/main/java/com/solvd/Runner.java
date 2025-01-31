package com.solvd;

import com.solvd.models.customer.Customer;
import com.solvd.services.CustomerAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner
{
    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main(String[] args )
    {
        Customer customer = CustomerAccountService.getCustomerFromDataBase(4L);
        log.info(customer);
    }
}
