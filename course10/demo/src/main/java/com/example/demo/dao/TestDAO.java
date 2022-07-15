package com.example.demo.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.Test;

@Repository
public interface TestDAO {
    public List<Test> getAll() throws Exception;
    public Test get(int id) throws Exception;
    public int insert(Test t) throws Exception;
    public int update(Test t) throws Exception;
}
