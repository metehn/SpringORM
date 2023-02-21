package com.metehan.SpringORM.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/customer/persist")
    @ResponseBody
    public String persistCustomer(@RequestParam(name = "name") String customerName, @RequestParam(name = "debit") double totalDebit) {


        Customer customer = new Customer(0, customerName, totalDebit);

        return "Customer ID: " + customerRepository.save(customer).getCustomerId();

    }

    @GetMapping("/customer/find/{id}")
    @ResponseBody
    public String findCustomer(@PathVariable(name = "id") long customerId) {


        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        return optionalCustomer.isPresent() ? "Customer Name: " + optionalCustomer.get().getCustomerName() : "Customer not found: " + customerId;

    }

    @GetMapping("/customer/merge/{id}")
    @ResponseBody
    public String mergeCustomer(@PathVariable(name = "id") long customerId, @RequestParam(name = "name") String customerName, @RequestParam(name = "debit") long totalCredit) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {

            Customer customer = optionalCustomer.get();
            customer.setCustomerName(customerName);
            customer.setTotalCredit(totalCredit);
            customerRepository.save(customer);

            return "Customer Name: " + customer.getCustomerName() + "\nCustomer Credit: " + customer.getTotalCredit();
        } else {
            return "Customer not found: " + customerId;
        }


    }

    @GetMapping("/customer/delete/{id}")
    @ResponseBody
    public String deleteCustomer(@PathVariable(name = "id") long customerId) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {

            customerRepository.deleteById(customerId);

            return "Customer Deleted ";
        } else {
            return "Customer not found: " + customerId;
        }
    }
}
