package upgrademethods;
import cellularautomata.*;

/**
 * 
 * @author christian
 *
 */
interface IUpdateMethod {

	public void doStep(CAFrame ca);
	
	public void doRound(CAFrame ca);
	
	public String toString();
	
}
