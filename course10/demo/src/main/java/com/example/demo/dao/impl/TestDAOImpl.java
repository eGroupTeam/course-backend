package com.example.demo.dao.impl;

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

import com.example.demo.entity.Test;
import com.example.demo.dao.TestDAO;

@Repository
public class TestDAOImpl implements TestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Test> getAll() throws Exception {
        List<Test> tests = jdbcTemplate.query("select * from TestTable",
                new ResultSetExtractor<List<Test>>() {
                    @Override
                    public List<Test> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<Test> lst = new ArrayList<Test>();
                        while (rs.next()) {
                            lst.add(new Test(rs.getInt("id"), rs.getString("str"), rs.getString("str2")));
                        }
                        return lst;
                    }
                });
        return tests;
    }

    public Test get(int id) throws Exception {
        Test t = jdbcTemplate.query(
                "select * from TestTable where id="+ String.valueOf(id),
                new ResultSetExtractor<Test>() {
                    @Override
                    public Test extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if(rs.next()) return new Test(
                            rs.getInt("id"),
                            rs.getString("str"),
                            rs.getString("str2")
                        );
                        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id: "+id+" 並不存在");
                    }
                });
        return t;
    }

    public int insert(Test t) throws Exception {
        int result = 0;

        result = jdbcTemplate.update(
                "INSERT INTO TestTable(STR, STR2) "
                        + "VALUES (?,?)",
                t.getStr(), t.getStr2());
        return result;
    }

    public int update(Test t) throws Exception {
        int result = 0;
        result = jdbcTemplate.update(
                "UPDATE TestTable set STR=?, STR2=? where id =?", t.getStr(), t.getStr2(), t.getId());
        return result;
    }
}