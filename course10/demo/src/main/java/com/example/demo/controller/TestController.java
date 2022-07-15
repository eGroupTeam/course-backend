package com.example.demo.controller;
//import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.TestDAO;
import com.example.demo.entity.Test;

@RestController
public class TestController {

    @Autowired
    TestDAO dao;

    @GetMapping("/tests")
    public List<Test> fetchTestList() throws Exception {
        return dao.getAll();
    }

    @GetMapping("/test/{id}")
    public Test fetchOneTest(@PathVariable int id) throws Exception {
        return dao.get(id);
    }

    @PostMapping(value = "/test")
    public void processFormCreate(@RequestBody Test t) throws Exception {
        dao.insert(t);
    }

    @PutMapping(value = "/test/{id}")
    public void processFormUpdate(@RequestBody Test t, @PathVariable("id") int id) throws Exception {
        t.setId(id);
        int result = dao.update(t);
        if (result == 0) {
            throw new SQLException("id: " + id + " 並不存在");
        }
    }
}
