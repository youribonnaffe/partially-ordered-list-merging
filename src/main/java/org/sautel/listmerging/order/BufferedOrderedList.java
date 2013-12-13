package org.sautel.listmerging.order;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class BufferedOrderedList implements OrderedList {

	private final List<Integer> orderedValues;
	private int currentIndex;

	public BufferedOrderedList(Integer... values) {
		orderedValues = Ordering.natural().sortedCopy(
				Lists.<Integer> newArrayList(values));
	}

	@Override
	public void consumeCurrentElement() {
		currentIndex++;
	}

	@Override
	public int getCurrentElement() {
		return orderedValues.get(currentIndex);
	}

	@Override
	public boolean hasCurrentElement() {
		return currentIndex < orderedValues.size();
	}
}
