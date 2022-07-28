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
import com.example.demo.entity.AddressCount;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {
  @Autowired
  private DataSource dataSource;
  
  private Organization getOrganization(ResultSet rs) throws SQLException, Exception{
    return new Organization(
      rs.getLong("organizationId"),
      rs.getString("createDate"),
      rs.getString("organizationName"),
      rs.getString("organizationIntro"),
      rs.getString("organizationTel"),
      rs.getString("organizationMail"),
      rs.getString("organizationAddr")
    );
      
  }

  public Organization get(Long organizationId) throws Exception{
    Organization organization = new Organization(-1l,"","",1);
    String sql = "select organizationId, createDate, organizationName, organizationIntro, organizationTel, organizationMail, organizationAddr from organization where organizationId = ?";
    //String sql = "select organizationId, organizationName, address, weight from organization where organizationId = ?";
    //當 Connection與PreparedStatement無法取得時，會closeConnection與PreparedStatement
    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ){
      stmt.setLong(1, organizationId);
      try (ResultSet rs = stmt.executeQuery();){
        if (rs.next()){
          organization=getOrganization(rs);
        } 
      }
      catch (Exception e){
        throw e;
      }
    }
    catch (Exception e){
      throw e;
    }
    return organization;

  }

  public List<Organization> getNameList(String organizationName) throws Exception{
    List<Organization> organizations = new ArrayList<Organization>();
    String sql = "select organizationId, organizationName, address, weight from organization where organizationName like ?";
    //當 Connection與PreparedStatement無法取得時，會closeConnection與PreparedStatement
    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);){
      stmt.setString(1, "%"+organizationName+"%");
      try (ResultSet rs = stmt.executeQuery();){
        while (rs.next()){
          organizations.add(getOrganization(rs));
        } 
      }
      catch (Exception e){
        throw e;
      }
    }
    catch (Exception e){
      throw e;
    }
    return organizations;

  }


  public List<Organization> getList() throws Exception{
    List<Organization> organizations = new ArrayList<Organization>();
    String sql = "select organizationId, createDate, organizationName, organizationIntro, organizationTel, organizationMail, organizationAddr from organization";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        organizations.add(getOrganization(rs));
      }  
      
    } catch(Exception e) {
      throw e;
    }
    return organizations;
  }

  public List<Organization> getWeightOrderedList() throws Exception{
    List<Organization> organizations = new ArrayList<Organization>();
    String sql = "select organizationId, organizationName, address, weight from organization order by weight";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        organizations.add(getOrganization(rs));
      }
    } catch(Exception e) {
      throw e;
    }
    return organizations;
  }

  public List<Organization> getWeightBetweenList(int lowBound, int upperBound) throws Exception{
    List<Organization> organizations = new ArrayList<Organization>();
    String sql = "select organizationId, organizationName, address, weight from organization where weight between ? and ?";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1,lowBound);
      stmt.setInt(2,upperBound);
      try(ResultSet rs = stmt.executeQuery();){
        while (rs.next()){
          organizations.add(getOrganization(rs));
        }  
      }
      catch(Exception e) {throw e;}  
    } catch(Exception e) {
      throw e;
    }
    return organizations;
  }

  public List<String> getDistinctAddressList() throws Exception{
    List<String> organizations = new ArrayList<String>();
    String sql = "select distinct address from organization";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        organizations.add(rs.getString("address"));
      }  
    } catch(Exception e) {
      throw e;
    }
    return organizations;
  }

  public List<AddressCount> getGroupByAddressList() throws Exception{
    List<AddressCount> organizations = new ArrayList<AddressCount>();
    String sql = "select address, COUNT(address) As count from organization group by address";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {     
      while (rs.next()){
        //System.out.println(rs.getString("COUNT(address)"));
        //organizations.add(new AddressCount(rs.getString("address"), rs.getInt("COUNT(address)")));
        organizations.add(new AddressCount(rs.getString("address"), rs.getInt("count")));
      }  
    } catch(Exception e) {
      throw e;
    }
    return organizations;
  }

  public List<String> getGroupByAddressHavingList() throws Exception{
    List<String> organizations = new ArrayList<String>();
    String sql = "select address from organization group by address having COUNT(address)>2";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      try(ResultSet rs = stmt.executeQuery();){
        while (rs.next()){
          organizations.add(rs.getString("address"));
        }  
      }
      catch(Exception e) {throw e;}  
    } catch(Exception e) {
      throw e;
    }
    return organizations;
  }

  public int insert(Organization organization) throws Exception{
    int result = 0;
    String sql = "insert into organization (createDate, organizationName , organizationIntro, organizationTel, organizationMail, organizationAddr) values(?, ?, ?, ?, ?, ?)";
    try( 
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {    
        stmt.setString(1, organization.getCreateDate());
        stmt.setString(2, organization.getOrganizationName());
        stmt.setString(3, organization.getOrganizationIntro());
        stmt.setString(4, organization.getOrganizationTel());
        stmt.setString(5, organization.getOrganizationMail());
        stmt.setString(6, organization.getOrganizationAddr());
      result = stmt.executeUpdate();
    } catch(Exception e) {
      //something wrong
      throw e;
    }
    return result;
  }
  public int update(Organization organization) throws Exception{
    int result = 0;
    String sql = "update organization set createDate=?, organizationName=?, organizationIntro=?, organizationTel=?, organizationMail=?, organizationAddr=? where organizationId =?";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
        stmt.setString(1, organization.getCreateDate());
        stmt.setString(2, organization.getOrganizationName());
        stmt.setString(3, organization.getOrganizationIntro());
        stmt.setString(4, organization.getOrganizationTel());
        stmt.setString(5, organization.getOrganizationMail());
        stmt.setString(6, organization.getOrganizationAddr());
        stmt.setLong(7, organization.getOrganizationId());
      result = stmt.executeUpdate();
    } catch(Exception e) {
      throw e;
    }
    return result;
  }

  public int delete(Long organizationId) throws Exception {
    int result = 0;
    String sql = "delete from organization where organizationId =?";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setLong(1, organizationId);
      result = stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
    return result;
  }
}
