package org.sautel.listmerging.order;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class BufferedOrderedList<T extends Comparable<T>> implements
		OrderedList<T> {

	private final List<T> orderedValues;
	private int currentIndex;

	@SafeVarargs
	public BufferedOrderedList(T... values) {
		orderedValues = Ordering.natural().sortedCopy(
				Lists.<T> newArrayList(values));
	}

	@Override
	public void consumeCurrentElement() {
		currentIndex++;
	}

	@Override
	public T getCurrentElement() {
		return orderedValues.get(currentIndex);
	}

	@Override
	public boolean hasCurrentElement() {
		return currentIndex < orderedValues.size();
	}
}
