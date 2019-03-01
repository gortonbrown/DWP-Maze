package test;

import org.junit.Assert;
import org.junit.*;

import uk.gov.dwp.maze.Explorer;
import uk.gov.dwp.maze.Maze;
import uk.gov.dwp.maze.Orientation;

public class ExplorerTests {
	private Explorer explorer;
	private Maze maze;
	
	@Before
	public void setup(){
		maze = new Maze("resources/Maze2.txt");
		explorer = new Explorer();
		explorer.enterMaze(maze);
	}
	
	@Test
	public void testExplorerStartPositionIsMazeStartPosition() {
		Assert.assertArrayEquals(explorer.getCurrentPosition(), maze.getStartPosition());
	}
	
	@Test
	public void testExplorerCannotMoveForwardFromStartBecauseOfTheWall(){

		Assert.assertEquals("X", maze.getItemAt((explorer.getCurrentPosition()[0]-1), explorer.getCurrentPosition()[1]));
		Assert.assertFalse(explorer.isMovePossible(explorer.getCurrentPosition()[0]-1, explorer.getCurrentPosition()[1]));
	}
	
	@Test
	public void testExplorerCanTurnAndMoveLeftBecauseThereIsASpace(){
		explorer.setOrientation(Orientation.LEFT);
	
		Assert.assertEquals(" ", maze.getItemAt((explorer.getCurrentPosition()[0]), explorer.getCurrentPosition()[1]-1));
		Assert.assertTrue(explorer.isMovePossible(explorer.getCurrentPosition()[0], explorer.getCurrentPosition()[1]-1));
	}
	
	@Test
	public void testExplorerCanTurnLeftAndMoveAndNewLocationIs_6_9_(){
		explorer.setOrientation(Orientation.LEFT);
		Assert.assertEquals(" ", maze.getItemAt((explorer.getCurrentPosition()[0]), explorer.getCurrentPosition()[1]-1));
		
		explorer.move();
		Integer[] expected = {6,9};
		Assert.assertArrayEquals(expected, explorer.getCurrentPosition());
	}
	
	@Test
	public void testExplorerCanTurnLeftAndContinueInThatDirectionMoveAndNewLocationIs_6_8_(){
		explorer.setOrientation(Orientation.LEFT);
		Assert.assertEquals(" ", maze.getItemAt((explorer.getCurrentPosition()[0]), explorer.getCurrentPosition()[1]-1));
		
		explorer.move();
		explorer.move();
		Integer[] expected = {6,8};
		Assert.assertArrayEquals(expected, explorer.getCurrentPosition());
	}
	
	@Test
	public void testExplorerCanTurnLeftAndContinueInThatDirectionMoveAndHistoryIsCollected(){
		explorer.setOrientation(Orientation.LEFT);
		Assert.assertEquals(" ", maze.getItemAt((explorer.getCurrentPosition()[0]), explorer.getCurrentPosition()[1]-1));
		
		explorer.move();
		explorer.move();
		String expectedHistory = "6,10 |6,9 |6,8";
		Assert.assertEquals(expectedHistory, explorer.getFootprints());
	}
	
	@Test
	public void testExplorerCanTurnGoSouthFrom_6_6_NewLocationIs_7_6_(){
		explorer.setPosition(6, 6);
		explorer.setOrientation(Orientation.BACK);
		
		explorer.move();

		Integer[] expected = {7,6};
		Assert.assertArrayEquals(expected, explorer.getCurrentPosition());
	}
	
	@Test
	public void testOptionsFromStart(){
		String expected = "LEFT:  | RIGHT:  | FORWARD:X | BACK:X";
		Assert.assertEquals(expected, explorer.directionOptions());
	}
	
	@Test
	public void testOptionsFrom_6_6(){
		explorer.setPosition(6, 6);
		String expected = "LEFT:X | RIGHT:  | FORWARD:X | BACK: ";
		Assert.assertEquals(expected, explorer.directionOptions());
	}
	
	@Test
	public void testOptionsFrom_0_0ShouldNotAllowMovementOptions(){
		explorer.setPosition(0, 0);
		String expected = "You were not able to move!You are at the edge. | You were not able to move!You are at the edge. | You were not able to move!You are at the edge. | You were not able to move!You are at the edge.";
		Assert.assertEquals(expected, explorer.directionOptions());
	}
	
	@Test
	public void testOptionsFrom_1_14ShouldOnlyAllowLeftMovementOptions(){
		explorer.setPosition(1, 14);
		String expected = "LEFT:  | You were not able to move!You are at the edge. | You were not able to move!You are at the edge. | You were not able to move!You are at the edge.";
		Assert.assertEquals(expected, explorer.directionOptions());
	}
}
