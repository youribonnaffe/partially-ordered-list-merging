package org.sautel.listmering.merge;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.sautel.listmerging.merge.BufferedListWriter;

public class BufferedListWriterTest {
	@Test
	public void write() {
		BufferedListWriter<Integer> writer = new BufferedListWriter<>();

		writer.write(2);
		writer.write(1);
		writer.write(3);

		assertThat(writer.getValues(), contains(2, 1, 3));
	}
}
