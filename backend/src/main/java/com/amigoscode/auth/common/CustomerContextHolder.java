package com.amigoscode.auth.common;

import com.amigoscode.customer.entity.Customer;

public class CustomerContextHolder {
    private static final ThreadLocal<Customer> threadLocal = new ThreadLocal<>();

    public static void setCustomer(Customer customer) {
        threadLocal.set(customer);
    }

    public static Customer getCustomer() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
