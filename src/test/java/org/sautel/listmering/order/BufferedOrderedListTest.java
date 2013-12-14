package org.sautel.listmering.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sautel.listmerging.order.BufferedOrderedList;

public class BufferedOrderedListTest {
	@Test
	public void emptyList() {
		BufferedOrderedList<Integer> list = new BufferedOrderedList<>();

		assertFalse(list.hasCurrentElement());
	}

	@Test
	public void oneElementList() {
		BufferedOrderedList<Integer> list = new BufferedOrderedList<>(1);

		assertTrue(list.hasCurrentElement());
		assertEquals(1, (int) list.getCurrentElement());
		list.consumeCurrentElement();
		assertFalse(list.hasCurrentElement());
	}

	@Test
	public void twoElementList() {
		BufferedOrderedList<Integer> list = new BufferedOrderedList<>(2, 1);

		assertTrue(list.hasCurrentElement());
		assertEquals(1, (int) list.getCurrentElement());
		list.consumeCurrentElement();
		assertTrue(list.hasCurrentElement());
		assertEquals(2, (int) list.getCurrentElement());
		list.consumeCurrentElement();
		assertFalse(list.hasCurrentElement());
	}
}
