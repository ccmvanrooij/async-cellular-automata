package upgrademethods;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import cellularautomata.CAFrame;

public class AsynOrdered extends UpdateMethod {

	LinkedList<Point> set;
	LinkedList<Point> copy;	
	Boolean newRound;
	Boolean initialised;
	
	public AsynOrdered() {
		set = new LinkedList<Point>();
		copy = new LinkedList<Point>();
		newRound = true;
		initialised = false;
	}
	
	@Override
	public void doStep(CAFrame ca) {
		
		if(!initialised) {			
			ArrayList<Point> helpArray = new ArrayList<Point>();						
			
			for(int x=0; x<ca.getWidth(); x++) {
				for(int y=0; y<ca.getHeight(); y++) {
					helpArray.add(new Point(x, y));
				}
			}			
			while(helpArray.size() != 0) {				
				int a = (int)(Math.random()*helpArray.size());
				Point z = helpArray.get(a);
				helpArray.remove(a);
				set.add(z);				
			}			
			initialised = true;
		}		
		if(newRound) {
			copy.addAll(set);
			newRound = false;
		}		
		Point temp = copy.remove();
		ca.setGrid(temp.x, temp.y, ca.doStep(temp.x, temp.y));
		
		if(copy.isEmpty()) {			
			newRound = true;
		}
	}

	@Override
	public String toString() {
		return "Asynchronous Ordered Updating";
	}
}
