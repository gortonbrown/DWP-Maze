package test;

import org.junit.Assert;
import org.junit.*;
import uk.gov.dwp.maze.History;

public class HistoryTests {
	
	@Test
	public void testHistoryReturnsHistory() {
		History history = new History();
		
		history.addFootprint(new Integer[]{12,3});
		history.addFootprint(new Integer[]{12,4});
		history.addFootprint(new Integer[]{12,5});
		
		String expectedHistory = "12,3 |12,4 |12,5";
		Assert.assertEquals(expectedHistory, history.getFootprints());
	}
}
