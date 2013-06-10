package pl.edu.agh.db2.northwind.main.query.stats.impl;

import pl.edu.agh.db2.northwind.main.query.stats.StatsPrinter;

import java.io.PrintWriter;

public class VerboseStatsPrinter implements StatsPrinter {

	private final PrintWriter out;

	public VerboseStatsPrinter(PrintWriter out) {
		this.out = out;
	}

	@Override
	public void print1(long timeInMillis) {
		out.println(String.format("%-34s\t%8d ms", "totalOrdersByCountry", timeInMillis));
	}

	@Override
	public void print2(long timeInMillis) {
		out.println(String.format("%-34s\t%8d ms", "avgRealisationTimeInDaysByYear", timeInMillis));
	}

	@Override
	public void print3(long timeInMillis) {
		out.println(String.format("%-34s\t%8d ms", "productQuantitySumBySupplier", timeInMillis));
	}

	@Override
	public void print4(long timeInMillis) {
		out.println(String.format("%-34s\t%8d ms", "ordersTotalByWeekDay", timeInMillis));
	}

	@Override
	public void print5(long timeInMillis) {
		out.println(String.format("%-34s\t%8d ms", "ordersTotalByYearByCustomerCountry", timeInMillis));
	}

	@Override
	public void print6(long timeInMillis) {
		out.println(String.format("%-34s\t%8d ms", "avgUnitPriceByShipperByYear", timeInMillis));
	}

	@Override
	public void printFinishTime(long timeInMillis) {
		out.println(String.format("%-34s\t%8d ms", "application totalTime", timeInMillis));
		out.println("###############################################");
	}

	@Override
	public void close() {
		if (out == null) {
			return;
		}
		out.close();
	}
}
