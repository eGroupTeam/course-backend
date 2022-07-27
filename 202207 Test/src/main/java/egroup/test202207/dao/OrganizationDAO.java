package egroup.test202207.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import egroup.test202207.entity.Organization;

@Repository
public interface OrganizationDAO {
    public int create(Organization product) throws Exception;
    public Organization get(int id) throws Exception;
    public int update(Organization product) throws Exception;
    public List<Organization> list() throws Exception;
    public int delete(int id) throws Exception;
}
