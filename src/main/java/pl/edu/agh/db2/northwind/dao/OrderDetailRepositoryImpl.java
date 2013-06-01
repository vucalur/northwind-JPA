package pl.edu.agh.db2.northwind.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.db2.northwind.model.OrderDetail;

import java.util.List;

public class OrderDetailRepositoryImpl implements OrderDetailRepositoryCustom {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public <S extends OrderDetail> S saveOne(S s) {
		updateRelations(s);
		return orderDetailRepository.save(s);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		for (S s : entities) {
			updateRelations(s);
		}
		return orderDetailRepository.save(entities);
	}

	private <S extends OrderDetail> void updateRelations(S s) {
		s.setProduct(productRepository.findOne(s.getProductId()));
		s.setOrder(orderRepository.findOne(s.getOrderId()));
	}
}
