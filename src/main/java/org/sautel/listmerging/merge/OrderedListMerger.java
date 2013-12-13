package org.sautel.listmerging.merge;

import static com.google.common.collect.Collections2.filter;

import java.util.Collection;
import java.util.List;

import org.sautel.listmerging.order.OrderedList;

import com.google.common.base.Predicate;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class OrderedListMerger {
	private static final class OrderedListOrdering extends
			Ordering<OrderedList> {
		@Override
		public int compare(OrderedList list1, OrderedList list2) {
			return Ints.compare(list1.getCurrentElement(),
					list2.getCurrentElement());
		}
	}

	private static final class ActiveInputListPredicate implements
			Predicate<OrderedList> {
		@Override
		public boolean apply(OrderedList input) {
			return input.hasCurrentElement();
		}
	}

	private static final OrderedListOrdering orderedListComparator = new OrderedListOrdering();
	private static final ActiveInputListPredicate activeInputListPredicate = new ActiveInputListPredicate();

	public void merge(List<OrderedList> inputLists, ListWriter writer) {
		Collection<OrderedList> activeInputLists = filterActiveInputLists(inputLists);
		while (!activeInputLists.isEmpty()) {
			OrderedList minInputList = orderedListComparator
					.min(activeInputLists);
			writer.write(minInputList.getCurrentElement());
			minInputList.consumeCurrentElement();
			activeInputLists = filterActiveInputLists(inputLists);
		}
	}

	private Collection<OrderedList> filterActiveInputLists(
			List<OrderedList> inputLists) {
		return filter(inputLists, activeInputListPredicate);
	}
}
