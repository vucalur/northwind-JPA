package pl.edu.agh.db2.northwind.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.db2.northwind.dao.SupplierRepository;
import pl.edu.agh.db2.northwind.model.Category;
import pl.edu.agh.db2.northwind.oxm.XmlConverter;
import pl.edu.agh.db2.northwind.oxm.holders.ListHolder;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MainApp {

	@Autowired
	private SupplierRepository dao;

	@Autowired
	private XmlConverter converter;

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");

		MainApp mainApp = appContext.getBean(MainApp.class);
		mainApp.start(args);
	}

	private void start(String[] args) {
		List<Category> categories = new ArrayList<>();
		for (String s : new String[]{"A", "B", "C"}) {
			Category category = new Category();
			category.setId(new Random().nextInt());
			category.setCategoryName(s);
			category.setDescription(s + s);
			categories.add(category);
		}

		// Marshalling only:
		JAXBElement<ListHolder> jaxbElement = new JAXBElement<>(new QName(null, "Categories"), ListHolder.class, new ListHolder<>(categories));
		converter.writeToXml(jaxbElement, "sampleExportedCategories.xml");
	}
}
