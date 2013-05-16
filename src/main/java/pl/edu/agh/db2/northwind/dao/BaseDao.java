package pl.edu.agh.db2.northwind.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	T create(T t);

	void delete(T id);

	T find(final Serializable id);

	T update(T t);

	void edit(T entity);

	List<T> findAll();
}
