package com.example.demo.dao;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AddressCount;
import com.example.demo.entity.Organization;
@Repository
public interface OrganizationDAO {
  public List<Organization> getList() throws Exception;
  public List<Organization> getNameList(String name) throws Exception;
  public List<Organization> getWeightOrderedList() throws Exception;
  public List<Organization> getWeightBetweenList(int lowBound, int upperBound) throws Exception;
  public List<String> getDistinctAddressList() throws Exception;
  public List<AddressCount> getGroupByAddressList() throws Exception;
  public List<String> getGroupByAddressHavingList() throws Exception;
  public Organization get(Long organizationId) throws Exception;
  public int insert(Organization organization) throws Exception;
  public int update(Organization organization) throws Exception;
  public int delete(Long organizationId) throws Exception;

}