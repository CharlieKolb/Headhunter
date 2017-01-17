package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.IOException;

public class Screen {

    private String screen0 = "screen0.png";
    private String screen1 = "screen1.png";
    private String screen2 = "screen2.png";
    private String screen3 = "screen3.png";
    private String screen4 = "screen4.png";
    private String screen0_bw = "screen0_bw.png";
    private String screen1_bw = "screen1_bw.png";
    private String screen2_bw = "screen2_bw.png";
    private String screen3_bw = "screen3_bw.png";
    
    
    private BufferedImage image;
    private BufferedImage si;
    private BufferedImage si0;
    private BufferedImage si1;
    private BufferedImage si2;
    private BufferedImage si3;
    private BufferedImage si4;
    
    private BufferedImage screen_bimg;
    private BufferedImage screen_bimg0;
    private BufferedImage screen_bimg1;
    private BufferedImage screen_bimg2;
    private BufferedImage screen_bimg3;
    
    public Screen() {
    	
        try {
			si0 = ImageIO.read(this.getClass().getResource(screen0));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			si1 = ImageIO.read(this.getClass().getResource(screen1));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			si2 = ImageIO.read(this.getClass().getResource(screen2));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			si3 = ImageIO.read(this.getClass().getResource(screen3));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
        try {
			si4 = ImageIO.read(this.getClass().getResource(screen4));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
            screen_bimg0 = ImageIO.read(this.getClass().getResource(screen0_bw));
        } catch(Exception e1) { System.err.println("BW-Image nicht gefunden"); }
        try {
            screen_bimg1 = ImageIO.read(this.getClass().getResource(screen1_bw));
        }    catch(Exception e1) { System.err.println("BW-Image nicht gefunden"); }
        try {
            screen_bimg2 = ImageIO.read(this.getClass().getResource(screen2_bw));
        }    catch(Exception e1) { System.err.println("BW-Image nicht gefunden"); }
        try {
            screen_bimg3 = ImageIO.read(this.getClass().getResource(screen3_bw));
        }    catch(Exception e1) { System.err.println("BW-Image nicht gefunden"); }
        screen_bimg = screen_bimg0;
    }
    
    public BufferedImage getImage(int screenNumber) {
        if(screenNumber == 0) {
            si = si0;
            
            image = si;
            return image;
        }
        else if(screenNumber == 1) {
            si = si1;
            screen_bimg = screen_bimg1;
            image = si;
            return image;
        }
        else if(screenNumber == 2) {
            si = si2;
            screen_bimg = screen_bimg2;
            image = si;
            return image;
        }
        else if(screenNumber == 3) {
            si = si3;
            screen_bimg = screen_bimg3;
            image = si;
            return image;
        }
        else if(screenNumber == 4) {
            si = si4;
            screen_bimg = screen_bimg3;
            image = si;
            return image;
        }
        
        else {
            System.out.println("ERROR: Screen nicht gefunden, überprüfe, ob alle Grafiken vorhanden sind");
            return image;
        } 
    }

    public BufferedImage getBGImage(int currentScreen) {
        if(currentScreen == 0) screen_bimg = screen_bimg0;
        else if(currentScreen == 1) screen_bimg = screen_bimg1;
        else if(currentScreen == 2) screen_bimg = screen_bimg2;
        else if(currentScreen == 3) screen_bimg = screen_bimg3;
        else screen_bimg = screen_bimg0;
        return screen_bimg;
    }









}
