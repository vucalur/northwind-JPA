package pl.edu.agh.db2.northwind.utils;

public class StringUtils {
	public static String multiply(String s, int times) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < times; ++i) {
			builder.append(s);
		}
		return builder.toString();
	}
}
