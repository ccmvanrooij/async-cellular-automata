package framework;
import java.awt.*;
import java.awt.event.*;

import upgrademethods.UpdateMethod;

import cellularautomata.CAFrame;

/**
 * 
 * @author christian
 *	Class to paint the selected updating scheme and cellular automata
 */
class AsynCanvas extends Canvas implements MouseListener, MouseMotionListener
{
    int w, h;
    CAFrame model;

    AsynCanvas(int w, int h, CAFrame ca)
    {   
    	this.w = w;
    	this.h = h;
    	this.model = ca;
    	this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }   
    
    public void changeModel(CAFrame ca) {
    	this.model = ca;
    	model.clear();
    	this.repaint();
    }
    
    public void changeUpdateMethod(UpdateMethod u) {
    	this.model.changeUpdateMethod(u);
    }
    

    /**
     * Paint methods
     */
    private int diameter()
    {   Dimension dim = getSize();
        return Math.min( dim.width/model.getWidth(), dim.height/model.getHeight() );
    }

    public void update(Graphics g) {
    	paint(g);
    }

    private static boolean isLinkerKnop(MouseEvent e) {
    	return (e.getModifiers()&MouseEvent.BUTTON1_MASK)!=0;
    }

    /**
     * MouseListeners
     */
    public void mousePressed (MouseEvent e) {
    	int x, y, d;
        d = diameter();
        x = e.getX()/d;
        y = e.getY()/d;

        if (x>=0 && x<this.w && y>=0 && y<this.h)
            model.paintBlack(x, y, isLinkerKnop(e) );
        this.repaint();
    }

    public void paint(Graphics g) {
    	int x, y, d;
        d = this.diameter();

        g.setColor(Color.BLUE);
        for (y=0; y<=this.h; y++)
            g.drawLine(0, y*d, this.w*d, y*d );
        for (x=0; x<=this.w; x++)
            g.drawLine(x*d, 0, x*d, this.h*d );

        for (y=0; y<this.h; y++)
        {   for (x=0; x<this.w; x++)
            {
        		g.setColor(model.colors[model.grid[x][y]]);
                g.fillRect( x*d+1, y*d+1, d-1, d-1 );
            }
        }
    }

    public void doAction(String action)
    {   if      (action.equals("Step"))    		model.doStep();
    	else if (action.equals("Round"))    	model.doRound();
    	else if (action.equals("Clear"))   		model.clear();
        else if (action.equals("Fill"))   		model.fill();
        else return;
        this.repaint();
    }        
    
    public void mouseEntered (MouseEvent e) {}
    public void mouseExited  (MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked (MouseEvent e) {}
    public void mouseMoved   (MouseEvent e) {}
    public void mouseDragged (MouseEvent e) { this.mousePressed(e); }
}