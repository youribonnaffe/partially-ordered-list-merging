package org.sautel.listmerging.order;

import static java.util.Collections.min;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PartiallyOrderedList implements OrderedList {
	private final Iterator<Integer> iterator;
	private final List<Integer> bufferedValues;

	public PartiallyOrderedList(Iterator<Integer> iterator, int maxPositionError) {
		this.iterator = iterator;
		int bufferSize = maxPositionError + 1;
		bufferedValues = new ArrayList<>(bufferSize);
		for (int i = 0; i < bufferSize; i++) {
			addNextValueIfExists();
		}
	}

	@Override
	public void consumeCurrentElement() {
		int currentValue = getCurrentElement();
		int currentValueIndex = bufferedValues.indexOf(currentValue);
		bufferedValues.remove(currentValueIndex);
		addNextValueIfExists();
	}

	private void addNextValueIfExists() {
		if (iterator.hasNext()) {
			bufferedValues.add(iterator.next());
		}
	}

	@Override
	public int getCurrentElement() {
		return min(bufferedValues);
	}

	@Override
	public boolean hasCurrentElement() {
		return !bufferedValues.isEmpty();
	}
}
