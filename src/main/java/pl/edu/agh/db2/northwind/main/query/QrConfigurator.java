package pl.edu.agh.db2.northwind.main.query;

import org.apache.commons.io.output.NullOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.db2.northwind.main.query.results.ResultsPrinter;
import pl.edu.agh.db2.northwind.main.query.results.impl.ResultsPrinterImpl;
import pl.edu.agh.db2.northwind.main.query.stats.StatsPrinter;
import pl.edu.agh.db2.northwind.main.query.stats.impl.InlineStatsPrinter;
import pl.edu.agh.db2.northwind.main.query.stats.impl.VerboseStatsPrinter;

import java.io.*;

@Component
public class QrConfigurator {

	@Autowired
	private QrProperties properties;

	public ResultsPrinter getResultsPrinter() {
		String type = properties.getOutputTypeResults();
		String filePath = properties.getPathResults();
		boolean append = properties.isAppendResults();

		switch (type) {
			case "none":
				return new ResultsPrinterImpl(new PrintWriter(new BufferedWriter(new OutputStreamWriter(NullOutputStream.NULL_OUTPUT_STREAM))));
			case "stdout":
				return new ResultsPrinterImpl(new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out))));
			case "file":
				try {
					return new ResultsPrinterImpl(new PrintWriter(new BufferedWriter(new FileWriter(filePath, append))));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			default:
				return null;
		}
	}

	public StatsPrinter getStatsPrinter() {
		String type = properties.getOutputTypeStats();
		String filePath = properties.getPathStats();
		boolean append = properties.isAppendStats();

		try {
			switch (type) {
				case "stdout":
					return new VerboseStatsPrinter(new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out))));
				case "file":
					return new VerboseStatsPrinter(new PrintWriter(new BufferedWriter(new FileWriter(filePath, append))));
				case "fileInline":
					return new InlineStatsPrinter(new PrintWriter(new BufferedWriter(new FileWriter(filePath, append))));
				default:
					return null;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
