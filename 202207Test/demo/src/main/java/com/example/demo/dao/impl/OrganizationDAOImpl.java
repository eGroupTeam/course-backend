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
                rs.getInt("org_id"),
                rs.getString("date"),
                rs.getString("org_name"),
                rs.getString("intro"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("address"));
    }

    public Organization get(int id) throws Exception {
        Organization organization = new Organization(-1, "", "", "", "", "", "");
        String sql = "select * from organization where org_id = ?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
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
        String sql = "select * from organization";
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
        String sql = "insert into organization (date, org_name, intro, phone, email, address) values(?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, organization.getDate());
            stmt.setString(2, organization.getName());
            stmt.setString(3, organization.getIntro());
            stmt.setString(4, organization.getPhone());
            stmt.setString(5, organization.getEmail());
            stmt.setString(6, organization.getAddress());
            result = stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public int update(Organization organization) throws Exception {
        int result = 0;
        String sql = "update organization set date=?, org_name=?, intro=?, phone=?, email=?, address=? where org_id=?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, organization.getDate());
            stmt.setString(2, organization.getName());
            stmt.setString(3, organization.getIntro());
            stmt.setString(4, organization.getPhone());
            stmt.setString(5, organization.getEmail());
            stmt.setString(6, organization.getAddress());
            stmt.setInt(7, organization.getId());
            result = stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public int delete(int id) throws Exception {
        int result = 0;
        String sql = "delete from organization where org_id=?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            result = stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
