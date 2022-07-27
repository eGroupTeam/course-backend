package egroup.test202207.dao.impl;

import egroup.test202207.dao.OrganizationDAO;
import egroup.test202207.entity.Organization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public int create(Organization o) throws Exception {
        int result = 0;
        result = jdbc.update(
                "insert into organization(`單位名稱`,`單位介紹`,`單位連絡電話`,`單位聯絡信箱`,`單位地址`,`建立日期`) value(?,?,?,?,?,?);",
                o.getName(), o.getDescription(), o.getPhone(), o.getEmail(), o.getAddress(), o.getCreateDate());
        return result;
    }

    public Organization get(int id) throws Exception {
        Organization o = jdbc.query(
                "select * from organization where  `單位ID`="+ String.valueOf(id),
                new ResultSetExtractor<Organization>() {
                    @Override
                    public Organization extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if(rs.next()) return new Organization(
                            rs.getInt("單位ID"),
                            rs.getString("建立日期"),
                            rs.getString("單位名稱"),
                            rs.getString("單位介紹"),
                            rs.getString("單位連絡電話"),
                            rs.getString("單位聯絡信箱"),
                            rs.getString("單位地址")
                        );
                        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id: "+id+" 並不存在");
                    }
                });
        return o;
    }

    public int update(Organization o) throws Exception {
        int result = 0;
        result = jdbc.update(
                "UPDATE organization set `建立日期`=?, `單位名稱`=? ,`單位介紹`=?, `單位連絡電話`=?, `單位聯絡信箱`=?, `單位地址`=? where `單位ID` =?", 
                o.getCreateDate(), o.getName(), o.getDescription(), o.getPhone(), o.getEmail(), o.getAddress(), o.getId());
        return result;
    }

    public List<Organization> list() throws Exception {
        List<Organization> orgnztnList = jdbc.query("select * from organization",
                new ResultSetExtractor<List<Organization>>() {
                    @Override
                    public List<Organization> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<Organization> lst = new ArrayList<Organization>();
                        while (rs.next()) {
                            lst.add(new Organization(rs.getInt("單位ID"), rs.getString("建立日期"), rs.getString("單位名稱"),rs.getString("單位介紹"),rs.getString("單位連絡電話"),rs.getString("單位聯絡信箱"),rs.getString("單位地址")));
                        }
                        return lst;
                    }
                });
        return orgnztnList;
    }

    public int delete(int id) throws Exception {
        int result = 0;
        result = jdbc.update("DELETE FROM organization where `單位ID` =?", id);
        return result;
    }
}
