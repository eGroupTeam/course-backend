package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dao.OrganizationDAO;
import com.example.demo.entity.Organization;

@RestController
@CrossOrigin
public class OrganizationController {
    @Autowired
    OrganizationDAO dao;

    @GetMapping(value = "/organization")
    public List<Organization> retrieveOrganizations() throws SQLException, Exception {
        return dao.getList();
    }

    @GetMapping(value = { "/organization/{id}" })
    public Organization retrieveOneOrganization(@PathVariable("id") int id) throws SQLException, Exception {
        Organization organization = dao.get(id);
        if (organization.getId() == -1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "id: " + id + " 並不存在");
        }
        return organization;
    }

    @PostMapping(value = "/organization")
    public void processFormCreate(@RequestBody Organization organization) throws Exception {
        dao.insert(organization);
    }

    @PutMapping(value = "/organization/{id}")
    public void processFormUpdate(@RequestBody Organization organization, @PathVariable("id") int id) throws Exception {
        if (organization.getId() == 0) {
            organization.setId(id);
        }

        int result = dao.update(organization);
        if (result == 0) {
            throw new SQLException("id: " + id + " 並不存在");
        }
    }

    @DeleteMapping(value = "/organization/{id}")
    public void delete(@PathVariable("id") int id) throws Exception {

        int result = dao.delete(id);
        if (result == 0) {
            throw new SQLException("id: " + id + " 並不存在");
        }
    }

}
