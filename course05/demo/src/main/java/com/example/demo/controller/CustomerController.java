package com.example.demo.controller;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;

@RestController
public class CustomerController {
    private ArrayList<Customer> customerList = new ArrayList<>();

    public CustomerController() {
        customerList.add(new Customer("Peralta", 30));
        customerList.add(new Customer("Santiago", 23));
        customerList.add(new Customer("Jeffrey", 29));
    }
    @GetMapping("/customer")
    public ArrayList<Customer> get() {
        return customerList;
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer){
        customerList.add(customer);
    }

    @PutMapping("/customer/{id}")
    public void editCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
        customerList.set(id, customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable("id") int id){
        customerList.remove(id);
    }
    
}
