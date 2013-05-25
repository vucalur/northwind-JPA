package pl.edu.agh.db2.northwind.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.db2.northwind.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
