package pl.edu.agh.db2.northwind.main.query.stats;

public interface StatsPrinter extends AutoCloseable {
	void print1(long timeInMillis);

	void print2(long timeInMillis);

	void print3(long timeInMillis);

	void print4(long timeInMillis);

	void print5(long timeInMillis);

	void print6(long timeInMillis);

	@Override
	void close();
}
