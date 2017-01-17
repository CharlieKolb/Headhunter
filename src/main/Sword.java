package main;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Sword {

    private boolean isSwording;
    private int x;
    private int y;
    private boolean PlayerFacingRight;
    private BufferedImage si;
    private BufferedImage si1;
    private BufferedImage si2;
    private BufferedImage si3;
    private BufferedImage si4;
    private BufferedImage SImage;
    
    private String sword_1_1 = "sword_1_1.png";
    private String sword_1_2 = "sword_1_2.png";
    private String sword_2_1 = "sword_2_1.png";
    private String sword_2_2 = "sword_2_2.png";
    
    public Sword(int playerX, int playerY, boolean isFacingRight) {
    	
        isSwording = false;
        x = playerX + 32;
        y = playerY;
        PlayerFacingRight = isFacingRight;
        try {
        si1 = ImageIO.read(this.getClass().getResource(sword_1_1));
        si2 = ImageIO.read(this.getClass().getResource(sword_1_2));
        si3 = ImageIO.read(this.getClass().getResource(sword_2_1));
        si4 = ImageIO.read(this.getClass().getResource(sword_2_2));
        } catch(Exception e) { e.printStackTrace();} 
        si = si1;
    }
    
    public void move(boolean Swords, int playerX, int playerY, boolean isFacingRight) {
        isSwording = Swords;
        if(!isFacingRight) {
            x = playerX - 30;
            y = playerY-1;
        }
        else {
            x = playerX + 29;
            y = playerY-1;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage(boolean Swording, boolean FacingRight) {
        isSwording = Swording;
        PlayerFacingRight = FacingRight;
        if(isSwording && !PlayerFacingRight) si = si4;
        else if(isSwording) si = si2;
        else if(PlayerFacingRight) si = si1;
        else si = si3;
        SImage = si;
        return SImage;
    }




}
