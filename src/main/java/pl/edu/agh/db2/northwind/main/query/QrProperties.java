package pl.edu.agh.db2.northwind.main.query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QrProperties {

	@Value("${queryRunner.queriesToExecute}")
	private String queriesToExecuteStr;

	@Value("${queryRunner.results.out.append}")
	private boolean appendResults;

	@Value("${queryRunner.results.out.type.file.path}")
	private String pathResults;

	@Value("${queryRunner.results.out.type}")
	private String outputTypeResults;

	@Value("${queryRunner.stats.out.append}")
	private boolean appendStats;

	@Value("${queryRunner.stats.out.type.file.path}")
	private String pathStats;

	@Value("${queryRunner.stats.out.type}")
	private String outputTypeStats;

	public String getQueriesToExecuteStr() {
		return queriesToExecuteStr;
	}

	public boolean isAppendResults() {
		return appendResults;
	}

	public String getPathResults() {
		return pathResults;
	}

	public String getOutputTypeResults() {
		return outputTypeResults;
	}

	public boolean isAppendStats() {
		return appendStats;
	}

	public String getPathStats() {
		return pathStats;
	}

	public String getOutputTypeStats() {
		return outputTypeStats;
	}
}
