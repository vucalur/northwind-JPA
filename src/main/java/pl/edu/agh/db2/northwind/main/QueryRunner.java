package pl.edu.agh.db2.northwind.main;

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
import pl.edu.agh.db2.northwind.main.properties.QueryRunnerProperties;

import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryRunner {

	private static final String QUERIES_NUMBERS_DELIMITER = ",";

	@Autowired
	private QueryRunnerProperties properties;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Inject
	private static Logger logger;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderRepository orderRepository;

	private TransactionTemplate transactionTemplate;

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");

		QueryRunner queryRunner = appContext.getBean(QueryRunner.class);
		queryRunner.start(args);
	}

	@Required
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	private void start(String[] args) {
		List<Integer> queriesToExecute = parseQueriesToExecuteStr();

		try (PrintWriter resultsOut = new PrintWriter(new BufferedWriter(getResultsOutput()));
			 PrintWriter statsOut = new PrintWriter(new BufferedWriter(getStatsOutput()))) {

			if (queriesToExecute.contains(1)) {
				transactionTemplate.execute(new TransactionCallback() {
					@Override
					public Object doInTransaction(TransactionStatus status) {
						logger.info("running totalOrdersByCountry");

						long start = System.currentTimeMillis();
						List<org.javatuples.Pair<String, Integer>> result = orderRepository.totalOrdersByCountry();
						long stop = System.currentTimeMillis();
						if (properties.isShowResults()) {
							for (org.javatuples.Pair<String, Integer> p : result) {
								resultsOut.println(String.format("%15s\t%8d", p.getValue0(), p.getValue1()));
							}
							resultsOut.println("\n");
						}
						statsOut.println(String.format("%-34s\t%8d ms", "totalOrdersByCountry", stop - start));
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
						if (properties.isShowResults()) {
							for (org.javatuples.Pair<Integer, Double> p : result) {
								resultsOut.println(String.format("%8d\t%8.4f", p.getValue0(), p.getValue1()));
							}
							resultsOut.println("\n");
						}
						statsOut.println(String.format("%-34s\t%8d ms", "avgRealisationTimeInDaysByYear", stop - start));
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
						if (properties.isShowResults()) {
							for (org.javatuples.Pair<Integer, String> p : result) {
								resultsOut.println(String.format("%8d\t%25s", p.getValue0(), p.getValue1()));
							}
							resultsOut.println("\n");
						}
						statsOut.println(String.format("%-34s\t%8d ms", "productQuantitySumBySupplier", stop - start));
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
						if (properties.isShowResults()) {
							for (Pair<Integer, Double> p : result) {
								resultsOut.println(String.format("%8s\t%10f", p.getValue0(), p.getValue1()));
							}
							resultsOut.println("\n");
						}
						statsOut.println(String.format("%-34s\t%8d ms", "ordersTotalByWeekDay", stop - start));
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
						if (properties.isShowResults()) {
							for (Triplet<Double, Integer, String> p : result) {
								resultsOut.println(String.format("%10f\t%8d\t%12s", p.getValue0(), p.getValue1(), p.getValue2()));
							}
							resultsOut.println("\n");
						}
						statsOut.println(String.format("%-34s\t%8d ms", "ordersTotalByYearByCustomerCountry", stop - start));
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
						if (properties.isShowResults()) {
							for (Triplet<Double, Integer, Integer> p : result) {
								resultsOut.println(String.format("%10f\t%8d\t%8d", p.getValue0(), p.getValue1(), p.getValue2()));
							}
							resultsOut.println("\n");
						}
						statsOut.println(String.format("%-34s\t%8d ms", "avgUnitPriceByShipperByYear", stop - start));
						return null;
					}
				});
			}

			if (properties.isShowResults()) {
				resultsOut.println("###############################################");
			}
			statsOut.println("###############################################");
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

	private Writer getStatsOutput() {
		try {
			if (properties.getOutputTypeStats().equals("file")) {
				return new FileWriter(properties.getPathStats(), properties.isAppendStats());
			} else {
				return new OutputStreamWriter(System.out);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Writer getResultsOutput() {
		try {
			if (properties.getOutputTypeStats().equals("file")) {
				return new FileWriter(properties.getPathResults(), properties.isAppendResults());
			} else {
				return new OutputStreamWriter(System.out);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}