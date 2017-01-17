package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.util.Random;

public class Enemy { 
	
    private int x;
    private int y;
    private int lx;
    private int dx;
    private int dy;
    
    private int Lifepoints = 1;
    private int amountOfDamage;
    private int isStaggeredTime;
    private int isStaggeredTimer;
    private int Type;
    private int FrameCounter;
    private int damageDelt;
    private int randomTimer;
    private int randomNumberSave;
    private int gotHitTimer;
    private int leftX;
    private int rightX;
    private Random random;
    
    private boolean gotHit;
    private boolean isFacingRight;
    private boolean isStaggered;
    private boolean hasBeatenGame;
    private boolean hasDied;
    
    private String enemy_1_L = "enemy_1_L.png";
    private String enemy_1_R = "enemy_1_R.png";
    private String enemy_1_R_W_1 = "enemy_1_R_W_1.png";
    private String enemy_1_R_W_2 = "enemy_1_R_W_2.png";
    private String enemy_1_L_W_1 = "enemy_1_L_W_1.png";
    private String enemy_1_L_W_2 = "enemy_1_L_W_2.png";
    private String enemy_1_L_S = "enemy_1_L_S.png";
    private String enemy_1_R_S = "enemy_1_R_S.png";
    
    
    private BufferedImage image;
    private BufferedImage ei;
    private BufferedImage e1;
    private BufferedImage e2;
    private BufferedImage e3;
    private BufferedImage e4;
    private BufferedImage e5;
    private BufferedImage e6;
    private BufferedImage e7;
    private BufferedImage e8;
    
    public Enemy(int type, 
                   boolean isFacingRight,
                   int spawnX,
                   int spawnY,
                   int Lifepoints,
                   int damageDealing,
                   int staggeredTime,
                   int minX,
                   int maxX) {
        Type = type;
       
        x = spawnX;
        y = spawnY;
        this.Lifepoints = Lifepoints;
        amountOfDamage = damageDealing;
        random = new Random();
        randomNumberSave = random.nextInt(9);
        this.isStaggeredTime = staggeredTime;
        this.isFacingRight = isFacingRight;
        hasBeatenGame = false;
        
            
        leftX = minX;
        rightX = maxX;
        try {
	        e1 = ImageIO.read(this.getClass().getResource(enemy_1_L));
	        e2 = ImageIO.read(this.getClass().getResource(enemy_1_R));
	        e3 = ImageIO.read(this.getClass().getResource(enemy_1_R_W_1));
	        e4 = ImageIO.read(this.getClass().getResource(enemy_1_R_W_2));
	        e5 = ImageIO.read(this.getClass().getResource(enemy_1_L_W_1));
	        e6 = ImageIO.read(this.getClass().getResource(enemy_1_L_W_2));
	        e7 = ImageIO.read(this.getClass().getResource(enemy_1_L_S)); 
	        e8 = ImageIO.read(this.getClass().getResource(enemy_1_R_S)); 
        } catch(Exception e) { e.printStackTrace();}
        
        if(isFacingRight) ei = e2;
        else ei = e1;

        
    }
    
