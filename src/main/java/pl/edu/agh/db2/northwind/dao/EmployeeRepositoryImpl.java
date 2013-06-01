package pl.edu.agh.db2.northwind.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.db2.northwind.model.Employee;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public <S extends Employee> S saveOne(S s) {
		updateRelations(s);
		return employeeRepository.save(s);
	}

	@Override
	public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
		for (S s : entities) {
			updateRelations(s);
		}
		return employeeRepository.save(entities);
	}

	// TODO: Doesn't work for self-referencing relationship
	private <S extends Employee> void updateRelations(S s) {
		if (s.getReportsTo() != null) {
			s.setSupervisor(employeeRepository.findOne(s.getReportsTo()));
		}
	}
}
