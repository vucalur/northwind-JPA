package pl.edu.agh.db2.northwind.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.db2.northwind.model.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
