package pl.edu.agh.db2.northwind.oxm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
							XmlConverterTestCategory.class,
							XmlConverterTestCustomer.class,
							XmlConverterTestEmployee.class,
							XmlConverterTestOrder.class,
							XmlConverterTestOrderDetail.class,
							XmlConverterTestProduct.class,
							XmlConverterTestShipper.class,
							XmlConverterTestSupplier.class,
					})
public class XmlConverterSuite {
}