    public void move(int playerX, int playerY) { 
        if(Type == 1) {
            if(isFacingRight) ei = e2;
            else ei = e1;
            if(Lifepoints <= 0) {
                x = 5000;
                y = 5000;    
                hasDied = true;
            }        
            if(playerX < x && playerX + 200 > x && playerY > y - 40 && playerY < y + 40 && lx > x) {
                isFacingRight = false;
                dx = 0;
            }
            else if(playerX > x && playerX - 200 > x && playerY > y - 40 && playerY < y + 40 && lx < x) {
                isFacingRight = true;
                dx = 0;
            }
            else if(playerX < x && playerX + 200 > x && playerY > y - 40 && playerY < y + 40) {
                ei = e1;
                if(randomTimer == 0) {
                    randomNumberSave = random.nextInt(9);
                    randomTimer++;
                }
                else if(randomTimer <= 10) {             
                    if(randomNumberSave >= 4) {
                        if(FrameCounter < 45) {
                            ei = e5;
                            FrameCounter++;
                        }
                        if(FrameCounter <= 90) {
                            ei = e6;
                            FrameCounter++;
                        }
                        if(FrameCounter == 90) FrameCounter = 0;
                        dx = -3;
                        randomTimer++;
                    } 
                    else if(randomNumberSave <= 3 && randomNumberSave >= 1) {
                        ei = e1;
                        dx = 0;
                        randomTimer++;
                    }
                    else if(randomNumberSave == 0) {
                        if(FrameCounter < 45) {
                            ei = e5;
                            FrameCounter++;
                        }
                        if(FrameCounter <= 90) {
                            ei = e6;
                            FrameCounter++;
                        }
                        if(FrameCounter == 90) FrameCounter = 0;
                        dx = 1;
                        randomTimer++;
                    }                
                    isFacingRight = false;
                }
                if(randomTimer >= 10) {
                    randomTimer = 0;
                    randomNumberSave = random.nextInt(9);
                }
            }     
            else if(playerX > x && playerX - 200 < x && playerY > y - 40 && playerY < y + 40) {
                ei = e2;
                if(randomTimer == 0) {
                    randomNumberSave = random.nextInt(9);
                    randomTimer++;
                }
                else if(randomTimer <=10) {    
                    if(randomNumberSave >= 4) {
                        if(FrameCounter < 45) {
                            ei = e3;
                            FrameCounter++;
                        }
                        if(FrameCounter <= 90) {
                            ei = e4;
                            FrameCounter++;
                        }
                        if(FrameCounter == 90) FrameCounter = 0;
                        dx = 3;
                        randomTimer++;
                    } 
                    else if(randomNumberSave <= 3 && randomNumberSave >= 1) {
                        ei = e2;
                        dx = 0;
                        randomTimer++;
                    }
                    else if(randomNumberSave == 0) {
                        if(FrameCounter < 45) {
                            ei = e3;
                            FrameCounter++;
                        }
                        if(FrameCounter <= 90) {
                            ei = e4;
                            FrameCounter++;
                        }
                        if(FrameCounter == 90) FrameCounter = 0;
                        dx = -1;
                        randomTimer++;
                    }                
                    isFacingRight = true;
                }
                if(randomTimer >= 10) {
                    randomTimer = 0;
                    randomNumberSave = random.nextInt(9);
                }
            }
            
            else {
                dx = 0; 
                randomTimer = 0;
            }
            
            if(isStaggered) {
                if(gotHit) {
                    Lifepoints = Lifepoints - damageDelt;
                    gotHit = false;
                }
                if(isStaggeredTimer > 0) {
                    if(isFacingRight) {
                        dx = -3;
                        ei = e8;
                    }
                    else {
                        dx = 3;
                        ei = e7;
                    }
                    isStaggeredTimer = isStaggeredTimer - 1;
                }            
                else isStaggered = false;
                
            }      
            if(x <= leftX && dx < 0 || x >= rightX && dx > 0) dx = 0;
            lx = playerX;
            x = x + dx;
            y = y + dy;        
            if(gotHitTimer != 0) gotHitTimer -= 1;       
        }
        
    }
    

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean hasDied() {
        return hasDied;
    }
    
    public int getLifepoints() {
        return Lifepoints;
    }
    
    
    public BufferedImage getImage() {
        if(Type == 1) {    
            image = ei;
            return image;
        }
        return image;
    }
    
    public void gotHit(int AmountOfDamage, boolean hitFromRight) {
        if(!isStaggered){
            damageDelt = AmountOfDamage;
            isStaggered = true;
            if(Type == 1) isStaggeredTimer = 30;
            if(gotHitTimer == 0) {
                gotHit = true;
                gotHitTimer = 20;
            }
        }
    }
    
    public int getAmountOfDamage() {
        return amountOfDamage;
    }
    
    public int getStaggeredTime() {
        return isStaggeredTime;
    }
    
    public void reset() {
        x = 2000;
        y = 2000;
    }
    
    public boolean hasWon() {
        return hasBeatenGame;
    }
}