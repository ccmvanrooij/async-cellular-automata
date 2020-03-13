package upgrademethods;

import cellularautomata.CAFrame;


/**
 * 
 * @author Christian
 *
 */
abstract public class UpdateMethod implements IUpdateMethod {
	
	public abstract void doStep(CAFrame ca);
	
	abstract public String toString();

	public void doRound(CAFrame caFrame) {
		int rounds = caFrame.getHeight()*caFrame.getWidth();
		while(rounds > 0) {
			this.doStep(caFrame);
			rounds--;
			System.out.println(rounds);
		}
	}
}
