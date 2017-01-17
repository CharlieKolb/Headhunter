package main;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Player {
    
    private int lifepoints;
    
    private String player_R = "player_R.png";
    private String player_L = "player_L.png";
    private String player_L_W_1 = "player_L_W_1.png";
    private String player_L_W_2 = "player_L_W_2.png";
    private String player_R_W_1 = "player_R_W_1.png";
    private String player_R_W_2 = "player_R_W_2.png";
    private String player_R_D = "player_R_D.png";
    private String player_L_D = "player_L_D.png";
    private String player_L_S_1  = "player_L_S_1.png";
    private String player_R_S_1  = "player_R_S_1.png";
    
    private String potion_hud_0 = "potions/potion_hud_0.png";
    private String potion_hud_1 = "potions/potion_hud_1.png";
    private String potion_hud_2 = "potions/potion_hud_2.png";

    private BufferedImage image;
    private BufferedImage pImage;
    private BufferedImage pi0;
    private BufferedImage pi1;
    private BufferedImage pi2;
    private BufferedImage ii;
    private BufferedImage i1;
    private BufferedImage i2;
    private BufferedImage i3;
    private BufferedImage i4;
    private BufferedImage i5;
    private BufferedImage i6;
    private BufferedImage i7;
    private BufferedImage i8;
    private BufferedImage i9;
    private BufferedImage i10;    
    
    private double dx;
    private double dy;
    private double x;
    private double y;
    private double ax;    // alte Position von x
    private double jumpHeightDifference;
    private double jumpHeightStart;
    private double jumpHeightY;
    private int isDashingCounter;
    private int jumpCounter;
    private int FrameCounter;
    private int isDashingCooldownCounter;
    private int isStaggeredTimer;
    private int isStaggeredTimer2;
    private int isSwordingCounter;
    private int restoreHealthCounter;
    private int potionCounter;
    private boolean isStaggered;
    private boolean isWalkingLeft;
    private boolean isWalkingRight;
    private boolean isJumping;
    private boolean isDashing;
    private boolean stoppedWalkingLeft;    
    private boolean stoppedWalkingRight;
    private boolean isFacingRight;
    private boolean isAbleToJump;
    private boolean isAbleToDash;
    private boolean hitFromRight;
    private boolean isAbleToWalkRight;
    private boolean isAbleToWalkLeft;
    private boolean isSwording;
    private boolean isAbleToSword;
    private boolean isStaggered1;
    private boolean died;
    private boolean isDowning;
    private boolean restoreHealth;
    private boolean pressedEnter;
    
    private int xp;
    private int level;
    private int xpToAdd;
    private int xpCD;
    

    public Player() {
    	
    	try {
        pi0 = ImageIO.read(this.getClass().getResource(potion_hud_0));
        pi1 = ImageIO.read(this.getClass().getResource(potion_hud_1));
        pi2 = ImageIO.read(this.getClass().getResource(potion_hud_2));
        ii = ImageIO.read(this.getClass().getResource(player_R));
        image = ii;
        isFacingRight = true;
        isAbleToJump = true;
        isAbleToDash = true;
        isAbleToWalkRight = true;
        isAbleToWalkLeft = true;
        isAbleToSword = true;
        level = 1;

        i1 = ImageIO.read(this.getClass().getResource(player_R));
        i2 = ImageIO.read(this.getClass().getResource(player_L));
        i3 = ImageIO.read(this.getClass().getResource(player_L_W_1));
        i4 = ImageIO.read(this.getClass().getResource(player_L_W_2));
        i5 = ImageIO.read(this.getClass().getResource(player_R_W_1));
        i6 = ImageIO.read(this.getClass().getResource(player_R_W_2));
        i7 = ImageIO.read(this.getClass().getResource(player_R_D));
        i8 = ImageIO.read(this.getClass().getResource(player_L_D));
        i9 = ImageIO.read(this.getClass().getResource(player_L_S_1));
        i10 = ImageIO.read(this.getClass().getResource(player_R_S_1));
        ii = i1;
    	} catch(Exception e) { e.printStackTrace();}
        
        lifepoints = 6;

        x = 40;
        y = 559;        //559
    }


    public void move() {
        if(xpCD == 0) lvlLogic();
        xpCD++;
        if(xpCD == 20) xpCD = 0;
        if(restoreHealth) {
            restoreHealthCounter++;
            
            if(restoreHealthCounter == 30 || restoreHealthCounter == 60 || restoreHealthCounter == 90 || restoreHealthCounter == 120 || restoreHealthCounter == 150) {
                lifepoints++;
            }
            if(lifepoints == 6) {
                restoreHealthCounter = 0;
                restoreHealth = false;
            }
        }
        if(isAbleToWalkRight) {
	        if(isWalkingRight) {     //WalkingRight
	            if(level == 1) dx = 4;
	            else if(level == 2) dx = 5;
	            else if(level == 3) dx = 6;
	            else if(level == 4) dx = 7;
	            isWalkingLeft = false;
	            if(!isJumping && stoppedWalkingRight) {
	                isWalkingRight = false;
	                stoppedWalkingRight = false;
	                isAbleToWalkLeft = true;
	                dx = 0;
	            }
	        }   
        }
        if(isAbleToWalkLeft) {
	        if(isWalkingLeft) {     //WalkingLeft
	            if(level == 1) dx = -4;
	            else if(level == 2) dx = -5;
	            else if(level == 3) dx = -6;
	            else if(level == 4) dx = -7;
	            isWalkingRight = false;
	            if(!isJumping && stoppedWalkingLeft) {
	                isWalkingLeft = false;
	                stoppedWalkingLeft = false;
	                dx = 0;
	                isAbleToWalkRight = true;
	            }
	        }   
        }
        if(isAbleToSword) {
            if(isSwording) {
                isSwordingCounter++;
            }
            if(isSwordingCounter > 10) {
                isSwording = false;  
                isAbleToSword = false;
            }
        }
        else {
            isSwording = false;
            isSwordingCounter++;
            if(isSwordingCounter > 30) {
                isAbleToSword = true;
                isSwordingCounter = 0;
            }
        }
        
        if(isAbleToDash) {
            if(isDashing) {
                if(isDashingCounter < 40) {
                    isAbleToJump = false;
                    if(isFacingRight) {
                        if(level == 1) dx = 7;
                        else if(level == 2)dx = 8;
                        else if(level == 3)dx = 9;
                        else if(level == 4)dx = 10;
                        dy = 0;
                        ii = i7;
                    }
                    else if(!isFacingRight) {
                        if(level == 1) dx = -7;
                        else if(level == 2)dx = -8;
                        else if(level == 3)dx = -9;
                        else if(level == 4)dx = -10;                        
                        dy = 0;
                        ii = i8;
                    }
                    isDashingCounter++;
                    isSwording = false;
                }
                else {
                    isAbleToJump = true;
                    isDashing = false;
                    isDashingCounter = 0;
                    isDashingCooldownCounter = 150;
                    dx = 0;

                }
            }
        }
       
        if(isAbleToJump) { 
            if(isJumping) {               
                jumpHeightDifference = jumpHeightStart - jumpHeightY;
                if(jumpHeightDifference <= 50) {
                    dy = -7; 
                }  
                else if(jumpHeightDifference <= 70) {
                    dy = -6;
                }
                else if(jumpHeightDifference >= 95) {
                    dy = 0;
                    jumpHeightY = 0;
                    isJumping = false;
                    if(ax > x) isWalkingLeft = true;
                    else if(ax < x) isWalkingRight = true;
                    
                }
                isSwording = false;
                ax = x;               
                x = x + dx;
                jumpHeightY = jumpHeightY + dy;
                y = y + dy;                 
            }
            else {                 
                x = x + dx;
                y = y + dy;     
            }
        }  
        else {
            x = x + dx;
            y = y + dy;     
        }
        if(isStaggered) {
            if(isStaggeredTimer != 0) {
                if(isStaggeredTimer > isStaggeredTimer2 - 25) {
                    if(!hitFromRight) {
                        ii = i9;
                        dx = 3;
                        dy = -3;
                        isAbleToWalkLeft = false;
                        isAbleToWalkRight = false;
                    }
                    else {
                        ii = i10;
                        dx = -3;
                        dy = -3;
                        isAbleToWalkLeft = false;
                        isAbleToWalkRight = false;
                    }
                    isStaggered1 = true;
                    isStaggeredTimer = isStaggeredTimer - 1;
                }  
                else if(isStaggeredTimer == isStaggeredTimer2 - 25) {
                    if(!hitFromRight) {
                        ii = i2;
                        dx = 0;
                        dy = 0;
                        isAbleToWalkLeft = true;
                        isAbleToWalkRight = true;
                    }
                    else {
                        ii = i1;
                        dx = 0;
                        dy = 0;
                        isAbleToWalkLeft = true;
                        isAbleToWalkRight = true;
                    }
                    isStaggered1 = false;
                    isStaggeredTimer = isStaggeredTimer - 1;     
                    isAbleToDash = true;
                    isAbleToJump = true;
                }                
                else if(isStaggeredTimer < isStaggeredTimer2 - 25) {
                    if(!hitFromRight) {
                        ii = i2;
                        if(isDashing) ii = i8;
                        isAbleToWalkLeft = true;
                        isAbleToWalkRight = true;
                    }
                    else {
                        ii = i1;
                        if(isDashing) ii = i7;
                        isAbleToWalkLeft = true;
                        isAbleToWalkRight = true;
                    }
                    isStaggeredTimer = isStaggeredTimer - 1;                    
                } 
            }            
            else {
                isAbleToWalkLeft = true;
                isAbleToWalkRight = true;
                isStaggered = false; 
                isAbleToJump = true;
                isAbleToDash = true;
            }
        } 
        if(!isJumping && !isDashing && !isStaggered1) {
        if(dx == 0) {
            if(isFacingRight) {
                ii = i1;
            }
            else {
                ii = i2;
            }
        }
        else if(dx > 0) {
            if(FrameCounter < 20) {
                ii = i5;
                FrameCounter++;
            }
            else if(FrameCounter < 40) {
                ii = i6;
                FrameCounter++;
            } 
            if(FrameCounter == 40) {
                ii = i5;
                FrameCounter = 0;
            }             
        }
        else if(dx < 0 ) {
            if(FrameCounter < 20) {
                ii = i3;
                FrameCounter++;
            }
            else if(FrameCounter <= 40) {
                ii = i4;
                FrameCounter++;
            }   
            if(FrameCounter == 40) {
                ii = i3;
                FrameCounter = 0;
            } 
        }
        }
        if(dy == 0) dy = 7;
         
           
        if(lifepoints <= 0) died = true;
        if(isDashingCooldownCounter != 0) isDashingCooldownCounter = isDashingCooldownCounter - 1;        
        }    

    public void lvlLogic() {
        if(xpToAdd != 0) {
            xp++;
            xpToAdd -= 1;
            if(level == 1 && xp >= 7) {
                level = 2;
                xp = xp - 7;
            }
            if(level == 2 && xp >= 13) {
                level = 3;
                xp = xp - 13;
            }
            if(level == 3 && xp >= 28) {
                level = 4;
                xp = 28;
            }
        
        }
    }    

    public void restoreHealth() {
        restoreHealth = true;
    }    
    
    public BufferedImage getPotionImage() {
        if(potionCounter == 0) pImage = pi0;
        else if(potionCounter == 1) pImage = pi1;
        else if(potionCounter == 2) pImage = pi2;
        return pImage;
    }
    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public double getDX() {
        return dx;
    }
    
    public double getDY() {
        return dy;
    }
    
    public boolean isInvincible() {
        return isStaggered;
    }
    
    public Image getImage() {
        image = ii;
        return image;
    }
    
    public void addXP(int exp) {
        xpToAdd += exp;
    }
    
    public int getXP() {
        return xp;
    }
    public int getlvl() {
        return level;
    }
    public int getDashingCooldownCounter() {
        return isDashingCooldownCounter;
    }
    
    public int getLifepoints() {
        return lifepoints;
    }
    public boolean isSwording() {
        return isSwording;
    }
    public void jumpcounterResettet() {
        jumpCounter = 0;
    }
    public void jumpCounterAuf2() {
        jumpCounter = 2;
    }
    public void gotHit(int amountOfDamage, int staggeredTime, boolean hitFromRight) {
        if(!isStaggered) {
            lifepoints = lifepoints - amountOfDamage;
            isStaggered = true;
            isStaggeredTimer = staggeredTime;
            isStaggeredTimer2 = staggeredTime;
            this.hitFromRight = hitFromRight;
        }
    }
 
    public void addPotion() {
        if(potionCounter < 2) potionCounter++;
    }
    
    public boolean died() {
        return died;
    }
    
    public void kill() {
        died = true;
    }
    public void setX(int value) {
        x = value;
    }
    
    public boolean isDashing() {
        return isDashing;
    }
    
    public void setY(int value) {
        y = value;
    }
    
    public void setDX(int value) {
        dx = value;
    }
    
    public void setDY(int value) {
        dy = value;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }
    
    public void respawn() {
        died = false;
        lifepoints = 6;
    }
    
    public boolean isDowning() {
        return isDowning;
    }
    
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ENTER) {
            pressedEnter = true;
        }

        if (key == KeyEvent.VK_LEFT) {
            if(pressedEnter) {    
                if(isAbleToWalkLeft) {
                    if(isWalkingRight = true) {
                        stoppedWalkingRight = false;
                        isWalkingRight = false;
                    }
                    dx = -2;
                    stoppedWalkingLeft = false;
                    isWalkingLeft = true;
                    isFacingRight = false;
                }
            }
        }
        if (key == KeyEvent.VK_RIGHT) {                         //Rechtslaufen
            if(pressedEnter) {   
            if(isAbleToWalkRight) {
                if(isWalkingLeft = true) {
                    stoppedWalkingLeft = false;
                    isWalkingLeft = false;
                }
                dx = 2;
                stoppedWalkingRight = false;
                isWalkingRight = true;
                isFacingRight = true;
            }
            }
        }
        if (key == KeyEvent.VK_D) {                             //Dash
            if(pressedEnter) {   
            if(isDashingCooldownCounter != 0);
            else isDashing = true;
            }
        }
        if (key == KeyEvent.VK_H) {                             //Dash
            gotHit(1, 150, true);
        }
        if (key == KeyEvent.VK_R) {                            //Reset
            died = true;
        }
        if (key == KeyEvent.VK_Q) {                            //Schwerschlag
            if(pressedEnter) {   
                isSwording = true;
            }
        }
        if (key == KeyEvent.VK_A) {                            //Schwerschlag
            if(pressedEnter) {   
                if(potionCounter != 0) {
                    potionCounter -= 1;
                    if(lifepoints <= 4) lifepoints = lifepoints + 2;
                    else if(lifepoints <= 5) lifepoints = lifepoints + 1;
                    
                }
            }
        }
        if (key == KeyEvent.VK_UP) {
            if(pressedEnter) {   
                if(jumpCounter < 2) {
                    jumpHeightStart=y;
                    isJumping = true;
                    jumpCounter = jumpCounter + 1;
                    jumpHeightY = y;
                }   
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            isDowning = true;
         }    
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {     
            stoppedWalkingLeft = true;       
        }

        if (key == KeyEvent.VK_RIGHT) {
            stoppedWalkingRight = true;     
        }
        
        if (key == KeyEvent.VK_DOWN) {
            isDowning = false;
        }
    }
    
    public boolean hasEntered() {
        return pressedEnter;
    }
    
}

