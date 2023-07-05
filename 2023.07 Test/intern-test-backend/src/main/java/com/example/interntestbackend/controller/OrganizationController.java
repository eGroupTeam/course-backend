package com.example.interntestbackend.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.interntestbackend.model.Organization;
import com.example.interntestbackend.service.OrganizationService;

@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService service;

    @PostMapping("/organizations")
    public void createProduct(@RequestBody Organization organization) {
        service.createOrganization(organization);
    }

    @GetMapping("/organizations/{id}")
    public Organization getOrganizationById(@PathVariable int id) {
        return service.getOrganizationById(id);
    }

    @GetMapping("/organizations")
    public List<Organization> getAllOrganization() {
        return service.getAllOrganization();
    }

    @GetMapping("/organizations/name")
    public List<String> getAllOrganizationsName() {
        List<String> organizationNames = service.getAllOrganizationsName();
        if (organizationNames.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "無資料");
        }
        return organizationNames;
    }

    @PutMapping("/organizations/{id}")
    public void updateOrganization(@PathVariable int id, @RequestBody Organization organization) {
        organization.setId(id);
        service.updateOrganization(organization);
    }

    @DeleteMapping("/organizations/{id}")
    public void deleteOrganization(@PathVariable int id) {
        service.deleteOrganization(id);
    }

    @GetMapping("/multiple")
    public List<String> multipleQuery(@RequestBody String organizationName, @RequestBody String productName) {
        List<String> resultList = service.multipleQuery(organizationName, productName);
        if (resultList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "無資料");
        }
        return resultList;
    }
}
