package pl.edu.agh.db2.northwind.main.query;

import org.apache.log4j.Logger;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import pl.edu.agh.db2.northwind.dao.OrderDetailRepository;
import pl.edu.agh.db2.northwind.dao.OrderRepository;
import pl.edu.agh.db2.northwind.main.query.results.ResultsPrinter;
import pl.edu.agh.db2.northwind.main.query.stats.StatsPrinter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryRunner {

	private static final String QUERIES_NUMBERS_DELIMITER = ",";

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Inject
	private static Logger logger;

	@Autowired
	private QrProperties properties;

	@Autowired
	private QrConfigurator configurator;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderRepository orderRepository;

	private TransactionTemplate transactionTemplate;

	public static void main(String[] args) {
		long allStart = System.currentTimeMillis();
		ApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");

		QueryRunner queryRunner = appContext.getBean(QueryRunner.class);
		queryRunner.start(args, allStart);
	}

	private void start(String[] args, long allStart) {
		List<Integer> queriesToExecute = parseQueriesToExecuteStr();

		try (ResultsPrinter resultsPrinter = configurator.getResultsPrinter();
			 StatsPrinter statsPrinter = configurator.getStatsPrinter()) {

			if (queriesToExecute.contains(1)) {
				transactionTemplate.execute(new TransactionCallback() {
					@Override
					public Object doInTransaction(TransactionStatus status) {
						logger.info("running totalOrdersByCountry");

						long start = System.currentTimeMillis();
						List<org.javatuples.Pair<String, Integer>> result = orderRepository.totalOrdersByCountry();
						long stop = System.currentTimeMillis();
						resultsPrinter.print1(result);
						statsPrinter.print1(stop - start);
						return null;
					}
				});
			}

			if (queriesToExecute.contains(2)) {
				transactionTemplate.execute(new TransactionCallback() {
					@Override
					public Object doInTransaction(TransactionStatus status) {
						logger.info("running avgRealisationTimeInDaysByYear");

						long start = System.currentTimeMillis();
						List<org.javatuples.Pair<Integer, Double>> result = orderRepository.avgRealisationTimeInDaysByYear();
						long stop = System.currentTimeMillis();
						resultsPrinter.print2(result);
						statsPrinter.print2(stop - start);
						return null;
					}
				});
			}

			if (queriesToExecute.contains(3)) {
				transactionTemplate.execute(new TransactionCallback() {
					@Override
					public Object doInTransaction(TransactionStatus status) {
						logger.info("running productQuantitySumBySupplier");

						long start = System.currentTimeMillis();
						List<org.javatuples.Pair<Integer, String>> result = orderDetailRepository.productQuantitySumBySupplier();
						long stop = System.currentTimeMillis();
						resultsPrinter.print3(result);
						statsPrinter.print3(stop - start);
						return null;
					}
				});
			}

			if (queriesToExecute.contains(4)) {
				transactionTemplate.execute(new TransactionCallback() {
					@Override
					public Object doInTransaction(TransactionStatus status) {
						logger.info("running ordersTotalByWeekDay");

						long start = System.currentTimeMillis();
						List<Pair<Integer, Double>> result = orderDetailRepository.ordersTotalByWeekDay();
						long stop = System.currentTimeMillis();
						resultsPrinter.print4(result);
						statsPrinter.print4(stop - start);
						return null;
					}
				});
			}

			if (queriesToExecute.contains(5)) {
				transactionTemplate.execute(new TransactionCallback() {
					@Override
					public Object doInTransaction(TransactionStatus status) {
						logger.info("running ordersTotalByYearByCustomerCountry");

						long start = System.currentTimeMillis();
						List<Triplet<Double, Integer, String>> result = orderDetailRepository.ordersTotalByYearByCustomerCountry();
						long stop = System.currentTimeMillis();
						resultsPrinter.print5(result);
						statsPrinter.print5(stop - start);
						return null;
					}
				});
			}

			if (queriesToExecute.contains(6)) {
				transactionTemplate.execute(new TransactionCallback() {
					@Override
					public Object doInTransaction(TransactionStatus status) {
						logger.info("running avgUnitPriceByShipperByYear");

						long start = System.currentTimeMillis();
						List<Triplet<Double, Integer, Integer>> result = orderDetailRepository.avgUnitPriceByShipperByYear();
						long stop = System.currentTimeMillis();
						resultsPrinter.print6(result);
						statsPrinter.print6(stop - start);
						return null;
					}
				});
			}

			long allStop = System.currentTimeMillis();
			statsPrinter.printFinishTime(allStop - allStart);
		}
	}

	private List<Integer> parseQueriesToExecuteStr() {
		List<Integer> result = new ArrayList<>();
		String[] queriesNumbersStr = properties.getQueriesToExecuteStr().split(QUERIES_NUMBERS_DELIMITER);
		for (String number : queriesNumbersStr) {
			result.add(Integer.valueOf(number));
		}
		return result;
	}

	@Required
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
}