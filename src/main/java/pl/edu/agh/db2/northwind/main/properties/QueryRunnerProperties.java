package pl.edu.agh.db2.northwind.main.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueryRunnerProperties {

	@Value("${queryRunner.queriesToExecute}")
	private String queriesToExecuteStr;

	@Value("${queryRunner.results.show}")
	private boolean showResults;

	@Value("${queryRunner.results.out.append}")
	private boolean appendResults;

	@Value("${queryRunner.results.out.path}")
	private String pathResults;

	@Value("${queryRunner.results.out.type}")
	private String outputTypeResults;

	@Value("${queryRunner.stats.out.append}")
	private boolean appendStats;

	@Value("${queryRunner.stats.out.path}")
	private String pathStats;

	@Value("${queryRunner.stats.out.type}")
	private String outputTypeStats;

	public String getQueriesToExecuteStr() {
		return queriesToExecuteStr;
	}

	public boolean isShowResults() {
		return showResults;
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
