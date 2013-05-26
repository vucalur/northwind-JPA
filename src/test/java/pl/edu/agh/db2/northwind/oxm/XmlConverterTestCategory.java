package pl.edu.agh.db2.northwind.oxm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.db2.northwind.model.Category;
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
public class XmlConverterTestCategory {
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private File xmlTmpFile;

	private List<Category> categoriesPattern;

	@Autowired
	private XmlConverter converter;

	@Before
	public void setUp() {
		try {
			xmlTmpFile = tempFolder.newFile("categories.xml");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		categoriesPattern = new ArrayList<>();
		for (String s : new String[]{"A", "B", "C"}) {
			Category category = new Category(new Random().nextInt(), s, s + s);
			categoriesPattern.add(category);
		}
	}

	@Test
	public void testLoadFromXmlSame() {
		converter.writeToXml(new ListHolder<>(categoriesPattern), xmlTmpFile.getPath());
		List<Category> unmarshalledCategories = ((ListHolder<Category>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		assertEquals(unmarshalledCategories, categoriesPattern);
	}

	@Test
	public void testLoadFromXmlDiffer() {
		converter.writeToXml(new ListHolder<>(categoriesPattern), xmlTmpFile.getPath());
		List<Category> unmarshalledCategories = ((ListHolder<Category>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		Category additionalCategory = new Category(123, "sth", "sth desc");
		unmarshalledCategories.add(additionalCategory);

		assertNotEquals(unmarshalledCategories, categoriesPattern);
	}
}
