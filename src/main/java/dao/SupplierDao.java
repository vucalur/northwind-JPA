package dao;
 
import model.Supplier;
import org.springframework.dao.DataAccessException;

import java.util.List;
 
public interface SupplierDao {
    public List<Supplier> getSuppliers() throws DataAccessException;
    public Supplier getSupplier(Long supplierId) throws DataAccessException;
}