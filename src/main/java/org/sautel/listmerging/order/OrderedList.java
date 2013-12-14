package org.sautel.listmerging.order;

public interface OrderedList<T> {
	void consumeCurrentElement();

	T getCurrentElement();

	boolean hasCurrentElement();
}
