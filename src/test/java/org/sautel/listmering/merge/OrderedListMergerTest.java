package org.sautel.listmering.merge;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sautel.listmerging.merge.BufferedListWriter;
import org.sautel.listmerging.merge.OrderedListMerger;
import org.sautel.listmerging.order.BufferedOrderedList;
import org.sautel.listmerging.order.OrderedList;

public class OrderedListMergerTest {
	private BufferedListWriter writer;
	private OrderedListMerger orderedListMerger;

	@Before
	public void setUp() {
		writer = new BufferedListWriter();
		orderedListMerger = new OrderedListMerger();
	}

	@Test
	public void noInputList() {
		orderedListMerger.merge(Collections.<OrderedList> emptyList(), writer);

		assertTrue(writer.getValues().isEmpty());
	}

	@Test
	public void oneInputList() {
		List<OrderedList> inputsList = Arrays
				.<OrderedList> asList(new BufferedOrderedList(1));

		orderedListMerger.merge(inputsList, writer);

		assertThat(writer.getValues(), contains(1));
	}

	@Test
	public void twoInputLists() {
		List<OrderedList> inputsList = Arrays.<OrderedList> asList(
				new BufferedOrderedList(1), new BufferedOrderedList(2));

		orderedListMerger.merge(inputsList, writer);

		assertThat(writer.getValues(), contains(1, 2));
	}

	@Test
	public void twoInputListsWithSeveralValue() {
		List<OrderedList> inputsList = Arrays.<OrderedList> asList(
				new BufferedOrderedList(1, 3, 4, 7), new BufferedOrderedList(2,
						5, 6));

		orderedListMerger.merge(inputsList, writer);

		assertThat(writer.getValues(), contains(1, 2, 3, 4, 5, 6, 7));
	}
}
