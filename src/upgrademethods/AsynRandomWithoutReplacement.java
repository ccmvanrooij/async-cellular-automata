package upgrademethods;

import java.awt.Point;
import java.util.ArrayList;

import cellularautomata.CAFrame;

public class AsynRandomWithoutReplacement extends UpdateMethod{

	boolean newRound;
	ArrayList<Point> set;
	
	public AsynRandomWithoutReplacement() {
		set = new ArrayList<Point>();
		newRound = true;
	}
		
	public void doStep(CAFrame ca) {
		
		if(newRound) { 
			for(int x=0; x<ca.getWidth(); x++) {
				for(int y=0; y<ca.getHeight(); y++) {
					set.add(new Point(x, y));
				}				
			}
			newRound = false;
		}
		
		int z = (int)(Math.random()*set.size());
		Point temp = set.get(z);
		set.remove(z);
		ca.setGrid(temp.x, temp.y, ca.doStep(temp.x, temp.y));
		
		if(set.isEmpty()) {
			newRound = true;
		}
	}

	public String toString() {
		return "Asynchronous Random without Replacement";
	}
}
