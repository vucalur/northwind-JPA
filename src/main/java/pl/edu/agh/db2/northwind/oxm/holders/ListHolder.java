package pl.edu.agh.db2.northwind.oxm.holders;

import javax.xml.bind.annotation.XmlAnyElement;
import java.util.ArrayList;
import java.util.List;

public class ListHolder<T> {

	@XmlAnyElement(lax = true)
	private List<T> values = new ArrayList<T>();

	public ListHolder(List<T> values) {
		this.values = values;
	}

	protected ListHolder() {
	}

	public List<T> getValues() {
		return values;
	}
}