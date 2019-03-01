package uk.gov.dwp.maze;

import java.util.LinkedList;
import java.util.List;

public class History {
	
	private List<Integer[]> history;
	
	public History() {
		history = new LinkedList<Integer[]>();
	}
	
	public void addFootprint(Integer[] position) {
		history.add(position);
	}
	
	public List<Integer[]> getHistory() {
		return history;
	}
	
	public String getFootprints() {	
		StringBuffer footprints = new StringBuffer();
		history.stream().forEach((entry) -> {
			footprints.append(entry[0]+","+entry[1]+" |");
		});
		
		return footprints.deleteCharAt(footprints.length()-1).toString().trim();
	}
}
