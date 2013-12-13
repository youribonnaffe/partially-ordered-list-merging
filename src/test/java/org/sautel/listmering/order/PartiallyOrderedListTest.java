package org.sautel.listmering.order;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.sautel.listmerging.order.PartiallyOrderedList;

public class PartiallyOrderedListTest {
	@Test
	public void emptyList() {
		PartiallyOrderedList list = buildPartiallyOrderedList(3);

		assertFalse(list.hasCurrentElement());
	}

	private PartiallyOrderedList buildPartiallyOrderedList(
			int maxPositionError, Integer... values) {
		List<Integer> valuesList = newArrayList(values);
		return new PartiallyOrderedList(valuesList.iterator(), maxPositionError);
	}

	@Test
	public void oneElementList() {
		PartiallyOrderedList list = buildPartiallyOrderedList(3, 1);

		assertValuesAre(list, 1);
	}

	private void assertValuesAre(PartiallyOrderedList list,
			int... expectedValues) {
		for (int i = 0; i < expectedValues.length; i++) {
			assertTrue(list.hasCurrentElement());
			assertEquals(expectedValues[i], list.getCurrentElement());
			list.consumeCurrentElement();
		}
		assertFalse(list.hasCurrentElement());
	}

	@Test
	public void twoOrderedElementsList() {
		PartiallyOrderedList list = buildPartiallyOrderedList(3, 1, 2);

		assertValuesAre(list, 1, 2);
	}

	@Test
	public void twoUnorderedElementsListWithAnAcceptedError() {
		PartiallyOrderedList list = buildPartiallyOrderedList(1, 2, 1);

		assertValuesAre(list, 1, 2);
	}

	@Test
	public void threeUnorderedElementsListWithANotAcceptedError() {
		PartiallyOrderedList list = buildPartiallyOrderedList(1, 3, 2, 1);

		assertValuesAre(list, 2, 1, 3);
	}

	@Test
	public void fiveOrderedElementsList() {
		PartiallyOrderedList list = buildPartiallyOrderedList(3, 1, 2, 3, 4, 5);

		assertValuesAre(list, 1, 2, 3, 4, 5);
	}

	@Test
	public void fiveOrderedElementsListWithAnAcceptedError() {
		PartiallyOrderedList list = buildPartiallyOrderedList(2, 3, 2, 1, 4, 5);

		assertValuesAre(list, 1, 2, 3, 4, 5);
	}

	@Test
	public void fiveOrderedElementsListWithNotAcceptedError() {
		PartiallyOrderedList list = buildPartiallyOrderedList(2, 3, 2, 5, 4, 1);

		assertValuesAre(list, 2, 3, 1, 4, 5);
	}
}
