package egroup.test202207.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import egroup.test202207.dao.OrganizationDAO;
import egroup.test202207.entity.Organization;

@CrossOrigin
@RestController
public class OrgnztnController {

    @Autowired
    OrganizationDAO dao;

    @PostMapping(value = "/organization")
    public void processFormCreate(@RequestBody Organization o) throws Exception {
        dao.create(o);
    }
    
    @GetMapping("/organization/{id}")
    public Organization fetchOne(@PathVariable int id) throws Exception {
        return dao.get(id);
    }

    @PutMapping(value = "/organization/{id}")
    public void processFormUpdate(@RequestBody Organization o, @PathVariable("id") int id) throws Exception {
        o.setId(id);
        int result = dao.update(o);
        if (result == 0) {
            throw new SQLException("id: " + id + " 並不存在");
        }
    }

    @GetMapping("/organizations")
    public List<Organization> fetchList() throws Exception {
        return dao.list();
    }

    @DeleteMapping("/organization/{id}")
    public void deleteOne(@PathVariable("id") int id) throws Exception{
        int result = dao.delete(id);
        if (result == 0) {
            throw new SQLException("id: " + id + " 並不存在");
        }
    }
}
