package org.sautel.listmerging.merge;

import java.util.LinkedList;
import java.util.List;

public class BufferedListWriter<T> implements ListWriter<T> {
	private final List<T> values = new LinkedList<>();

	@Override
	public void write(T value) {
		values.add(value);
	}

	public List<T> getValues() {
		return values;
	}
}
