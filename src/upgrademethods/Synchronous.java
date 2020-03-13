package upgrademethods;
import java.awt.Color;

import cellularautomata.CAFrame;
import cellularautomata.ICAFrame;

/**
 * 
 * @author christian
 *
 */
public class Synchronous extends UpdateMethod {

	public void doStep(CAFrame ca) {
		
		int [][] copy = new int[ca.getWidth()][ca.getHeight()];
		
		for(int x=0; x<ca.getWidth(); x++) {
			for(int y=0; y<ca.getHeight(); y++) {
				copy[x][y] = ca.doStep(x, y);
			}
		}		
		ca.replaceGrid(copy);
	}	
	
	public String toString() {
		return "Synchronous Updating";
	}

	public void doRound(CAFrame caFrame) {
		this.doStep(caFrame);		
	}
}
