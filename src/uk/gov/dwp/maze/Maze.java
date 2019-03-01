package uk.gov.dwp.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maze {
	private List<List<String>> maze;
	
	public final static String EXIT_MAZE = "F";
	public final static String START_MAZE = "S";
	public final static String WALL_OF_MAZE = "X";
	public final static String EMPTY_SPACE_IN_MAZE = " ";
	
	public Integer WIDTH;
	public Integer HEIGHT;
	
	public Maze(String mazepath){
		maze = new ArrayList<List<String>>();
	
		try (Stream<String> stream = Files.lines(Paths.get(mazepath))) {
			stream.forEach(row -> {
				maze.add(Arrays.asList(row));
				drawMaze(row);
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		getDimensions();
	}
	
	private void getDimensions(){
		String row = getMazeRow(0).get(0);
		WIDTH = row.length() - 1;
		HEIGHT = getMaze().size() - 1;
	}
	
	public boolean isOnlyOneExit(){
		return unique(EXIT_MAZE);
	}
	
	public boolean isOnlyOneStartPoint(){
		return unique(START_MAZE);
	}
	
	public boolean unique(String uniqueCharacter){
		int countOccurences = countUnique(uniqueCharacter);
		
		if (countOccurences == 1)
			return true;
		
		return false;
	}
	
	public int countUnique(String uniqueCharacter) {
		AtomicInteger count = new AtomicInteger(0);
		
		getMaze().stream().forEach((item) -> {
			if (item.toString().contains(uniqueCharacter)){
				String[] row = item.toString().split("");
				for(int i=0; i<row.length; i++) {
					if (row[i].equals(uniqueCharacter))
						count.incrementAndGet();
				}
			}
		});
		
		return count.get();
	}
	
	public List<List<String>> getMaze(){
		return maze;
	}
	
	public int getNumberOfWalls(){
		return countUnique(WALL_OF_MAZE);
	}
	
	public int getNumberOfSpaces(){
		return countUnique(EMPTY_SPACE_IN_MAZE);
	}
	
	public void drawMaze(String row){
		System.out.println(row);
	}
	
	public Integer[] getStartPosition() {
		return getPosition(START_MAZE);
	}
	
	public Integer[] getExitPosition() {
		return getPosition(EXIT_MAZE);
	}
	
	private Integer[] getPosition(String position) {
		AtomicInteger rowCounter = new AtomicInteger(-1);
		AtomicInteger columnCounter = new AtomicInteger(0);
			
		for (List<String> row: getMaze()) {
			rowCounter.incrementAndGet();
			if (row.toString().contains(position)) {
				
				String[] item = row.toString().split("");
				
				for(int i=1; i<item.length; i++) {
					if (item[i].equals(position)){
						break;
					}
					columnCounter.incrementAndGet();
				}
				
				break;
			}
		}
		
		return new Integer[]{rowCounter.intValue(), columnCounter.intValue()};
	}
	
	public String getItemAt(Integer rowPosn, Integer columnPosition) {
		return getMazeColumn(getMazeRow(rowPosn), columnPosition) ;
	}
	
	private String getMazeColumn(List<String> row, Integer column) {
		 String[] rowItem = row.get(0).split("");
		 return rowItem[column];
	}
	
	private List<String> getMazeRow(Integer row){
		return getMaze().get(row);
	}
	
	public static void main(String[] args) {
		Maze maze = new Maze("resources/Maze1.txt");
		Explorer explorer = new Explorer();
		explorer.enterMaze(maze);
		
		System.out.println("The start position is at: "+maze.getStartPosition()[0]+","+maze.getStartPosition()[1]);
		System.out.println("The explorer is at: "+explorer.getCurrentPosition()[0]+","+explorer.getCurrentPosition()[1]);
		
		System.out.println("What is at location (5,2)? "+maze.getItemAt(5, 2));
		System.out.println("number of walls: "+maze.getNumberOfWalls());
		System.out.println("number of spaces: "+maze.getNumberOfSpaces());
		
		
	}
}
