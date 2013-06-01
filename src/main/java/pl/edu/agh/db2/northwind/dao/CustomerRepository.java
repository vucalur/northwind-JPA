package pl.edu.agh.db2.northwind.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.db2.northwind.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}