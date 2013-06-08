package pl.edu.agh.db2.northwind.main.query.results;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.List;

public interface ResultsPrinter {
	void print1(List<Pair<String, Integer>> result);

	void print2(List<Pair<Integer, Double>> result);

	void print3(List<Pair<Integer, String>> result);

	void print4(List<Pair<Integer, Double>> result);

	void print5(List<Triplet<Double, Integer, String>> result);

	void print6(List<Triplet<Double, Integer, Integer>> result);
}
