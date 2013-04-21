package pl.edu.agh.db2.northwind.dao;

import org.springframework.dao.DataAccessException;
import pl.edu.agh.db2.northwind.model.Supplier;

import java.util.List;

public interface SupplierDao {
    public List<Supplier> getSuppliers() throws DataAccessException;

    public Supplier getSupplier(Integer supplierId) throws DataAccessException;

    public List<Supplier> getSuppliesCompanyNameStartsWith(String prefix) throws DataAccessException;

    void persist(Supplier supplier) throws DataAccessException;
}