package com.amigoscode;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

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

        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(Main.class, args);

        printBeans(applicationContext);
    }


    @Bean("foo")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    // @RequestScope()
    public Foo getFoo() {
        return new Foo("bar");
    }

    record Foo(String name){}

    private static void printBeans(ConfigurableApplicationContext ctx) {
        String[] beanDefinitionNames =
                ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
