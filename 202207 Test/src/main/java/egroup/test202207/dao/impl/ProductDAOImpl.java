package egroup.test202207.dao.impl;

import egroup.test202207.dao.ProductDAO;
import egroup.test202207.entity.Product;

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
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    JdbcTemplate jdbc;

    public int create(Product p) throws Exception {
        int result = 0;
        result = jdbc.update(
                "insert into organization_product(`產品名稱`,`產品說明`,`產品價格`,`產品所屬單位`) value(?,?,?,?);",
                p.getName(), p.getDescription(), p.getPrice(), p.getOrgnztnId());
        return result;
    }

    public Product get(int id) throws Exception {
        Product p = jdbc.query(
                "select * from organization_product natural join organization where `產品所屬單位`=`單位ID` AND `產品ID`=" + String.valueOf(id),
                new ResultSetExtractor<Product>() {
                    @Override
                    public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (rs.next())
                            return new Product(
                                    rs.getInt("產品ID"),
                                    rs.getString("產品名稱"),
                                    rs.getString("產品說明"),
                                    rs.getInt("產品排序"),
                                    rs.getInt("產品價格"),
                                    rs.getInt("產品所屬單位"),
                                    rs.getString("單位名稱"));
                        else
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id: " + id + " 並不存在");
                    }
                });
        return p;
    }

    public int update(Product p) throws Exception {
        int result = 0;
        result = jdbc.update(
                "UPDATE organization_product set `產品名稱`=?, `產品說明`=?,`產品價格`=?,`產品所屬單位`=? where `產品ID`=?;",
                p.getName(), p.getDescription(), p.getPrice(), p.getOrgnztnId(), p.getId());
        return result;
    }

    public List<Product> list() throws Exception {
        List<Product> orgnztnList = jdbc.query("select * from organization_product natural join organization where `產品所屬單位`=`單位ID` order by `產品排序` DESC",
                new ResultSetExtractor<List<Product>>() {
                    @Override
                    public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<Product> lst = new ArrayList<Product>();
                        while (rs.next()) {
                            lst.add(new Product(
                                rs.getInt("產品ID"), 
                                rs.getString("產品名稱"), 
                                rs.getString("產品說明"), 
                                rs.getInt("產品排序"), 
                                rs.getInt("產品價格"), 
                                rs.getInt("產品所屬單位"), 
                                rs.getString("單位名稱")
                            ));
                        }
                        return lst;
                    }
                });
        return orgnztnList;
    }

    public int delete(int id) throws Exception {
        int result = 0;
        result = jdbc.update("DELETE FROM organization_product where `產品ID` =?", id);
        return result;
    }
}
