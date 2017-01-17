package main;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameTimer {

    
    private String[] n;
    
    //n4 n3 : n2 n1
    private int n1; //Sekunden Ziffer 1
    private int n2; //Sekunden Ziffer 2
    private int n3; //Minuten Ziffer 2
    private int n4; //Minuten Ziffer 2
    private int FrameCounter;

    private boolean isFrozen;
    private boolean started;
    
    BufferedImage i1;
    BufferedImage i2;
    BufferedImage i3;
    BufferedImage i4;
    
    BufferedImage[] ni;
    

    
    public GameTimer() {

    	
        n = new String[10];
        n[0] = "0.png";
        n[1] = "1.png";
        n[2] = "2.png";
        n[3] = "3.png";
        n[4] = "4.png";
        n[5] = "5.png";
        n[6] = "6.png";
        n[7] = "7.png";
        n[8] = "8.png";
        n[9] = "9.png";
        ni = new BufferedImage[10];
        for(int i=0; i < 10; i++)
			try {
				ni[i] = ImageIO.read(this.getClass().getResource(n[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
        updateImage();
    }
    
    public void move() {
        if(!isFrozen && started) {
            FrameCounter++;
            if(FrameCounter == 60) {
                n1++;
                if(n1 > 9) {
                    n2++;
                    n1 = 0;
                }
                if(n2 > 5) {
                    n3++;
                    n1 = 0;
                    n2 = 0;
                }
                if(n3 > 9) {
                    n4++;
                    n1 = 0;
                    n2 = 0;
                    n3 = 0;
                }
                FrameCounter = 0;
            }
            updateImage(); 
        }
    }
    
    public void freezeTimer() {
        isFrozen = true;
    }
    
    public void go() {
        started = true;
    }
    
    public void updateImage(){
        i1 = ni[n1];
        i2 = ni[n2];
        i3 = ni[n3];
        i4 = ni[n4];
    }
    
    public BufferedImage getImage1() {
        return i1;
    }
    
    public BufferedImage getImage2() {
        return i2;
    }
    
    public BufferedImage getImage3() {
        return i3;
    }
    
    public BufferedImage getImage4() {
        return i4;
    }
    
    //This is me being stupid
    public int getNumber4() {
        return n1;
    }
    public int getNumber3() {
        return n2;
    }
    public int getNumber2() {
        return n3;
    }
    public int getNumber1() {
        return n4;
    }

}
