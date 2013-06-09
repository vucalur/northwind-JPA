package pl.edu.agh.db2.northwind.dao.factory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import pl.edu.agh.db2.northwind.dao.FlushingEveryNRepository;
import pl.edu.agh.db2.northwind.dao.FlushingEveryNRepositoryImpl;
import pl.edu.agh.db2.northwind.model.OrderDetail;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * For entities Order and OrderDetail produces FlushingEveryNRepository. <br/>
 * For any other entities: SimpleJpaRepository.
 */
public class FlushingRepositoriesFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new MyRepositoryFactory(entityManager);
	}

	private static class MyRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

		private EntityManager entityManager;

		public MyRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		protected Object getTargetRepository(RepositoryMetadata metadata) {
			if (metadata.getDomainType().equals(pl.edu.agh.db2.northwind.model.Order.class) || metadata.getDomainType().equals(OrderDetail.class)) {
				return new FlushingEveryNRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
			} else {
				return new SimpleJpaRepository<T, I>((Class<T>) metadata.getDomainType(), entityManager);
			}
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			if (metadata.getDomainType().equals(pl.edu.agh.db2.northwind.model.Order.class) || metadata.getDomainType().equals(OrderDetail.class)) {
				return FlushingEveryNRepository.class;
			} else {
				return JpaRepository.class;
			}
		}
	}
}