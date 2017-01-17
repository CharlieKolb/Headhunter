package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.Timer;

/*  
 *  File: Main.java
 *  Author: Charlie Kolb/Kevin Wiesmüller/Florian Pachnek
 *  Graphics: Alexander Ortmeier/Kevin Wiesmüller/Charlie Kolb/Georg Moser/Lukas Sterflinger
 *  Description: Main Function, runs the Game Window
*/  
 
public class Main extends JFrame implements ActionListener {
	private enum gState_ {
		Menu,
		Gameboard,
		Card;
	}
	

    /**
     * 
     */
    private static final long serialVersionUID = -304073506992716187L;

    private static int size_x;
    private static int size_y;
    
    private String playerName;
    
    private Timer timer;
    
    private gState_ gState;
    private Menu menu;
    private Gameboard gameboard;
    private HighscoreCard card;
    
    
    private Highscore highscore;
    
    private boolean hardmode;
    
    public Main() {
        
        size_x = 1280;
        size_y = 720;

        menu = new Menu(this);
        add(menu);
        gState = gState_.Menu;
        menu.requestFocusInWindow();
		menu.repaint();
		menu.revalidate();
		
		try{
        	FileInputStream fileIn = new FileInputStream("Headhunter_saveFile.ser");
        	ObjectInputStream in = new ObjectInputStream(fileIn);
        	highscore = new Highscore((HighscoreSave[]) in.readObject());
        	in.close();
        	fileIn.close();
    	} catch (Exception i) {
    		highscore = new Highscore();
    		i.printStackTrace();
    	}


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size_x, size_y);
        setLocationRelativeTo(null);
        setTitle("Headhunter");
        setResizable(false);
        setVisible(true);
        
        timer = new Timer(5, this);
        timer.start();
    }
    
	public void actionPerformed(ActionEvent e) {
    	switch(gState) {
    	default: break;
    	case Menu:
    		if(menu != null) {
    			menu.move(e);
    			if(menu.go()) {
    				playerName = menu.getName();
    				hardmode = menu.useHardmode();
    				gameboard = new Gameboard(hardmode, playerName, this);
    				remove(menu);
    				add(gameboard);
    				gState = gState_.Gameboard;
    				gameboard.requestFocusInWindow();
    				gameboard.repaint();
    				gameboard.revalidate();
    			}
    			if(menu.goToHighscore()) {
    				card = new HighscoreCard(this, highscore.getTopTen());
    				remove(menu);
    				add(card);
    				gState = gState_.Card;
    				card.requestFocusInWindow();
    				card.repaint();
    				card.revalidate();
    			}
    		}
    		break;
    	case Gameboard: 
    		if(gameboard != null) {
    			gameboard.move(e);
    			if(gameboard.toMenu()) {
    				if(highscore != null) highscore.add(gameboard.getSave());
    				else {
    					highscore = new Highscore();
    					highscore.add(gameboard.getSave());
    				}
    				remove(gameboard);
    				add(menu = new Menu(this));
    				gState = gState_.Menu;
    				menu.requestFocusInWindow();
    				menu.repaint();
    				menu.revalidate();
    			}
    			if(gameboard.toHighscore()) {
    				if(highscore != null) highscore.add(gameboard.getSave());
    				else {
    					highscore = new Highscore();
    					highscore.add(gameboard.getSave());
    				}
    				card = new HighscoreCard(this, highscore.getTopTen());
    				remove(gameboard);
    				add(card);
    				gState = gState_.Card;
    				card.requestFocusInWindow();
    				card.repaint();
    				card.revalidate();
    			}
    		}
    		break;
    	case Card:
    		if(card != null) {
    			card.move(e);
    			if(card.resetScore()) highscore.reset();
    			if(card.go()) {
    				remove(card);
    				add(menu = new Menu(this));
    				gState = gState_.Menu;
    				menu.requestFocusInWindow();
    				menu.repaint();
    				menu.revalidate();
    			}
    		}
    		break;
    	}
    	repaint();
	}

    public static void main(String[] args) {
        new Main();
    }
    
    public static int get_size_x()
    {
        return size_x;
    }
    public static int get_size_y()
    {
        return size_y;
    }

	
    
}
