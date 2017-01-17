package main;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HighscoreCard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HighscoreSave[] save;
	
	private Graphics2D g2d;
	
	private boolean go;
	private boolean resetScore;
	
	private JButton b1;
	
	private JButton b2;
	
	public HighscoreCard(ActionListener e, HighscoreSave[] highscore) {
		setFocusable(true);
        setDoubleBuffered(true);
        setLayout(null);
		
        save = highscore;
		
		b1 = new JButton("Back to Menu");
		b1.addActionListener(e);
		b1.setBounds(200, 550, 150, 50);
		add(b1);
		
		b2 = new JButton("Reset Highscore");
		b2.addActionListener(e);
		b2.setBounds(700, 550, 150, 50);
		add(b2);

	}
	
	public void move(ActionEvent e) {
		if(e.getSource() == b1) {
			go = true;
		}
		if(e.getSource() == b2) {
			resetScore = true;
			save = new HighscoreSave[10];
		}
	}
	
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D)g;
		
		g2d.setFont(getFont().deriveFont(Font.BOLD, 22f));
		
		g2d.drawString("Name", 250, 70);
		g2d.drawString("Hardmode", 567, 70);
		g2d.drawString("Time", 794, 70);


		
		
		for(int i = 0; i < save.length; i++) {
			g2d.drawString(Integer.toString(i+1), 200, 100 + 30 * (i+1));
			if(save[i] != null && save[i].getName() != null) {
				int[] n = save[i].getTime();
				g2d.drawString(save[i].getName(), 230, 100 + 30 * (i+1));
				if(save[i].getHardmode()) g2d.drawString("Yes", 600, 100 + 30*(i+1));
				else g2d.drawString("No", 606, 100 + 30*(i+1));
				g2d.drawString(n[0] + n[1] + ":" + n[2] + n[3], 800, 100 + 30*(i+1));
			}
		}
	}
	
	
	public boolean go() {
		return go;
	}
	
	public boolean resetScore() {
		if(resetScore) {
			resetScore = false;
			return true;
		} else return false;
	}
}

