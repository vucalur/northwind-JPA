package pl.edu.agh.db2.northwind.main.query.stats.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.edu.agh.db2.northwind.main.query.stats.StatsPrinter;

import java.io.PrintWriter;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class VerboseStatsPrinter implements StatsPrinter {

	private PrintWriter out;

	@Required
	public void setOut(PrintWriter out) {
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
		out.println("###############################################");
	}
}
