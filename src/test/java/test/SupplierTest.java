package test;

import dao.SupplierDao;
import model.Supplier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.logging.Logger;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/src/test/resources/testContext.xml"})
public class SupplierTest {

    @Autowired
    private SupplierDao supplierDao;
    private Logger logger = Logger.getLogger("myLog");
    private Long id;

    @Before
    public void init() {
        id = 1L;
    }

    @Test
    public void listSuppliersTest() {
        List<Supplier> suppliers = supplierDao.getSuppliers();
//      logger.info("Cars: " + cars.size());
        Assert.assertNotNull(suppliers);
        Assert.assertEquals(29, suppliers.size());
    }

    @Test
    public void getSupplierTest() {
        Supplier supplier = supplierDao.getSupplier(id);
        Assert.assertEquals((Object) id.longValue(), supplier.getSupplierID());
        Assert.assertEquals("Exotic Liquids", supplier.getCompanyName());
    }
}