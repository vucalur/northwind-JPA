package pl.edu.agh.db2.northwind.dao;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import pl.edu.agh.db2.northwind.main.loader.PropertiesReader;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FlushingEveryNRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements FlushingEveryNRepository<T, ID> {

	private EntityManager entityManager;

	public FlushingEveryNRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		List<S> result = new ArrayList<>();

		if (entities == null) {
			return result;
		}

		int counter = 0;
		for (S s : entities) {
			result.add(save(s));
			if (++counter % PropertiesReader.FLUSH_SIZE == 0) {
				entityManager.flush();
				entityManager.clear();
				counter = 0;
			}
		}

		return result;
	}
}