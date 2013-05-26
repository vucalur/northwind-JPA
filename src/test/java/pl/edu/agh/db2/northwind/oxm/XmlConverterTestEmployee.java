package pl.edu.agh.db2.northwind.oxm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.db2.northwind.model.Employee;
import pl.edu.agh.db2.northwind.oxm.holders.ListHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class XmlConverterTestEmployee {
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private File xmlTmpFile;

	private List<Employee> employeesPattern;

	@Autowired
	private XmlConverter converter;

	@Before
	public void setUp() {
		try {
			xmlTmpFile = tempFolder.newFile("employees.xml");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String[] firstNames = new String[]{"John", "Kelly", "Jean"};
		String[] lastNames = new String[]{"Doe", "Smith", "McGregor"};
		String[] addresses = new String[]{"somewhere1", "somewhere2", "somewhere3"};
		String[] postalCodes = new String[]{"123456", "123456", "123456"};
		String[] phones = new String[]{"123456789", "123456789", "123456789"};
		String[] extensions = new String[]{"1", "2", "3"};
		employeesPattern = new ArrayList<>();
		for (int i = 0; i < firstNames.length; ++i) {
			Employee employee = new Employee(i, firstNames[i], lastNames[i], firstNames[i], new Date(), firstNames[i], new Date(), addresses[i],
											 addresses[i], addresses[i], postalCodes[i], addresses[i], phones[i], extensions[i], firstNames[i], 1);
			employeesPattern.add(employee);
		}
	}

	@Test
	public void testLoadFromXmlSame() {
		converter.writeToXml(new ListHolder<>(employeesPattern), xmlTmpFile.getPath());
		List<Employee> unmarshalledEmployees = ((ListHolder<Employee>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		assertEquals(unmarshalledEmployees, employeesPattern);
	}

	@Test
	public void testLoadFromXmlDiffer() {
		converter.writeToXml(new ListHolder<>(employeesPattern), xmlTmpFile.getPath());
		List<Employee> unmarshalledEmployees = ((ListHolder<Employee>) converter.loadFromXml(xmlTmpFile.getPath())).getValues();

		Employee additional = new Employee(4, "Someone", "Additional", "Someone", new Date(), "Someone", new Date(), "Somewhere",
										   "Somewhere", "Somewhere", "654321", "Somewhere", "987654321", "4", "Someone", 1);
		employeesPattern.add(additional);

		assertNotEquals(unmarshalledEmployees, employeesPattern);
	}
}