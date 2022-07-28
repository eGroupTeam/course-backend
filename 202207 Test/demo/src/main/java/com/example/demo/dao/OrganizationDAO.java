package com.example.demo.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Organization;

@Repository
public interface OrganizationDAO {
  public List<Organization> getList() throws Exception;

  public Organization get(int organizationId) throws Exception;

  public int insert(Organization organization) throws Exception;

  public int delete(int organizationId) throws Exception;

  public int update(Organization organization) throws Exception;
}