package pl.edu.agh.db2.northwind.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface FlushingEveryNRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	@Override
	<S extends T> List<S> save(Iterable<S> entities);
}