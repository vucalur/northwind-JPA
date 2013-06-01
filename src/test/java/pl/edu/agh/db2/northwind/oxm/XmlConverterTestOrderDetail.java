package pl.edu.agh.db2.northwind.oxm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.db2.northwind.model.OrderDetail;
import pl.edu.agh.db2.northwind.oxm.holders.ListHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class XmlConverterTestOrderDetail {

	private static final Random RANDOM = new Random();

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private File xmlTmpFile;

	private List<OrderDetail> pattern;

	@Autowired
	private XmlConverter converter;

	@Before
	public void setUp() {
		try {
			xmlTmpFile = tempFolder.newFile("orderdetails.xml");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		pattern = new ArrayList<>();
		for (String s : new String[]{"A", "B", "C"}) {
			OrderDetail orderDetail = new OrderDetail(RANDOM.nextInt(), null, null, (float) 1.23, RANDOM.nextInt(100), (float) 0.32);
			orderDetail.setProductId(RANDOM.nextInt());
			orderDetail.setOrderId(RANDOM.nextInt());

			pattern.add(orderDetail);
		}
	}

	@Test
	public void testLoadFromXmlSame() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<OrderDetail> unmarshalled = ((ListHolder<OrderDetail>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		assertEquals(unmarshalled, pattern);
	}

	@Test
	public void testLoadFromXmlDiffer() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<OrderDetail> unmarshalled = ((ListHolder<OrderDetail>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		String s = "D";
		OrderDetail additional = new OrderDetail(RANDOM.nextInt(), null, null, (float) 1.23, RANDOM.nextInt(100), (float) 0.32);
		additional.setProductId(RANDOM.nextInt());
		additional.setOrderId(RANDOM.nextInt());

		unmarshalled.add(additional);

		assertNotEquals(unmarshalled, pattern);
	}
}
