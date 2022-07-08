package com.egroup.course.demospringpj.controller;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egroup.course.demospringpj.entity.Customer;

@RestController
public class MyCustomerController {
  private ArrayList<Customer> cList = new ArrayList<>();

  public MyCustomerController() {
    cList.add(new Customer("Patric", 2));
    cList.add(new Customer("SpongeBob", 7));
    cList.add(new Customer("Sandy", 13));
  }

  @GetMapping("/customer")
  public ArrayList<Customer> get() {
    return cList;
  }
}
