package pl.edu.agh.db2.northwind.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.db2.northwind.dao.*;
import pl.edu.agh.db2.northwind.model.Category;
import pl.edu.agh.db2.northwind.model.Product;
import pl.edu.agh.db2.northwind.model.Supplier;
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

	@Autowired
	private XmlConverter converter;

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");

		MainApp mainApp = appContext.getBean(MainApp.class);
		mainApp.start(args);
	}

	private void start(String[] args) {
		List<Category> unmarshalledCategories = ((ListHolder<Category>) converter.loadFromXml("categories.xml")).getValues();
		List<Supplier> unmarshalledSuppliers = ((ListHolder<Supplier>) converter.loadFromXml("suppliers.xml")).getValues();
		List<Product> unmarshalledProducts = ((ListHolder<Product>) converter.loadFromXml("products.xml")).getValues();

		categoryRepository.save(unmarshalledCategories);
		supplierRepository.save(unmarshalledSuppliers);
		productRepository.saveAll(unmarshalledProducts);

		System.out.println(productRepository.findOne(12));
		System.out.println(productRepository.findOne(12).getProductName());
		System.out.println(productRepository.findOne(12).getSupplierId());
		System.out.println(supplierRepository.findOne(5).getCompanyName());
		// FIXME: causes "could not initialize proxy - no Session " problem
//		System.out.println(productRepository.findOne(12).getSupplier().getCompanyName());
	}
}
