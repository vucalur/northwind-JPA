package pl.edu.agh.db2.northwind.dao.factory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class SimpleJpaRepositoriesFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

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
			return new SimpleJpaRepository<T, I>((Class<T>) metadata.getDomainType(), entityManager);
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return JpaRepository.class;
		}
	}
}