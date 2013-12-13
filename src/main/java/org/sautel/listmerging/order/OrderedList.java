package org.sautel.listmerging.order;

public interface OrderedList {
	void consumeCurrentElement();

	int getCurrentElement();

	boolean hasCurrentElement();
}
