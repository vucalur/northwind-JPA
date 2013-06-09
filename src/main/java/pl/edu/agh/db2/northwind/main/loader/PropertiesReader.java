package pl.edu.agh.db2.northwind.main.loader;

import java.io.IOException;
import java.util.Properties;

/**
 * WORKAROUND class for inability of making FlushingEveryNRepositoryImpl a bean and reading the property using:
 * <pre>@Value("${dataloader.flush.batch_size}") private int flushBatchSize;</pre>
 */
public class PropertiesReader {

	public static final int FLUSH_SIZE;

	static {
		Properties prop = new Properties();
		try {
			prop.load(PropertiesReader.class.getClassLoader().getResourceAsStream("properties/loader.properties"));
			FLUSH_SIZE = Integer.parseInt(prop.getProperty("dataloader.flush.batch_size"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
