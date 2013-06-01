package pl.edu.agh.db2.northwind.oxm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.db2.northwind.model.Shipper;
import pl.edu.agh.db2.northwind.oxm.holders.ListHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static pl.edu.agh.db2.northwind.utils.StringUtils.multiply;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class XmlConverterTestShipper {

	private static final Random RANDOM = new Random();

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private File xmlTmpFile;

	private List<Shipper> pattern;

	@Autowired
	private XmlConverter converter;

	@Before
	public void setUp() {
		try {
			xmlTmpFile = tempFolder.newFile("shippers.xml");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		pattern = new ArrayList<>();
		for (String s : new String[]{"A", "B", "C"}) {
			Shipper shipper = new Shipper(RANDOM.nextInt(), s, multiply(s, 2));
			pattern.add(shipper);
		}
	}

	@Test
	public void testLoadFromXmlSame() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<Shipper> unmarshalled = ((ListHolder<Shipper>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		assertEquals(unmarshalled, pattern);
	}

	@Test
	public void testLoadFromXmlDiffer() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<Shipper> unmarshalled = ((ListHolder<Shipper>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		Shipper additional = new Shipper(123, "sth", "12 345 6789");
		unmarshalled.add(additional);

		assertNotEquals(unmarshalled, pattern);
	}
}
