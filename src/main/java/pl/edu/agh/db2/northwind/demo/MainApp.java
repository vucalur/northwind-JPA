package pl.edu.agh.db2.northwind.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import pl.edu.agh.db2.northwind.dao.*;
import pl.edu.agh.db2.northwind.model.*;
import pl.edu.agh.db2.northwind.oxm.XmlConverter;
import pl.edu.agh.db2.northwind.oxm.holders.ListHolder;

import java.util.List;

@Component
public class MainApp {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShipperRepository shipperRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	private TransactionTemplate transactionTemplate;

	@Autowired
	private XmlConverter converter;

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");

		MainApp mainApp = appContext.getBean(MainApp.class);
		mainApp.start(args);
	}

	@Required
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	private void start(String[] args) {
		List<Category> unmarshalledCategories = ((ListHolder<Category>) converter.loadFromXml("categories.xml")).getValues();
		List<Supplier> unmarshalledSuppliers = ((ListHolder<Supplier>) converter.loadFromXml("suppliers.xml")).getValues();
		List<Product> unmarshalledProducts = ((ListHolder<Product>) converter.loadFromXml("products.xml")).getValues();

		List<Shipper> unmarshalledShippers = ((ListHolder<Shipper>) converter.loadFromXml("shippers.xml")).getValues();
		List<Employee> unmarshalledEmployees = ((ListHolder<Employee>) converter.loadFromXml("employees.xml")).getValues();
		List<Customer> unmarshalledCustomers = ((ListHolder<Customer>) converter.loadFromXml("customers.xml")).getValues();

		final List<Order> unmarshalledOrders = ((ListHolder<Order>) converter.loadFromXml("orders.xml")).getValues();

		final List<OrderDetail> unmarshalledOrderDetails = ((ListHolder<OrderDetail>) converter.loadFromXml("orderdetails.xml")).getValues();

		categoryRepository.save(unmarshalledCategories);
		supplierRepository.save(unmarshalledSuppliers);
		productRepository.saveAll(unmarshalledProducts);

		shipperRepository.save(unmarshalledShippers);
		employeeRepository.saveAll(unmarshalledEmployees);
		customerRepository.save(unmarshalledCustomers);

		orderRepository.saveAll(unmarshalledOrders);
		orderDetailRepository.saveAll(unmarshalledOrderDetails);

		transactionTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				System.out.println(productRepository.findOne(12));
				System.out.println(productRepository.findOne(12).getProductName());
				System.out.println(productRepository.findOne(12).getSupplierId());
				System.out.println(supplierRepository.findOne(5).getCompanyName());
				System.out.println(productRepository.findOne(12).getSupplier().getCompanyName());

				return null;
			}
		});
	}
}
