package com.example.demo.controller;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.OrganizationDAO;
import com.example.demo.entity.AddressCount;
import com.example.demo.entity.Organization;
@RestController
@CrossOrigin
public class OrganizationController {
  @Autowired
  OrganizationDAO dao;
  @GetMapping(value = "/organization")
  public List<Organization> retrieveOrganizations() throws SQLException, Exception{
      return dao.getList();
  }

  @GetMapping(value = {"/organization/{organizationId}"})
  public Organization retrieveOneOrganization(@PathVariable("organizationId") Long organizationId) throws SQLException, Exception{
    Organization organization = dao.get(organizationId);
    if (organization.getOrganizationId() == -1){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "organizationId: "+organizationId+" 並不存在");
    }
    return organization;
  }


  @PostMapping(value = "/organization")
  public void processFormCreate(@RequestBody Organization organization) throws Exception {
      dao.insert(organization);
  }

  @PutMapping(value = "/organization/{organizationId}")
  public void processFormUpdate(@RequestBody Organization organization, @PathVariable("organizationId") Long organizationId) throws Exception {
    if (organization.getOrganizationId() == null){
      organization.setOrganizationId(organizationId);
    }
    
    int result = dao.update(organization);
    if (result == 0){
      throw new SQLException("organizationId: "+organizationId+" 並不存在");
    }
  }

  @DeleteMapping(value = "/organization/{organizationId}")
  public void delete(@PathVariable("organizationId") Long organizationId) throws Exception {

    int result = dao.delete(organizationId);
    if (result == 0) {
      throw new SQLException("organizationId: " + organizationId + " 並不存在");
    }
  }
}