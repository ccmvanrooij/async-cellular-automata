package framework;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

import upgrademethods.AsynOrdered;
import upgrademethods.AsynRandomReplacement;
import upgrademethods.AsynRandomWithoutReplacement;
import upgrademethods.AsynTimeDriven;
import upgrademethods.Synchronous;
import upgrademethods.UpdateMethod;

import cellularautomata.CAFrame;
import cellularautomata.GameOfLife;


/**
 * 
 * @author Christian van Rooij
 * Applet used as a framework for demos about (a-)synchronous Cellular Automata
 */
class AsynFrame extends Frame implements ActionListener, WindowListener, Runnable
{
    AsynCanvas output;
    
    CAFrame initialCA;   
    UpdateMethod initialUpdateMethod;
    
    Thread  animatie;
    static int canvasW = 70;
    static int canvasH = 50;
    int animationDelay = 100;
    String roundOrStep;
    
    final String items [][] = { {"File", "About", "line", "Clear", "Fill", "line", "Quit"}
                              , {"Animation", "Animation Delay", "line", "Step", "Round", "line", "Animate Step", "Animate Round", "line", "Stop"}
                              , {"Update Methods",
                            	  "Synchronous",
                            	  "submenu open", 
                            	  "Asynchronous",
                            	  "Time Driven", 
                            	  "Ordered Asynchronous",
                            	  "Random with replacement",
                            	  "Random without replacement",
                            	  "submenu close"}
                              , {"Examples", "Game of Life", "Adjusted Game of Life", "Synchonization Task", "Forrest Fire"}
                              };
    
    /**
     * Methods to construct the interface of the application
     * @param arg
     */
    public static void main(String [] arg) {
		AsynFrame a = new AsynFrame(canvasW, canvasH);
        a.setVisible(true);
	}
    
    public AsynFrame (int w, int h)
    {   this.setSize(800, 600);        
        this.addWindowListener(this);
        initInterface(w, h);
        initMenu();
    }

    private void initMenu()
    {   MenuBar  bar;
        bar  = new MenuBar();
        for (int i=0; i<items.length; i++)
            bar.add( maakMenu(items[i], this) );
        this.setMenuBar(bar);
    }

    private Menu maakMenu(String [] keuzes, ActionListener listener)
    {   Menu     menu;
        MenuItem item;
        menu = new Menu(keuzes[0]);
        for (int i=1; i<keuzes.length; i++) {   
        	if(keuzes[i] == "submenu open") {
        		i++;
        		Menu subMenu;
        		MenuItem subItem;
        		subMenu = new Menu(keuzes[i]);
        		i++;
        		while (!keuzes[i].equals("submenu close")) {
        			subItem = new MenuItem(keuzes[i]);
            		subMenu.add(subItem);
            		subItem.addActionListener(listener);
            		i++;
        		}
        		menu.add(subMenu);        		
        	} else if(keuzes[i] == "line") {
                menu.addSeparator();        		
        	} else { 
        		item = new MenuItem(keuzes[i]);
        		menu.add(item);
        		item.addActionListener(listener);
        	}
        }
        return menu;
    }

    private void initInterface(int w, int h) { 
    	initialUpdateMethod = new Synchronous();
    	initialCA = new GameOfLife(w, h, initialUpdateMethod);
    	output = new AsynCanvas(w, h, initialCA);
        this.setLayout(new BorderLayout() );
        this.add(output, BorderLayout.CENTER);
        this.changeHeader();
    }

    /**
     * Selected MenuItems are processed here
     */
    public void actionPerformed(ActionEvent ev) {
    	String action = ((MenuItem) ev.getSource()).getLabel();
        this.performAction(action);
    }

    public void performAction(String action) {
    	if (action.equals("Quit")) {
    		System.exit(0);
        }    
    	else if (action.equals("Animate Step")) {
    		animatie = null;
    		roundOrStep = "Step";
    		animatie = new Thread(this);
            animatie.start();
        }
    	else if (action.equals("Animate Round")) {
    		animatie = null;
    		roundOrStep = "Round";
    		animatie = new Thread(this);
            animatie.start();    		
        }    	
        else if (action.equals("Stop")) {
        	animatie=null;
        }
        else if (action.equals("About")) {
        	JOptionPane.showMessageDialog(this,
        	    "Created by Christian van Rooij \n " +
        	    "Inleiding Adaptieve Systemen \n " +
        	    "Universiteit Utrecht",        	    
        	    "About",
        	    JOptionPane.PLAIN_MESSAGE);        	
        }
    	
        else if (action.equals("Animation Delay")) {
        	String temp = (String)JOptionPane.showInputDialog(
        			this, "Current Animation Delay: " + this.animationDelay + "\n"+ 
        			"Insert a new value for the Animation Delay",
        			"Animation Delay",
        			JOptionPane.PLAIN_MESSAGE);        	
        	try {
            	int delay = Integer.parseInt(temp);
            	this.animationDelay = Math.abs(delay);
        	} catch (Exception e) {
        		JOptionPane.showMessageDialog(this,
                	    "Invalid Number",
                	    "Error",
                	    JOptionPane.PLAIN_MESSAGE);         		
        	}
        }
    	// Changing Update Method
        else if (action.equals("Synchronous")) {
        	this.animationDelay = 10;
        	this.initialUpdateMethod = new Synchronous();
        	this.initialCA.changeUpdateMethod(this.initialUpdateMethod);
        }
        else if (action.equals("Ordered Asynchronous")) {        	
        	this.initialUpdateMethod = new AsynOrdered();
        	this.initialCA.changeUpdateMethod(this.initialUpdateMethod);
        }
        else if (action.equals("Time Driven")) {        	
        	this.initialUpdateMethod = new AsynTimeDriven();
        	this.initialCA.changeUpdateMethod(this.initialUpdateMethod);
        }
        else if (action.equals("Random with replacement")) {
        	this.initialUpdateMethod = new AsynRandomReplacement();
        	this.initialCA.changeUpdateMethod(this.initialUpdateMethod);	
        }
        
        else if (action.equals("Random without replacement")) {        	
        	this.initialUpdateMethod = new AsynRandomWithoutReplacement();
        	this.initialCA.changeUpdateMethod(this.initialUpdateMethod);
        }
    
    	// Changing Demo
        else if (action.equals("Game of Life")) {
        	this.initialCA = new GameOfLife(canvasW, canvasH, initialUpdateMethod);
        	this.output.changeModel(initialCA);        	
        } 
        else output.doAction(action);
    	this.changeHeader();
    }

	public void run()
    {   while (animatie!=null)
        {   output.doAction(roundOrStep);
            try  {Thread.sleep(this.animationDelay);}
            catch (InterruptedException e) {}
            this.output.repaint();
        }    
    }
	
	private void changeHeader() {
		this.setTitle("Asynchronous Framework: " + this.initialCA.toString() + ", " + this.initialUpdateMethod.toString());
	}
    
    public void windowClosing    (WindowEvent e) {System.exit(0);}
    public void windowClosed     (WindowEvent e) {}
    public void windowOpened     (WindowEvent e) {}
    public void windowIconified  (WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated  (WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
