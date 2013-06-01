package pl.edu.agh.db2.northwind.oxm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.db2.northwind.model.Order;
import pl.edu.agh.db2.northwind.oxm.holders.ListHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static pl.edu.agh.db2.northwind.utils.StringUtils.multiply;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class XmlConverterTestOrder {

	private static final Random RANDOM = new Random();

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private File xmlTmpFile;

	private List<Order> pattern;

	@Autowired
	private XmlConverter converter;

	@Before
	public void setUp() {
		try {
			xmlTmpFile = tempFolder.newFile("orders.xml");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		pattern = new ArrayList<>();
		for (String s : new String[]{"A", "B", "C"}) {
			Order order = new Order(RANDOM.nextInt(), null, null, new Date(), new Date(), new Date(), null, (float) 1.23, s, multiply(s, 2),
									multiply(s, 3), multiply(s, 4), multiply(s, 5), multiply(s, 6));
			order.setCustomerId(multiply(s, RANDOM.nextInt(10)));
			order.setEmployeeId(RANDOM.nextInt());
			order.setShipperId(RANDOM.nextInt());

			pattern.add(order);
		}
	}

	@Test
	public void testLoadFromXmlSame() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<Order> unmarshalled = ((ListHolder<Order>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		assertEquals(unmarshalled, pattern);
	}

	@Test
	public void testLoadFromXmlDiffer() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<Order> unmarshalled = ((ListHolder<Order>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		String s = "D";
		Order additional = new Order(RANDOM.nextInt(), null, null, new Date(), new Date(), new Date(), null, (float) 1.23, s, multiply(s, 2),
									 multiply(s, 3), multiply(s, 4), multiply(s, 5), multiply(s, 6));
		additional.setCustomerId(multiply(s, RANDOM.nextInt(10)));
		additional.setEmployeeId(RANDOM.nextInt());
		additional.setShipperId(RANDOM.nextInt());

		unmarshalled.add(additional);

		assertNotEquals(unmarshalled, pattern);
	}
}
