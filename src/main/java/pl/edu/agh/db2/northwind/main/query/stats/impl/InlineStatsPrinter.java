package pl.edu.agh.db2.northwind.main.query.stats.impl;

import pl.edu.agh.db2.northwind.main.query.stats.StatsPrinter;

import java.io.PrintWriter;

public class InlineStatsPrinter implements StatsPrinter {
	private final PrintWriter out;

	public InlineStatsPrinter(PrintWriter out) {
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

	@Override
	public void close() {
		if (out == null) {
			return;
		}
		out.close();
	}
}
