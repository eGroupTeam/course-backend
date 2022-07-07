package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
  @RestController
  public class MainController{
    @GetMapping({"/","/hello"})
    public String index() {
        return "<h1>Hi!</h1>";
    }
    @GetMapping("/request")
    public String request(HttpServletRequest request) {
      //System.out.println("get");
      String name = request.getParameter("id");
      return name;
    }
    @PostMapping("/request")
    public String postRequest(@ModelAttribute Customer customer) {
      //System.out.println("post");
      return customer.getName();
    }
}