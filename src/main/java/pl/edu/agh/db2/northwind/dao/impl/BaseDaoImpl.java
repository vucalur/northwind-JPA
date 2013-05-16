package pl.edu.agh.db2.northwind.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.edu.agh.db2.northwind.dao.BaseDao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {
	protected HibernateTemplate hibernateTemplate;

	protected EntityManager entityManager;

	private Class<T> entityClass;

	public BaseDaoImpl() {
		this.entityClass = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	/*@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}*/

	/*@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}*/

	public T create(final T t) {
		hibernateTemplate.persist(t);
		return t;
	}

	public void edit(T entity) {
		hibernateTemplate.merge(entity);
	}

	public void delete(T entity) {
		this.hibernateTemplate.delete(entity);
	}

	public T find(final Serializable id) {
		return (T) this.hibernateTemplate.get(entityClass, id);
	}

	public T update(final T t) {
		return this.hibernateTemplate.merge(t);
	}

	public List<T> findAll() {
		return hibernateTemplate.findByCriteria(DetachedCriteria
														.forClass(entityClass));
	}
}
