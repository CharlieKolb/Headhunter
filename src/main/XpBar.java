package main;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class XpBar {

    private String[][] xpM;
    
    private BufferedImage[][] xiM;
    private BufferedImage xImage;
    
    public XpBar() {
    	
        xpM = new String[3][29];
        xiM = new BufferedImage[3][29];
        
        xpM[0][0] = "xp_0_0.png";
        xpM[0][1] = "xp_0_1.png";
        xpM[0][2] = "xp_0_2.png";
        xpM[0][3] = "xp_0_3.png";
        xpM[0][4] = "xp_0_4.png";
        xpM[0][5] = "xp_0_5.png";
        xpM[0][6] = "xp_0_6.png";
        xpM[0][7] = "xp_0_7.png";
        xpM[0][8] = "xp_1_0.png";
        
        xpM[1][0] = "xp_1_0.png";
        xpM[1][1] = "xp_1_1.png";
        xpM[1][2] = "xp_1_2.png";
        xpM[1][3] = "xp_1_3.png";
        xpM[1][4] = "xp_1_4.png";
        xpM[1][5] = "xp_1_5.png";
        xpM[1][6] = "xp_1_6.png";
        xpM[1][7] = "xp_1_7.png";
        xpM[1][8] = "xp_1_8.png";
        xpM[1][9] = "xp_1_9.png";
        xpM[1][10] = "xp_1_10.png";
        xpM[1][11] = "xp_1_11.png";
        xpM[1][12] = "xp_1_12.png";
        xpM[1][13] = "xp_1_13.png";
        xpM[1][14] = "xp_2_0.png";
        
        xpM[2][0] = "xp_2_0.png";
        xpM[2][1] = "xp_2_1.png";
        xpM[2][2] = "xp_2_2.png";
        xpM[2][3] = "xp_2_3.png";
        xpM[2][4] = "xp_2_4.png";
        xpM[2][5] = "xp_2_5.png";
        xpM[2][6] = "xp_2_6.png";
        xpM[2][7] = "xp_2_7.png";
        xpM[2][8] = "xp_2_8.png";
        xpM[2][9] = "xp_2_9.png";
        xpM[2][10] = "xp_2_10.png";
        xpM[2][11] = "xp_2_11.png";
        xpM[2][12] = "xp_2_12.png";
        xpM[2][13] = "xp_2_13.png";
        xpM[2][14] = "xp_2_14.png";
        xpM[2][15] = "xp_2_15.png";
        xpM[2][16] = "xp_2_16.png";
        xpM[2][17] = "xp_2_17.png";
        xpM[2][18] = "xp_2_18.png";
        xpM[2][19] = "xp_2_19.png";
        xpM[2][20] = "xp_2_20.png";
        xpM[2][21] = "xp_2_21.png";
        xpM[2][22] = "xp_2_22.png";
        xpM[2][23] = "xp_2_23.png";
        xpM[2][24] = "xp_2_24.png";
        xpM[2][25] = "xp_2_25.png";
        xpM[2][26] = "xp_2_26.png";
        xpM[2][27] = "xp_2_27.png";
        xpM[2][28] = "xp_2_28.png";
        
        try {
        xiM[0][0] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[0][0]));         
        xiM[0][1] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[0][1]));    
        xiM[0][2] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[0][2]));  
        xiM[0][3] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[0][3]));  
        xiM[0][4] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[0][4]));  
        xiM[0][5] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[0][5]));  
        xiM[0][6] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[0][6]));  
        xiM[0][7] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[0][7]));
        xiM[0][8] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[0][8]));
        
        xiM[1][0] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][0]));
        xiM[1][1] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][1]));
        xiM[1][2] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][2]));
        xiM[1][3] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][3]));
        xiM[1][4] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][4]));
        xiM[1][5] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][5]));
        xiM[1][6] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][6]));
        xiM[1][7] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][7]));
        xiM[1][8] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][8]));
        xiM[1][9] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][9]));
        xiM[1][10] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][10]));
        xiM[1][11] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][11]));
        xiM[1][12] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][12]));
        xiM[1][13] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][13]));
        xiM[1][13] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[1][14]));
        xiM[1][14] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][0]));
        
        xiM[2][0] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][0]));
        xiM[2][1] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][1]));
        xiM[2][2] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][2]));
        xiM[2][3] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][3]));
        xiM[2][4] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][4]));
        xiM[2][5] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][5]));
        xiM[2][6] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][6]));
        xiM[2][7] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][7]));
        xiM[2][8] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][8]));
        xiM[2][9] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][9]));
        xiM[2][10] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][10]));
        xiM[2][11] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][11]));
        xiM[2][12] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][12]));
        xiM[2][13] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][13]));
        xiM[2][14] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][14]));
        xiM[2][15] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][15]));
        xiM[2][16] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][16]));
        xiM[2][17] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][17]));
        xiM[2][18] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][18]));
        xiM[2][19] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][19]));
        xiM[2][20] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][20]));
        xiM[2][21] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][21]));
        xiM[2][22] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][22]));
        xiM[2][23] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][23]));
        xiM[2][24] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][24]));
        xiM[2][25] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][25]));
        xiM[2][26] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][26]));
        xiM[2][27] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][27]));
        xiM[2][28] = ImageIO.read(this.getClass().getResource("xpBar/" + xpM[2][28]));
    	} catch(Exception e) { e.printStackTrace();}
        
        xImage = xiM[0][0];
    }
    
    public Image getImage() {
        return xImage;
    }
    
    public void logic(int playerXP, int playerLvL) {
        if(playerLvL > 3) xImage = xiM[2][28];
        else xImage = xiM[playerLvL - 1][playerXP];
    }
}
