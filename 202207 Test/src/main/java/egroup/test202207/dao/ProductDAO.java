package egroup.test202207.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import egroup.test202207.entity.Product;

@Repository
public interface ProductDAO {
    public int create(Product product) throws Exception;
    public Product get(int id) throws Exception;
    public int update(Product product) throws Exception;
    public List<Product> list() throws Exception;
    public int delete(int id) throws Exception;
}