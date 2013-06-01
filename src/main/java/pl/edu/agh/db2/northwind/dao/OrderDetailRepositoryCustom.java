package pl.edu.agh.db2.northwind.dao;

import pl.edu.agh.db2.northwind.dao.workaround.GenericRelationAwareRepository;
import pl.edu.agh.db2.northwind.model.OrderDetail;

public interface OrderDetailRepositoryCustom extends GenericRelationAwareRepository<OrderDetail> {
}
