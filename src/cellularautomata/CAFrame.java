package cellularautomata;
import java.awt.Color;

import upgrademethods.UpdateMethod;;

/**
 * 
 * @author christian
 *
 */
public abstract class CAFrame implements ICAFrame
{
    public UpdateMethod method;
    public int[][] grid;
    public Color[] colors;
    int h, w;

    public CAFrame(int w, int h, UpdateMethod x) {   
    	this.w = w;
    	this.h = h;
        this.method = x;
        grid = new int[w][h];
        this.clear();
    }

    public void paintBlack(int x, int y, boolean b) {
    	if (b) {
	    	this.grid[x][y] = 0;
	    } else {
	    	this.grid[x][y] = 1;
	    }
    }

    public boolean isBlack(int x, int y) {
    	return (this.grid[x][y] == 0);
    }

    public void clear() {
    	for (int y=0; y<this.h; y++)
            for (int x=0; x<this.w; x++)
                this.paintBlack(x, y, false);
    }
    
    public void fill() {
    	for (int y=0; y<this.h; y++)
            for (int x=0; x<this.w; x++)
            	this.paintBlack(x, y, true);
    }

        
    public void doStep() {
    	this.method.doStep(this);
    }
    
    public void doRound() {
    	this.method.doRound(this);
    }
    
    abstract public int doStep(int x, int y);

	public int getHeight() {
		return this.h;
	}

	public int getWidth() {
		return this.w;
	}
	
	public int[][] getGrid() {
		return this.grid;
	}

	public void replaceGrid(int[][] z) {
		this.grid = z;
	}
	
	public void setGrid(int x, int y, int z) {
		this.grid[x][y] = z;
	}

	public void changeUpdateMethod(UpdateMethod u) {
		this.method = u;		
	}
	
	public abstract String toString();
}