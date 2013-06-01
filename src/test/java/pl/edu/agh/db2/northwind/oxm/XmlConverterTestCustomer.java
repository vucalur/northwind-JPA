package pl.edu.agh.db2.northwind.oxm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.db2.northwind.model.Customer;
import pl.edu.agh.db2.northwind.oxm.holders.ListHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static pl.edu.agh.db2.northwind.utils.StringUtils.multiply;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class XmlConverterTestCustomer {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private File xmlTmpFile;

	private List<Customer> pattern;

	@Autowired
	private XmlConverter converter;

	@Before
	public void setUp() {
		try {
			xmlTmpFile = tempFolder.newFile("customers.xml");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		pattern = new ArrayList<>();
		for (String s : new String[]{"A", "B", "C"}) {
			Customer customer = new Customer(s, multiply(s, 2), multiply(s, 3), multiply(s, 4), multiply(s, 5), multiply(s, 6), multiply(s, 7),
											 multiply(s, 8), multiply(s, 9), multiply(s, 10), multiply(s, 11));
			pattern.add(customer);
		}
	}

	@Test
	public void testLoadFromXmlSame() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<Customer> unmarshalled = ((ListHolder<Customer>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		assertEquals(unmarshalled, pattern);
	}

	@Test
	public void testLoadFromXmlDiffer() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<Customer> unmarshalled = ((ListHolder<Customer>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		Customer additional = new Customer("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
		unmarshalled.add(additional);

		assertNotEquals(unmarshalled, pattern);
	}
}
