package pl.edu.agh.db2.northwind.main.query.results.impl;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.edu.agh.db2.northwind.main.query.results.ResultsPrinter;

import java.io.PrintWriter;
import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ResultsPrinterImpl implements ResultsPrinter {

	private PrintWriter out;

	@Required
	public void setOut(PrintWriter out) {
		this.out = out;
	}

	@Override
	public void print1(List<Pair<String, Integer>> result) {
		for (Pair<String, Integer> p : result) {
			out.println(String.format("%15s\t%8d", p.getValue0(), p.getValue1()));
		}
		out.println("\n");
	}

	@Override
	public void print2(List<Pair<Integer, Double>> result) {
		for (Pair<Integer, Double> p : result) {
			out.println(String.format("%8d\t%8.4f", p.getValue0(), p.getValue1()));
		}
		out.println("\n");
	}

	@Override
	public void print3(List<Pair<Integer, String>> result) {
		for (Pair<Integer, String> p : result) {
			out.println(String.format("%8d\t%25s", p.getValue0(), p.getValue1()));
		}
		out.println("\n");
	}

	@Override
	public void print4(List<Pair<Integer, Double>> result) {
		for (Pair<Integer, Double> p : result) {
			out.println(String.format("%8s\t%10f", p.getValue0(), p.getValue1()));
		}
		out.println("\n");
	}

	@Override
	public void print5(List<Triplet<Double, Integer, String>> result) {
		for (Triplet<Double, Integer, String> p : result) {
			out.println(String.format("%10f\t%8d\t%12s", p.getValue0(), p.getValue1(), p.getValue2()));
		}
		out.println("\n");
	}

	@Override
	public void print6(List<Triplet<Double, Integer, Integer>> result) {
		for (Triplet<Double, Integer, Integer> p : result) {
			out.println(String.format("%10f\t%8d\t%8d", p.getValue0(), p.getValue1(), p.getValue2()));
		}
		out.println("\n###############################################");
	}
}
