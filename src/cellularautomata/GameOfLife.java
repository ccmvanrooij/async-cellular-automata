package cellularautomata;
import java.awt.Color;

import upgrademethods.UpdateMethod;

/**
 * 
 * @author Christian
 *
 */
public class GameOfLife extends CAFrame {
	
	public GameOfLife(int w, int h, UpdateMethod x) {
		super(w, h, x);
		this.colors = new Color[2];
		colors[0] = Color.red;
		colors[1] = Color.white;
	}

	/**
	 * Execute a step from the Game of Life
	 */
	public int doStep(int x, int y)
    {   
		int n = this.buren(x,y);
		if(n==3 || (this.isBlack(x, y)&&n==2)) {
			return 0;
		} else {
			return 1;
		}
		
        //return (this.paintBlack(x,y, n==3 || (this.isBlack(x,y)&&n==2)) );
    }
		
	int buren(int x, int y)
    {
        int w, h;
        w = this.getWidth();
        h = this.getHeight();

        int xl = x-1; if (xl<0)  xl+=w;
        int yl = y-1; if (yl<0)  yl+=h;
        int xr = x+1; if (xr>=w) xr-=w;
        int yr = y+1; if (yr>=h) yr-=h;
        int n=0;
        if (this.isBlack(xl,yl)) n++;
        if (this.isBlack(x ,yl)) n++;
        if (this.isBlack(xr,yl)) n++;
        if (this.isBlack(xl,y )) n++;
        if (this.isBlack(xr,y )) n++;
        if (this.isBlack(xl,yr)) n++;
        if (this.isBlack(x ,yr)) n++;
        if (this.isBlack(xr,yr)) n++;
        return n;
    }
	
	public String toString() {
		return "Game of Life";
	}
}