package main;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Invinc {

    private String invinc_1 = "i_1.png";
    private String invinc_2 = "i_2.png";
    BufferedImage iv;
    BufferedImage iv0;
    BufferedImage iv1;
    private Image image;
    
    public Invinc() {
    
    	try {
        iv0 = ImageIO.read(this.getClass().getResource(invinc_1));
        iv1 = ImageIO.read(this.getClass().getResource(invinc_2));
    	} catch (Exception e) { e.printStackTrace(); }
    }


    public Image getImage(boolean isInvinc) {
        if(isInvinc) {
            iv = iv0;
        }
        else iv = iv1;
        image = iv;
        return image;
        
    }
}
