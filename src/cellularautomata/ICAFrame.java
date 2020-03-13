package cellularautomata;
import upgrademethods.UpdateMethod;

/**
 * 
 * @author christian
 *
 */
public interface ICAFrame {
	
	public void doStep();

	public int doStep(int x, int y);
	
	public void doRound();
		
	public void clear();
	
	public void fill();

	public boolean isBlack(int x, int y);
	
	public void paintBlack(int x, int y, boolean linkerKnop);

	public int getWidth();

	public int getHeight();
	
	public int[][] getGrid();
	
	public void replaceGrid(int[][] z);
	
	public void changeUpdateMethod(UpdateMethod u);
	
	public String toString();
}
