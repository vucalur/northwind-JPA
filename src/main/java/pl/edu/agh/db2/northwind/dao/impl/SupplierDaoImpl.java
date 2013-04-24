package pl.edu.agh.db2.northwind.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.db2.northwind.dao.SupplierDao;
import pl.edu.agh.db2.northwind.model.Supplier;
import pl.edu.agh.db2.northwind.model.Supplier_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SupplierDaoImpl implements SupplierDao {

    protected EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public List<Supplier> getSuppliers() throws DataAccessException {
        Query query = entityManager.createQuery("select c from Supplier c");
        List<Supplier> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public Supplier getSupplier(Integer carId) throws DataAccessException {
        return entityManager.find(Supplier.class, carId);
    }

    @Override
    @Transactional
    public List<Supplier> getSuppliesCompanyNameStartsWith(String prefix) throws DataAccessException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Supplier> cq = cb.createQuery(Supplier.class);
        Root<Supplier> root = cq.from(Supplier.class);
        cq.where(cb.like(root.get(Supplier_.companyName), prefix + "%"));
        cq.select(root);
        TypedQuery<Supplier> q = entityManager.createQuery(cq);
        return q.getResultList();
    }

    @Override
    @Transactional
    public void persist(Supplier supplier) throws DataAccessException {
        entityManager.persist(supplier);
    }
}