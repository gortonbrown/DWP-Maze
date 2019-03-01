package test;

import org.junit.Assert;
import org.junit.*;

import uk.gov.dwp.maze.Maze;

public class MazeTests {

	public Maze getMaze1() {
		return new Maze("resources/Maze1.txt");
	}
	
	public Maze getMaze2() {
		return new Maze("resources/Maze2.txt");
	}
	
	@Test
	public void testMazeHasOnlyOneExit(){
		Assert.assertEquals(true, getMaze1().isOnlyOneExit());
	}
	
	@Test
	public void testMazeHasOnlyOneStartPosition(){
		Assert.assertEquals(true, getMaze1().isOnlyOneStartPoint());
	}
	
	@Test
	public void testMazeWalls(){
		Assert.assertEquals(149, getMaze1().getNumberOfWalls());
	}
	
	@Test
	public void testMazeSpaces(){
		Assert.assertEquals(74, getMaze1().getNumberOfSpaces());
	}
	
	@Test
	public void testLocationAt_0_0_isAWall(){
		Assert.assertEquals("X", getMaze1().getItemAt(0, 0));
	}
	
	@Test
	public void testLocationAt_1_5_isASpaceAtMaze1(){
		Assert.assertEquals(" ", getMaze1().getItemAt(1, 5));
	}
	
	@Test
	public void testLocationAt_12_1_isASpaceAtMaze2(){
		Assert.assertEquals(" ", getMaze2().getItemAt(12, 1));
	}
	
	@Test
	public void testLocationAt_0_14_isAWallAtMaze1(){
		Assert.assertEquals("X", getMaze1().getItemAt(0, 14));
	}
	
	@Test
	public void testStartPositionIs_3_3_ForMaze1(){
		Integer[] expected = {3,3};
		Assert.assertArrayEquals(expected, getMaze1().getStartPosition());
	}
	
	@Test
	public void testExitPositionIs_14_1_ForMaze1(){
		Integer[] expected = {14,1};
		Assert.assertArrayEquals(expected, getMaze1().getExitPosition());
	}
	
	@Test
	public void testLocationAt_9_2_isAWallForMaze2(){
		Assert.assertEquals("X", getMaze2().getItemAt(9, 2));
	}
	
	@Test
	public void testStartPositionIs_6_10_ForMaze2(){
		Integer[] expected = {6,10};
		Assert.assertArrayEquals(expected, getMaze2().getStartPosition());
	}
	
	@Test
	public void testExitPositionIs_13_14_ForMaze2(){
		Integer[] expected = {13,14};
		Assert.assertArrayEquals(expected, getMaze2().getExitPosition());
	}
}
