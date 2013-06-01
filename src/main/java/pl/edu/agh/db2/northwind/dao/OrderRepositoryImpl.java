package pl.edu.agh.db2.northwind.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.db2.northwind.model.Order;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
	@Autowired
	private ShipperRepository shipperRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public <S extends Order> S saveOne(S s) {
		updateRelations(s);
		return orderRepository.save(s);
	}

	@Override
	public <S extends Order> List<S> saveAll(Iterable<S> entities) {
		for (S s : entities) {
			updateRelations(s);
		}
		return orderRepository.save(entities);
	}

	private <S extends Order> void updateRelations(S s) {
		s.setShipper(shipperRepository.findOne(s.getShipperId()));
		s.setEmployee(employeeRepository.findOne(s.getEmployeeId()));
		s.setCustomer(customerRepository.findOne(s.getCustomerId()));
	}
}
