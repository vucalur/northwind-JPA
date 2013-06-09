package pl.edu.agh.db2.northwind.dao;

import org.javatuples.Pair;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.agh.db2.northwind.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends FlushingEveryNRepository<Order, Integer> {

	/**
	 * EN: How many orders were submitted from each country
	 * PL (original): Ile zamówień z każdego kraju zostało zrealizowanych
	 */
	@Query("select new org.javatuples.Pair(" +
				   "c.country, count(*)) " +
				   "from Order o join o.customer c " +
				   "group by c.country")
	List<Pair<String, Integer>> totalOrdersByCountry();

	/**
	 * EN: What was the average order realization time in each year
	 * PL (original): Jaki był średni czas realizacji zamówienia w każdym roku
	 */
	@Query("select new org.javatuples.Pair(" +
				   "year(o.orderDate), avg(o.shippedDate- o.orderDate)) " +
				   "from Order o " +
				   "group by year(o.orderDate) " +
				   "order by year(o.orderDate)")
	List<Pair<Integer, Double>> avgRealisationTimeInDaysByYear();
}