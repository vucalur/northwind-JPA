package pl.edu.agh.db2.northwind.oxm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.db2.northwind.model.Product;
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
public class XmlConverterTestProduct {

	private static final Random RANDOM = new Random();

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private File xmlTmpFile;

	private List<Product> pattern;

	@Autowired
	private XmlConverter converter;

	@Before
	public void setUp() {
		try {
			xmlTmpFile = tempFolder.newFile("products.xml");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		pattern = new ArrayList<>();
		for (String s : new String[]{"A", "B", "C"}) {
			Product product = new Product(RANDOM.nextInt(), null, null, s, RANDOM.nextBoolean(), multiply(s, 2), RANDOM.nextFloat(),
										  RANDOM.nextInt(), RANDOM.nextInt(), RANDOM.nextInt());
			product.setCategoryId(RANDOM.nextInt());
			product.setSupplierId(RANDOM.nextInt());

			pattern.add(product);
		}
	}

	@Test
	public void testLoadFromXmlSame() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<Product> unmarshalled = ((ListHolder<Product>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		assertEquals(unmarshalled, pattern);
	}

	@Test
	public void testLoadFromXmlDiffer() {
		converter.writeToXml(new ListHolder<>(pattern), xmlTmpFile.getPath());
		List<Product> unmarshalled = ((ListHolder<Product>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		String s = "D";
		Product additional = new Product(RANDOM.nextInt(), null, null, s, RANDOM.nextBoolean(), multiply(s, 2), RANDOM.nextFloat(),
										 RANDOM.nextInt(), RANDOM.nextInt(), RANDOM.nextInt());
		additional.setCategoryId(RANDOM.nextInt());
		additional.setSupplierId(RANDOM.nextInt());

		unmarshalled.add(additional);

		assertNotEquals(unmarshalled, pattern);
	}
}
