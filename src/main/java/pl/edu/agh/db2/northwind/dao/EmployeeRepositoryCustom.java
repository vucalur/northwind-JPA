package pl.edu.agh.db2.northwind.dao;

import pl.edu.agh.db2.northwind.dao.workaround.GenericRelationAwareRepository;
import pl.edu.agh.db2.northwind.model.Employee;

public interface EmployeeRepositoryCustom extends GenericRelationAwareRepository<Employee> {
}
