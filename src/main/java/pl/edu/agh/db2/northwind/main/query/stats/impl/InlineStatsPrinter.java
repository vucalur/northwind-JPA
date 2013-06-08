package pl.edu.agh.db2.northwind.main.query.stats.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.edu.agh.db2.northwind.main.query.stats.StatsPrinter;

import java.io.PrintWriter;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class InlineStatsPrinter implements StatsPrinter {
	private PrintWriter out;

	@Required
	public void setOut(PrintWriter out) {
		this.out = out;
	}

	@Override
	public void print1(long timeInMillis) {
		out.print(String.format("%d", timeInMillis));
	}

	@Override
	public void print2(long timeInMillis) {
		printWithTab(timeInMillis, out);
	}

	@Override
	public void print3(long timeInMillis) {
		printWithTab(timeInMillis, out);
	}

	@Override
	public void print4(long timeInMillis) {
		printWithTab(timeInMillis, out);
	}

	@Override
	public void print5(long timeInMillis) {
		printWithTab(timeInMillis, out);
	}

	@Override
	public void print6(long timeInMillis) {
		printWithTab(timeInMillis, out);
		out.println("");
	}

	private void printWithTab(long timeInMillis, PrintWriter out) {
		out.print(String.format("\t%d", timeInMillis));
	}
}
