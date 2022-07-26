package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class TestController{
    @GetMapping("/test")
    @ResponseBody
    public String index() {
        return "<h1>Hi! This is Test!</h1>";
    }

    @GetMapping("/customer")
    public String getCustomer() {
        return "customer.html";
    }

    @GetMapping("/test/request")
    @ResponseBody
    public String request(@ModelAttribute("id") String name, @ModelAttribute("id") int age){
       
       return name+"("+age+")";
    }
}
