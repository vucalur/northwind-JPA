package pl.edu.agh.db2.northwind.main.loader;

import org.springframework.stereotype.Component;
import pl.edu.agh.db2.northwind.model.*;

import java.util.List;

@Component
public class ReferencesUpdater {

	public void updateOrders(List<Order> toUpdate, List<Shipper> shippers, List<Employee> employees, List<Customer> customers) {
		for (Order order : toUpdate) {
			order.setShipper(findShipperById(order.getShipperId(), shippers));
			order.setEmployee(findEmployeeById(order.getEmployeeId(), employees));
			order.setCustomer(findCustomerById(order.getCustomerId(), customers));
		}
	}

	private Shipper findShipperById(Integer id, List<Shipper> shippers) {
		for (Shipper shipper : shippers) {
			if (shipper.getId().equals(id)) {
				return shipper;
			}
		}
		throw new IllegalStateException(String.format("Unable to find Shipper with id: %d", id));
	}

	private Employee findEmployeeById(Integer id, List<Employee> employees) {
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				return employee;
			}
		}
		throw new IllegalStateException(String.format("Unable to find Employee with id: %d", id));
	}

	private Customer findCustomerById(String id, List<Customer> customers) {
		for (Customer customer : customers) {
			if (customer.getId().equals(id)) {
				return customer;
			}
		}
		throw new IllegalStateException(String.format("Unable to find Customer with id: %d", id));
	}

	public void updateOrderDetails(List<OrderDetail> toUpdate, List<Product> products, List<Order> orders) {
		for (OrderDetail od : toUpdate) {
			od.setProduct(findProductById(od.getProductId(), products));
			od.setOrder(findOrderById(od.getOrderId(), orders));
		}
	}

	private Product findProductById(Integer id, List<Product> products) {
		for (Product product : products) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		throw new IllegalStateException(String.format("Unable to find Product with id: %d", id));
	}

	private Order findOrderById(Integer id, List<Order> orders) {
		for (Order order : orders) {
			if (order.getId().equals(id)) {
				return order;
			}
		}
		throw new IllegalStateException(String.format("Unable to find Order with id: %d", id));
	}
}
