package org.sautel.listmerging.merge;

import java.util.LinkedList;
import java.util.List;

public class BufferedListWriter implements ListWriter {
	private final List<Integer> values = new LinkedList<>();

	@Override
	public void write(int value) {
		values.add(value);
	}

	public List<Integer> getValues() {
		return values;
	}
}
