package com.example.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.OrganizationDAO;
import com.example.demo.entity.Organization;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {
  @Autowired
  private DataSource dataSource;

  private Organization getOrganization(ResultSet rs) throws SQLException, Exception {
    return new Organization(
        rs.getInt("organizationId"),
        rs.getString("date"),
        rs.getString("name"),
        rs.getString("introduction"),
        rs.getString("phone"),
        rs.getString("email"),
        rs.getString("address")
        
        );
  }

  public Organization get(int organizationId) throws Exception {
    Organization organization = new Organization(-1, "", null, 1, organizationId, organizationId);
    String sql = "select organizationId,date, name,introduction,phone,email, address from organization where organizationId = ?";
    // String sql = "select organizationId, name, address, weight from organization where organizationId = ?";
    // 當 Connection與PreparedStatement無法取得時，會closeConnection與PreparedStatement
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1, organizationId);
      try (ResultSet rs = stmt.executeQuery();) {
        if (rs.next()) {
          organization = getOrganization(rs);
        }
      } catch (Exception e) {
        throw e;
      }
    } catch (Exception e) {
      throw e;
    }
    return organization;

  }

  public List<Organization> getList() throws Exception {
    List<Organization> organizations = new ArrayList<Organization>();
    String sql = "select organizationId, date, name, introduction, phone, email, address from organization";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();) {
      while (rs.next()) {
        organizations.add(getOrganization(rs));
      }

    } catch (Exception e) {
      throw e;
    }
    return organizations;
  }

  public int insert(Organization organization) throws Exception {
    int result = 0;
    String sql = "insert into organization (organizationId, date, name, introduction, phone, email, address) values(?, ?, ?, ?, ?, ?, ?)";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1, organization.getOrganizationId());
      stmt.setString(2, organization.getDate());
      stmt.setString(3, organization.getName());
      stmt.setString(4, organization.getIntroduction());
      stmt.setString(5, organization.getPhone());
      stmt.setString(6, organization.getEmail());
      stmt.setString(7, organization.getAddress());
      
      result = stmt.executeUpdate();
    } catch (Exception e) {
      // something wrong
      throw e;
    }
    return result;
  }
  public int update (Organization organization) throws Exception {
    int result = 0;
    String sql = "update organization set date=?, name=?, introduction=?, phone=?, email=?, address=? where organizationId =?";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setString(1, organization.getDate());
      stmt.setString(2, organization.getName());
      stmt.setString(3, organization.getIntroduction());
      stmt.setString(4, organization.getPhone());
      stmt.setString(5, organization.getEmail());
      stmt.setString(6, organization.getAddress());
      stmt.setInt(7, organization.getOrganizationId());
      result = stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
    return result;
  }

  public int delete(int organizationId) throws Exception {
    int result = 0;
    String sql = "delete from organization where organizationId =?";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1, organizationId);
      result = stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
    return result;
  }

  
}
