package main;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField tField;
	
	private Graphics2D g2d;
	
	private boolean go;
	private boolean hardmode;
	private boolean goToHighscore;
	
	private JButton b1;
	private JButton b2;
	private JButton b3;
	
	public Menu(ActionListener e) {
		setFocusable(true);
        setDoubleBuffered(true);
        setLayout(null);
		
		tField = new JTextField();
		tField.addActionListener(e);
		tField.setSize(300, 30);
		tField.setLocation(640 - tField.getWidth()/2, 500);
		tField.setFont(getFont().deriveFont(22f));
		tField.setHorizontalAlignment(JTextField.CENTER);
		add(tField);
		
		b1 = new JButton("Play");
		b1.addActionListener(e);
		b1.setBounds(490, 550, 150, 50);
		add(b1);
		
		b2 = new JButton("Play Hardmode");
		b2.addActionListener(e);
		b2.setBounds(639, 550, 150, 50);
		add(b2);
		
		b3 = new JButton("Go to Highscore");
		b3.addActionListener(e);
		b3.setBounds(490, 610, 298, 30);
		add(b3);
	}
	
	public void move(ActionEvent e) {
		if(e.getSource() == b1) {
			go = true;
			hardmode = false;
		}
		if(e.getSource() == b2) {
			go = true;
			hardmode = true;
		}
		if(e.getSource() == b3) {
			goToHighscore = true;
		}
		
	}
	
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D)g;
		
		
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 64f));
		g2d.drawString("HEADHUNTER", 423, 100);
		
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22f));
		g2d.drawString("A game by:", 300, 200);
		
		g2d.drawString("Charlie Kolb", 450, 200);
		g2d.drawString("Programming, Graphics", 700, 200);
		
		g2d.drawString("Kevin Wiesmüller", 450, 230);
		g2d.drawString("Programming, Graphics", 700, 230);

		g2d.drawString("Florian Pachnek", 450, 260);
		g2d.drawString("Programming", 700, 260);
		
		g2d.drawString("Alexander Ortmeier", 450, 290);
		g2d.drawString("Graphics", 700, 290);
		
		g2d.drawString("Lukas Sterflinger", 450, 320);
		g2d.drawString("Graphics, Playtest", 700, 320);
		
		g2d.drawString("Georg Moser", 450, 350);
		g2d.drawString("Graphics, Playtest", 700, 350);

		g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 18F));
		g2d.drawString("Enter your name:", 570, 496);
		
	}
	
	public String getName() {
		if(!tField.getText().equals("")) return tField.getText();
		else return "Player";
	}
	
	public boolean go() {
		return go;
	}
	
	public boolean useHardmode() {
		return hardmode;
	}
	
	public boolean goToHighscore() {
		return goToHighscore;
	}
}
