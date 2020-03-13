package upgrademethods;

import cellularautomata.*;
/**
 * 
 * @author christian
 *
 */
public class AsynRandomReplacement extends UpdateMethod {
	
	public void doStep(CAFrame ca) {
		
		int x, y;
		y = (int)(Math.random()*ca.getHeight());
		x = (int)(Math.random()*ca.getWidth());	
		ca.setGrid(x, y, ca.doStep(x, y));
	}
	
	public String toString() {
		return "Asynchronous Random with Replacement";		
	}
}