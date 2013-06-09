package pl.edu.agh.db2.northwind.main.loader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.db2.northwind.dao.*;
import pl.edu.agh.db2.northwind.model.*;
import pl.edu.agh.db2.northwind.oxm.XmlConverter;
import pl.edu.agh.db2.northwind.oxm.holders.ListHolder;

import javax.inject.Inject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader {

	private static final String STATS_FILENAME = "loadingOrders.stats";

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Inject
	private static Logger logger;

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

	@Autowired
	private ReferencesUpdater updater;

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");

		DataLoader dataLoader = appContext.getBean(DataLoader.class);
		dataLoader.start(args);
	}

	private void start(String[] args) {
		List<Category> categories = ((ListHolder<Category>) converter.loadFromXml("categories.xml")).getValues();
		List<Supplier> suppliers = ((ListHolder<Supplier>) converter.loadFromXml("suppliers.xml")).getValues();
		List<Product> products = ((ListHolder<Product>) converter.loadFromXml("products.xml")).getValues();

		List<Shipper> shippers = ((ListHolder<Shipper>) converter.loadFromXml("shippers.xml")).getValues();
		List<Employee> employees = ((ListHolder<Employee>) converter.loadFromXml("employees.xml")).getValues();
		List<Customer> customers = ((ListHolder<Customer>) converter.loadFromXml("customers.xml")).getValues();

		final List<List<Order>> orders = new ArrayList<>();
		orders.add(((ListHolder<Order>) converter.loadFromXml("orders_rand_10000.xml")).getValues());
		orders.add(((ListHolder<Order>) converter.loadFromXml("orders_rand_20000.xml")).getValues());

		final List<List<OrderDetail>> orderDetails = new ArrayList<>();
		orderDetails.add(((ListHolder<OrderDetail>) converter.loadFromXml("orderdetails_rand_10000.xml")).getValues());
		orderDetails.add(((ListHolder<OrderDetail>) converter.loadFromXml("orderdetails_rand_20000.xml")).getValues());

		updater.updateOrders(orders.get(0), shippers, employees, customers);
		updater.updateOrders(orders.get(1), shippers, employees, customers);
		updater.updateOrderDetails(orderDetails.get(0), products, orders.get(0));
		updater.updateOrderDetails(orderDetails.get(1), products, orders.get(1));

		logger.info("Loading ALL started");
		categoryRepository.save(categories);
		supplierRepository.save(suppliers);
		productRepository.saveAll(products);

		shipperRepository.save(shippers);
		employeeRepository.saveAll(employees);
		customerRepository.save(customers);

		logger.info("Loading orders started");

		long[] times = new long[3];
		times[0] = System.currentTimeMillis();

		orderRepository.save(orders.get(0));
		orderDetailRepository.save(orderDetails.get(0));

		times[1] = System.currentTimeMillis();
		orderRepository.save(orders.get(1));
		orderDetailRepository.save(orderDetails.get(1));

		times[2] = System.currentTimeMillis();

		logger.info("Loading orders finished");
		logger.info("Loading ALL finished");

		// TODO: conditional logging
		// TODO 2: logging file name read from property
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(STATS_FILENAME, true)))) {
			logger.info(String.format("Writing times measurements data to: %s", STATS_FILENAME));
			out.println(String.format("Measurements from: %s", new Date().toString()));
			out.println("##################");
			out.println("Orders_count\tOrderDetails_count\tTotal_time\tAvg_time_for_one_Order\t######");
			long totalOrdersCountAll = 0;
			long totalOrderDetailsCountAll = 0;
			long totalTimeAll = 0;
			for (int i = 0; i < 2; ++i) {
				long totalTime = times[i + 1] - times[i];
				totalTimeAll += totalTime;
				long totalOrdersCount = orders.get(i).size();
				totalOrdersCountAll += totalOrdersCount;
				long totalOrderDetailsCount = orderDetails.get(i).size();
				totalOrderDetailsCountAll += totalOrderDetailsCount;

				out.println(String.format("%d\t%d\t%d\t%d",
										  totalOrdersCount,
										  totalOrderDetailsCount,
										  totalTime,
										  totalTime / totalOrdersCount));
			}
			out.println("###\t\t###");
			out.println(String.format("%d\t%d\t%d\t%d",
									  totalOrdersCountAll,
									  totalOrderDetailsCountAll,
									  totalTimeAll,
									  totalTimeAll / totalOrdersCountAll));
			out.println("##################\n");
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
		}
	}
}
