package com.amigoscode;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        /*
            Never do this:
                CustomerService customerService =
                        new CustomerService(new CustomerDataAccessService());
                CustomerController customerController =
                        new CustomerController(customerService);
         */

        SpringApplication.run(Main.class, args);
    }
}
