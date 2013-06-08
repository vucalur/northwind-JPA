package pl.edu.agh.db2.northwind.dao;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.agh.db2.northwind.model.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>, OrderDetailRepositoryCustom {

	/**
	 * EN: How many product units from each of the suppliers were sold
	 * PL (original): Ile sztuk produktów od każdego z dostawców udało się sprzedać
	 */
	@Query("select new org.javatuples.Pair(" +
				   "sum(od.quantity), s.companyName) " +
				   "from OrderDetail od join od.product.supplier s " +
				   "group by s.id")
	List<Pair<Integer, String>> productQuantitySumBySupplier();

	/**
	 * EN: What order total was submitted on each weekday
	 * PL (original): Jaka kwota zamówień była zgłaszana w każdy z dni tygodnia
	 */
	@Query("select new org.javatuples.Pair(" +
				   "extract(dow from o.orderDate), sum(od.quantity * (od.unitPrice * (1-od.discount)))) " +
				   "from OrderDetail od join od.order o " +
				   "group by extract(dow from o.orderDate) " +
				   "order by extract(dow from o.orderDate)")
	List<Pair<Integer, Double>> ordersTotalByWeekDay();

	/**
	 * EN: What was the total value of products ordered from each country for each year; we mean customer's country
	 * PL (original): Jaka była wartość produktów zamówionych z każdego z krajów w każdym roku; chodzi o kraj zamawiającego
	 */
	@Query("select new org.javatuples.Triplet(" +
				   "sum(od.quantity * (od.unitPrice * (1-od.discount))), year(o.orderDate), c.country) " +
				   "from OrderDetail od join od.order o join o.customer c " +
				   "group by c.country, year(o.orderDate)")
	List<Triplet<Double, Integer, String>> ordersTotalByYearByCustomerCountry();

	/**
	 * EN: What was the average value of product unit shipped by each shipper for each year; we mean customer's country
	 * PL (original): Jaka była średnia wartość jednej sztuki produktu dla każdego ze spedytorów w każdym roku
	 */
	@Query("select new org.javatuples.Triplet(" +
				   "sum(od.unitPrice * od.quantity) / sum(od.quantity), year(o.orderDate), s.id) " +
				   "from OrderDetail od join od.order o join o.shipper s " +
				   "group by s.id, year(o.orderDate)")
	List<Triplet<Double, Integer, Integer>> avgUnitPriceByShipperByYear();
}