package uk.gov.dwp.maze;

public class Explorer {
	
	private Integer rowPosition = 0;
	private Integer columnPosition = 0;
	private Maze maze;
	private History history;
	
	private Integer rowDirection = 1;
	private Integer columnDirection = 0;
	
	private Orientation orientation;
	private final String SUCCESS = "Successful move"; 
	private final String FAILED_TO_MOVE = "You were not able to move!";
	
	public void enterMaze(Maze maze){
		this.maze = maze;
		setOrientation(Orientation.FORWARD);
		history = new History();
		setPosition(this.maze.getStartPosition()[0], this.maze.getStartPosition()[1]);
	}
	
	public Integer[] getCurrentPosition() {
		return new Integer[]{rowPosition, columnPosition};
	}
	
	public void setPosition(Integer row, Integer column) {
		rowPosition = row;
		columnPosition = column;
		history.addFootprint(new Integer[]{row, column});
	}
	
	public History getHistory(){
		return history;
	}
	
	public String getFootprints() {
		return getHistory().getFootprints();
	}
	
	public boolean isMovePossible(Integer row, Integer column){
		if (maze.getItemAt(row, column).equals(maze.WALL_OF_MAZE))
			return false;
		
		return true;
	}
	
	public void move() {
		
		Integer row = getCurrentPosition()[0] + rowDirection;
		Integer column = getCurrentPosition()[1] + columnDirection;
		if (row > 0 && row < maze.WIDTH && column > 0 && column <maze.WIDTH){
			
			if (isMovePossible(row, column)){
				setPosition(row, column);
				System.out.println(SUCCESS+"to "+row+","+column);
			}else
				System.out.println(FAILED_TO_MOVE);
		}else{
			System.out.println(FAILED_TO_MOVE+ "You are at the edge.");
		}
	}
	
	public String moveTest() {
		Integer row = getCurrentPosition()[0] + rowDirection;
		Integer column = getCurrentPosition()[1] + columnDirection;
		
		if (row > 0 && row < maze.WIDTH && column > 0 && column <maze.WIDTH){
			System.out.println(getOrientation()+":"+maze.getItemAt(row, column));
			return getOrientation()+":"+maze.getItemAt(row, column);
		}else{
			System.out.println(FAILED_TO_MOVE+ "You are at the edge.");
			return (FAILED_TO_MOVE+ "You are at the edge.");
		}
	}
	
	public void adjustDirection(Integer rowAdjust, Integer columnAdjust) {
		rowDirection = rowAdjust;
		columnDirection = columnAdjust;
	}
	
	public String directionOptions(){
		StringBuffer report = new StringBuffer();
		Orientation originalOrientation = getOrientation();
		
		setOrientation(Orientation.LEFT);
		report.append(moveTest() +" | ");
		
		setOrientation(Orientation.RIGHT);
		report.append(moveTest() +" | ");
		
		setOrientation(Orientation.FORWARD);
		report.append(moveTest() +" | ");
		
		setOrientation(Orientation.BACK);
		report.append(moveTest());
		
		setOrientation(originalOrientation);
		
		return report.toString();
	}
	
	public void setOrientation(Orientation orientation){
		this.orientation = orientation;
		switch(orientation){
			case LEFT : 
				adjustDirection(0, -1);
				break;
			case RIGHT :
				adjustDirection(0, 1);
				break;
			case BACK :
				adjustDirection(1, 0);
				break;
			case FORWARD:
				adjustDirection(-1, 0);
				break;
		}
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
}
