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

import egroup.test202207.dao.ProductDAO;
import egroup.test202207.entity.Product;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    ProductDAO dao;

    @PostMapping("/product")
    public void processFormCreate(@RequestBody Product p) throws Exception {
        dao.create(p);
    }
    
    @GetMapping("/product/{id}")
    public Product fetchOne(@PathVariable int id) throws Exception {
        return dao.get(id);
    }

    @PutMapping("/product/{id}")
    public void processFormUpdate(@RequestBody Product p, @PathVariable("id") int id) throws Exception {
        p.setId(id);
        int result = dao.update(p);
        if (result == 0) {
            throw new SQLException("id: " + id + " 並不存在");
        }
    }

    @GetMapping("/products")
    public List<Product> fetchList() throws Exception {
        return dao.list();
    }

    @DeleteMapping("/product/{id}")
    public void deleteOne(@PathVariable("id") int id) throws Exception{
        int result = dao.delete(id);
        if (result == 0) {
            throw new SQLException("id: " + id + " 並不存在");
        }
    }
}