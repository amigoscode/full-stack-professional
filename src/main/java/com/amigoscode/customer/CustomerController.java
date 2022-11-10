package com.amigoscode.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
   @RequestMapping(
           path = "api/v1/customer",
           method = RequestMethod.GET
   )*/
    @GetMapping("api/v1/customers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("api/v1/customers/{customerId}")
    public Customer getCustomer(
            @PathVariable("customerId") Integer customerId) {
        return customerService.getCustomer(customerId);
    }

}
