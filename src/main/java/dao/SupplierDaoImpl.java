package dao;

import model.Supplier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SupplierDaoImpl implements SupplierDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Supplier> getSuppliers() throws DataAccessException {
        Query query = getEntityManager().createQuery("select c from Supplier c");
        List<Supplier> resultList = query.getResultList();
        return resultList;
    }

    @Transactional
    public Supplier getSupplier(Long carId) throws DataAccessException {
        return getEntityManager().find(Supplier.class, carId);
    }
}