package pl.edu.agh.db2.northwind.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.db2.northwind.dao.SupplierRepository;
import pl.edu.agh.db2.northwind.oxm.XmlConverter;

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
	}
}
