package pl.edu.agh.db2.northwind.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.db2.northwind.dao.SupplierDao;
import pl.edu.agh.db2.northwind.model.Supplier;

public class MainApp {

	public static void main(String[] args) {
		new MainApp().run();
	}

	void run() {
		ApplicationContext appContext =
				new ClassPathXmlApplicationContext("context.xml");

		SupplierDao dao = (SupplierDao) appContext.getBean("supplierDao");

//        Supplier supplier = new Supplier();
//        supplier.setCompanyName("Y");
//        System.out.println("1");
//        dao.persist(supplier);
//        System.out.println("2");
//        supplier = new Supplier();
//        supplier.setCompanyName("Z");
//        System.out.println("3");
//        dao.persist(supplier);
//        System.out.println("4");

		System.out.println(dao.getSupplier(1));
		System.out.println("%%%%%%%%%%%%%%%%%%%%%");
		for (Supplier s : dao.getSuppliesCompanyNameStartsWith("P")) {
			System.out.println(s.getCompanyName() + "     " + s.getId());
		}
		System.out.println("Done");
	}
}
