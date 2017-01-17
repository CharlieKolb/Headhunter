package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hud {

    private String hud_lifepoints_0 = "hud_lifepoints_0.png";
    private String hud_lifepoints_1 = "hud_lifepoints_1.png";
    private String hud_lifepoints_2 = "hud_lifepoints_2.png";
    private String hud_lifepoints_3 = "hud_lifepoints_3.png";
    private String hud_lifepoints_4 = "hud_lifepoints_4.png";
    private String hud_lifepoints_5 = "hud_lifepoints_5.png";
    private String hud_lifepoints_6 = "hud_lifepoints_6.png";
    
    private String hud_n_boss_lifepoints[];
    private String hud_h_boss_lifepoints[];
    private BufferedImage bli[];
    private BufferedImage bliImage;
    
    private String hud_dash_0 = "hud_dash_0_unused.png";
    private String hud_dash_1 = "hud_dash_1.png";
    private String hud_dash_2 = "hud_dash_2.png";
    private String hud_dash_3 = "hud_dash_3.png";
    private String hud_dash_4 = "hud_dash_4.png";
    private String hud_dash_5 = "hud_dash_5.png";
    private String hud_dash_6 = "hud_dash_6.png";
    
    private String hud_timer = "hud_timer.png";
    
    private boolean hardcore;
    
    private BufferedImage[] di;
    private BufferedImage[] li;
    private BufferedImage ti;

    
    public Hud(boolean hardmode) {

    	
        hardcore = hardmode;
        if(!hardcore) {
            hud_n_boss_lifepoints = new String[24];
            bli = new BufferedImage[24];
            for(int i = 0; i < 23; i++) {
                hud_n_boss_lifepoints[i] = "hud_boss/hud_boss_n_lifepoints_" + i + ".png";
                try {
					bli[i] = ImageIO.read(this.getClass().getResource(hud_n_boss_lifepoints[i]));
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        if(hardcore) {
            hud_h_boss_lifepoints = new String[45];
            bli = new BufferedImage[45];
            for(int i = 0; i < 45; i++) {
                hud_h_boss_lifepoints[i] = "hud_boss/hud_boss_h_lifepoints_" + i + ".png";
                try {
					bli[i] = ImageIO.read(this.getClass().getResource(hud_h_boss_lifepoints[i]));
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        
        li = new BufferedImage[7];
        try {
			li[0] = ImageIO.read(this.getClass().getResource(hud_lifepoints_0));
			li[1] = ImageIO.read(this.getClass().getResource(hud_lifepoints_1));
			li[2] = ImageIO.read(this.getClass().getResource(hud_lifepoints_2));
			li[3] = ImageIO.read(this.getClass().getResource(hud_lifepoints_3));
			li[4] = ImageIO.read(this.getClass().getResource(hud_lifepoints_4));
			li[5] = ImageIO.read(this.getClass().getResource(hud_lifepoints_5));
			li[6] = ImageIO.read(this.getClass().getResource(hud_lifepoints_6));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        try {
			li[0] = ImageIO.read(this.getClass().getResource(hud_lifepoints_0));
			li[1] = ImageIO.read(this.getClass().getResource(hud_lifepoints_1));
			li[2] = ImageIO.read(this.getClass().getResource(hud_lifepoints_2));
			li[3] = ImageIO.read(this.getClass().getResource(hud_lifepoints_3));
			li[4] = ImageIO.read(this.getClass().getResource(hud_lifepoints_4));
			li[5] = ImageIO.read(this.getClass().getResource(hud_lifepoints_5));
			li[6] = ImageIO.read(this.getClass().getResource(hud_lifepoints_6));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        di = new BufferedImage[7];
        try {
        	di[0] = ImageIO.read(this.getClass().getResource(hud_dash_0));
        	di[1] = ImageIO.read(this.getClass().getResource(hud_dash_1));
        	di[2] = ImageIO.read(this.getClass().getResource(hud_dash_2));
        	di[3] = ImageIO.read(this.getClass().getResource(hud_dash_3));
        	di[4] = ImageIO.read(this.getClass().getResource(hud_dash_4));
        	di[5] = ImageIO.read(this.getClass().getResource(hud_dash_5));
        	di[6] = ImageIO.read(this.getClass().getResource(hud_dash_6));

        } catch(IOException e) {
        	e.printStackTrace();
        }
        
        try {
			ti = ImageIO.read(this.getClass().getResource(hud_timer));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public BufferedImage getLifepointsImage(int Lifepoints) {
        if(Lifepoints == 6) return li[6];
        else if(Lifepoints == 5) return li[5];
        else if(Lifepoints == 4) return li[4];
        else if(Lifepoints == 3) return li[3];
        else if(Lifepoints == 2) return li[2];
        else if(Lifepoints == 1) return li[1];					//li = new ImageIcon(this.getClass().getResource(hud_lifepoints_1));
        else return li[0];
    }
    
    public Image getTimerImage() {
        return ti;
    }

    public Image getDashImage(int DashCD, boolean hasObtainedDash) {
        if(!hasObtainedDash) return di[0];
        else if (DashCD > 120) return di[6];
        else if (DashCD > 90) return di[5];
        else if (DashCD > 60) return di[4];
        else if (DashCD > 30) return di[3];
        else if (DashCD > 0) return di[2];
        else return di[1];
    }
    
    public Image getBossLPImage(int Lifepoints) {
        if(Lifepoints > 0) bliImage = bli[Lifepoints];
        else bliImage = bli[0];
        return bliImage;
    }


}
