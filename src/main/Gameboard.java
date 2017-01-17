package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;





public class Gameboard extends JPanel {

    private String playerName;
    /**
     * 
     */
    private static final long serialVersionUID = -3123199456705340882L;
    private Player player;
    private Screen screen;
    private int currentScreen;
    private int lastScreen;
    private Sword sword;
    private Invinc invinc;
    private GameTimer gameTimer;
    private XpBar xpBar;


    
    private Enemy[] enemy;
    
    //private Enemy boss;    //Screen 4
    private Boss boss;
    
    private boolean[] enemyDied;
    
    private boolean collectedP0;
    private boolean collectedP1;
    
    private boolean addedButtons;
    
    
    
    private Hud hud;
    private int hud1x;
    private int hud1y;
    private int hud2x;
    private int hud2y;
    
    private int[] n;
    
    private boolean hardcore;
    
    private boolean beatGame;
    private boolean endGame;
    
    private boolean toMenu;
    private boolean toHighscore;
    
    private JButton menu;
    private JButton highscore;
    
    private int RGBValue;
    private double fadeOutTransparency;
    
    private String potion_0 = "potion_0.png";
    private String potion_1 = "potion_1.png";
    private BufferedImage pi0;
    private BufferedImage pi1;
    private BufferedImage p0Image;
    private BufferedImage p1Image;
    
    
    public Gameboard(boolean hardmode, String name, ActionListener e) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);

        playerName = name;
        
        player = new Player();
        screen = new Screen();
        sword = new Sword(player.getX(), player.getY(), player.isFacingRight());
        invinc = new Invinc();
        gameTimer = new GameTimer();
        xpBar = new XpBar();
        currentScreen = 0;
        lastScreen = -1;
        
        hardcore = hardmode;
        
        n = new int[4];
        
        setLayout(null);
        menu = new JButton("Go to Menu");
        menu.addActionListener(e);
        menu.setBounds(300, 600, 150, 50);

        highscore = new JButton("Go to Highscore");
        highscore.addActionListener(e);
        highscore.setBounds(820, 600, 150, 50);
        
        fadeOutTransparency = 1;
        
        enemy = new Enemy[13];
        enemyDied = new boolean[13];
        
        //(Type, isFacingRight, x, y, HP, Damage, StaggeredTime (30 == 1sec), minX, maxX) ) 
        if(!hardcore) {
            enemy[0] = new Enemy(1, false, 800, 291, 2, 1, 150, 290, 866);      //Screen 1
            enemy[1] = new Enemy(1, false, 829, 547, 2, 1, 150, 708 , 835);      //Screen 1
            enemy[2] = new Enemy(1, false, 1110, 579, 2, 1, 150, 873, 1215);     //Screen 1
            enemy[3] = new Enemy(1, false, 255, 99, 3, 1, 150, 55, 265);       //Screen 2
            enemy[4] = new Enemy(1, true, 265, 195, 3, 1, 150, 40, 711);       //Screen 2
            enemy[5] = new Enemy(1, false, 620, 291, 5, 1, 150, 160, 713);      //Screen 2
            enemy[6] = new Enemy(1, false, 420, 483, 3, 1, 150, 162, 480);      //Screen 2
            enemy[7] = new Enemy(1, false, 455, 579, 3, 1, 150, 30, 1210);      //Screen 2
            enemy[8] = new Enemy(1, true, 545, 483, 5, 1, 150, 544, 837);       //Screen 2
            enemy[9] = new Enemy(1, false, 1100, 579, 5, 1, 150, 30, 1210);    //Screen 2   
            enemy[10] = new Enemy(1, true, 50, 483, 7, 1, 150, 34, 110);      //Screen 3
            enemy[11] = new Enemy(1, true, 315, 547, 7, 1, 150, 294, 385);      //Screen 3
            enemy[12] = new Enemy(1, true, 455, 579, 7, 1, 150, 450, 1210);      //Screen 3
            //boss = new Enemy(2, false, 480, 280, 22, 1, false, 60);          //Screen 4
            boss = new Boss(false);
        }
        else {      //Bosskoordinaten: 920|60
            enemy[0] = new Enemy(1, false, 800, 291, 4, 2, 150, 290, 866);      //Screen 1
            enemy[1] = new Enemy(1, false, 829, 547, 4, 2, 150, 708 , 835);      //Screen 1
            enemy[2] = new Enemy(1, false, 1110, 579, 4, 2, 150, 873, 1215);     //Screen 1
            enemy[3] = new Enemy(1, false, 255, 99, 7, 2, 150, 55, 265);       //Screen 2
            enemy[4] = new Enemy(1, true, 265, 195, 7, 2, 150, 40, 711);       //Screen 2
            enemy[5] = new Enemy(1, false, 620, 291, 12, 2, 150, 160, 713);      //Screen 2
            enemy[6] = new Enemy(1, false, 420, 483, 7, 2, 150, 162, 480);      //Screen 2
            enemy[7] = new Enemy(1, false, 455, 579, 7, 2, 150, 30, 1210);      //Screen 2
            enemy[8] = new Enemy(1, true, 545, 483, 12, 2, 150, 544, 837);       //Screen 2
            enemy[9] = new Enemy(1, false, 1100, 579, 12, 2, 150, 30, 1210);    //Screen 2   
            enemy[10] = new Enemy(1, true, 50, 483, 2, 17, 150, 34, 100);      //Screen 3
            enemy[11] = new Enemy(1, true, 315, 547, 3, 17, 150, 294, 375);      //Screen 3
            enemy[12] = new Enemy(1, true, 455, 579, 2, 17, 150, 450, 1210);      //Screen 3
            //boss = new Enemy(2, false, 480, 280, 44, 2, true, 60);          //Screen 4
            boss = new Boss(true);
        }
        try {
        p0Image = ImageIO.read(this.getClass().getResource("potions/" + potion_0));
        p1Image = ImageIO.read(this.getClass().getResource("potions/" + potion_1));
        pi0 = p1Image;
        pi1 = p1Image;
        } catch(Exception d) {d.printStackTrace();}
        hud = new Hud(hardcore);
        hud1x = 10;
        hud1y = 10;
        hud2x = 10;
        hud2y = 45;
        
    }
    


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        
       if(!endGame) {
	        if(currentScreen != lastScreen) g2d.drawImage(screen.getImage(currentScreen), 0, 0, this);
	        if(currentScreen == 0) {
	        	g2d.drawImage(enemy[0].getImage(), enemy[0].getX(), enemy[0].getY(), this); 
	        	g2d.drawImage(enemy[1].getImage(), enemy[1].getX(), enemy[1].getY(), this); 
	        	g2d.drawImage(enemy[2].getImage(), enemy[2].getX(), enemy[2].getY(), this);
	            
	        }
	        if(currentScreen == 1) {           
	        	g2d.drawImage(enemy[3].getImage(), enemy[3].getX(), enemy[3].getY(), this);
	        	g2d.drawImage(enemy[4].getImage(), enemy[4].getX(), enemy[4].getY(), this);
	        	g2d.drawImage(enemy[5].getImage(), enemy[5].getX(), enemy[5].getY(), this);
	        	g2d.drawImage(enemy[6].getImage(), enemy[6].getX(), enemy[6].getY(), this);
	        	g2d.drawImage(enemy[7].getImage(), enemy[7].getX(), enemy[7].getY(), this);
	        	g2d.drawImage(enemy[8].getImage(), enemy[8].getX(), enemy[8].getY(), this);
	        	g2d.drawImage(enemy[9].getImage(), enemy[9].getX(), enemy[9].getY(), this);
	        }
	        if(currentScreen == 2) {
	        	g2d.drawImage(enemy[10].getImage(), enemy[10].getX(), enemy[10].getY(), this);
	        	g2d.drawImage(enemy[11].getImage(), enemy[11].getX(), enemy[11].getY(), this);
	        	g2d.drawImage(enemy[12].getImage(), enemy[12].getX(), enemy[12].getY(), this);
	        }
	        if(currentScreen == 3) {
	        	g2d.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
	        }
	        if(currentScreen == 0) g2d.drawImage(pi0, 242, 262, this);
	        if(currentScreen == 1) g2d.drawImage(pi1, 540, 134, this);
	        
	        if(player.isFacingRight() && !player.isDashing()) g2d.drawImage(invinc.getImage(player.isInvincible()), player.getX() - 3, player.getY() - 10, this);
	        else if(player.isFacingRight() && player.isDashing()) g2d.drawImage(invinc.getImage(player.isInvincible()), player.getX() + 7, player.getY() - 10, this);
	        else if(player.isDashing()) g2d.drawImage(invinc.getImage(player.isInvincible()), player.getX() + 7, player.getY() - 10, this);
	        else g2d.drawImage(invinc.getImage(player.isInvincible()), player.getX() + 3, player.getY() - 10, this);
	        
	        g2d.drawImage(sword.getImage(player.isSwording(), player.isFacingRight()), sword.getX(), sword.getY(), this);
	        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
	        g2d.drawImage(hud.getLifepointsImage(player.getLifepoints()), hud1x, hud1y, this);
	        g2d.drawImage(player.getPotionImage(), 51, 45, this);
	        if(currentScreen == 3) g2d.drawImage(hud.getBossLPImage(boss.getLifepoints()), 910, 10, this);
	        g2d.drawImage(hud.getDashImage(player.getDashingCooldownCounter(), true), hud2x, hud2y, this);
	        g2d.drawImage(hud.getTimerImage(), 590, 5, this);
	        g2d.drawImage(xpBar.getImage(), 10, 90, this); 
	        if(currentScreen == 4)  g2d.drawImage(screen.getImage(currentScreen), 0, 0, this); 
	        g2d.drawImage(gameTimer.getImage1(),665, 10, this);
	        g2d.drawImage(gameTimer.getImage2(), 644, 10, this);
	        g2d.drawImage(gameTimer.getImage3(), 616, 10, this);
	        g2d.drawImage(gameTimer.getImage4(), 595, 10, this);
	        
	        
	        
	        if(!player.hasEntered()) {
	        	
	        	g2d.setColor(Color.DARK_GRAY);
	        	g2d.fillRect(399, 104, 500, 400);
	        	
	        	g2d.setColor(Color.GRAY);
	        	g2d.fillRect(407, 112, 484, 384);
	        	
	        	g2d.setColor(Color.BLACK);
	        	g2d.setStroke(new BasicStroke(3));
	        	g2d.drawRect(399, 104, 500, 400);
	        	
	        	g2d.drawRect(407, 112, 484, 384);
	        	
	        	g2d.setFont(getFont().deriveFont(Font.BOLD, 26f));
	        	g2d.drawString("Controls: ", 410, 140);
	        	
	        	g2d.drawString("Q", 410, 200);
	        	g2d.drawString("Attack", 610, 200);
	
	        	g2d.drawString("D", 410, 230);
	        	g2d.drawString("Dash", 610, 230);
	
	        	g2d.drawString("A", 410, 260);
	        	g2d.drawString("Use Potion", 610, 260);
	
	        	g2d.drawString("Up", 410, 290);
	        	g2d.drawString("(Double-)Jump", 610, 290);
	        	
	        	g2d.drawString("Down", 410, 320);
	        	g2d.drawString("Drop", 610, 320);
	        	
	        	g2d.drawString("Right", 410, 350);
	        	g2d.drawString("Move Right", 610, 350);
	        	
	        	g2d.drawString("Left", 410, 380);
	        	g2d.drawString("Move Left", 610, 380);
	
	        	g2d.drawString("R", 410, 410);
	        	g2d.drawString("Reset", 610, 410);
	        	
	        	g2d.drawString("Kill enemies to level up", 410, 460);
	        	
	        	g2d.drawString("Press Enter to start the game", 410, 488);
	        } 
	        else {
	        	
	        	g2d.setColor(Color.WHITE);
	        	g2d.fillRect(280, 660, 700, 20);
	        	
	        	g2d.setColor(Color.BLACK);
	        	g2d.setStroke(new BasicStroke(3));
	        	g2d.drawRect(280, 660, 700, 20);
	        	
	        	g2d.setFont(getFont().deriveFont(Font.BOLD, 14f));
	        	g2d.drawString("Arrow-Keys - Move/Jump/Drop", 300, 675);
	        	g2d.drawString("Q - Attack", 550, 675);
	        	g2d.drawString("D - Dash", 650, 675);
	        	g2d.drawString("A - Use Potion", 750, 675);
	        	g2d.drawString("R - Reset", 900, 675);
	        }
	        
	        if(beatGame) {
	            if(fadeOutTransparency * 1.03 >= 240) {
	            	endGame = true;
	            }
	           	else g2d.setColor(new Color(0, 0, 0, (int)(fadeOutTransparency *= 1.03)));
	           	g2d.fillRect(0, 0, 1500, 1000);
	            
	        }
	        
       }
       else {
    	   g2d.setColor(Color.BLACK);
    	   g2d.fillRect(140, 20, 1000, 500);
    	   g2d.setColor(Color.GRAY);
    	   g2d.fillRect(150, 30, 980, 480);
    	   
    	   g2d.setColor(Color.BLACK);
    	   g2d.setFont(getFont().deriveFont(Font.BOLD, 40f));
    	   g2d.drawString("You beat the boss! You win!", 380, 100);
    	   g2d.drawImage(boss.getImage(), 510, 140, this);
    	   g2d.drawString("Your time is: " + n[0] + n[1] + ":" + n[2] + n[3], 470, 500);
    	   if(!addedButtons){
    		   add(menu);
        	   add(highscore);
        	   addedButtons = true;
    	   }
    	   
       }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
   

    public void move(ActionEvent e) {
        long previousTime = System.currentTimeMillis();     
        
        if(!endGame) {
	        updateScreen();
	        if(player.died()) {
	            restart();            
	        }
	        if(player.hasEntered()) gameTimer.go();
	        player.move();                
	        sword.move(player.isSwording(), player.getX(), player.getY(), player.isFacingRight());
	        if(currentScreen == 0) {
	            enemy[0].move(player.getX(), player.getY()); 
	            enemy[1].move(player.getX(), player.getY()); 
	            enemy[2].move(player.getX(), player.getY()); 
	        }
	        if(currentScreen == 1) {
	            enemy[3].move(player.getX(), player.getY()); 
	            enemy[4].move(player.getX(), player.getY()); 
	            enemy[5].move(player.getX(), player.getY()); 
	            enemy[6].move(player.getX(), player.getY()); 
	            enemy[7].move(player.getX(), player.getY()); 
	            enemy[8].move(player.getX(), player.getY()); 
	            enemy[9].move(player.getX(), player.getY()); 
	        }
	        if(currentScreen == 2) {
	            enemy[10].move(player.getX(), player.getY()); 
	            enemy[11].move(player.getX(), player.getY()); 
	            enemy[12].move(player.getX(), player.getY()); 
	        }
	        if(currentScreen == 3) {
	            boss.move();
	        }
	        checkDeaths();
	        potionCollected();
	        healAreaCheck();
	        
	        swordCollision();
	        collisionDetectionNPC();
	        wallCollision(player.getX(), player.getY());
	        xpBar.logic(player.getXP(), player.getlvl());
	        gameTimer.move();        
	        if(boss.initiateEnd()) {
	            gameTimer.freezeTimer();
	            n[0] = gameTimer.getNumber1();
	            n[1] = gameTimer.getNumber2();
	            n[2] = gameTimer.getNumber3();
	            n[3] = gameTimer.getNumber4();
	            beatGame = true;
	        }
            repaint();  

        }
        else {
        	if(e.getSource() == menu) toMenu = true;
        	if(e.getSource() == highscore) toHighscore = true;
        	if(!toMenu && !toHighscore) repaint();

        }
        

        long ms_to_sleep = 1000/60 - System.currentTimeMillis() + previousTime;                //60
        try {
            Thread.sleep(ms_to_sleep); 
        }
        catch(Exception d) {
        }
    }
    
    public void updateScreen() {
        int tempX = player.getX();
        int tempY = player.getY();
        
        if(currentScreen == 0) {
            if(tempX > 1255) {
                if(tempY > 505 && tempY < 620) {
                    lastScreen = 0;
                    currentScreen = 1;  
                    player.setX(10);
                    player.setY(580);
                }            
            } 
            if(tempX > 1255) {
                if(tempY > 25 && tempY < 130) {
                    lastScreen = 0;
                    currentScreen = 1;  
                    player.setX(10);
                    player.setY(100);
                }            
            }              
        }     
        if(currentScreen == 1) {
            if(tempX <= 9) {
                if(tempY > 25 && tempY < 160) {
                    lastScreen = 1;
                    currentScreen = 0;  
                    player.setX(1254);
                    player.setY(100);
                }            
            }   
            if(tempX <= 9) {
                if(tempY > 490 && tempY < 610) {
                    lastScreen = 1;
                    currentScreen = 0;
                    player.setX(1254); 
                    player.setY(580);
                }            
            } 
            if(tempX > 1000 && tempX < 1280) {
                if(tempY <= 30) {
                    lastScreen = 1;
                    currentScreen = 2;
                    player.setX(1152);
                    player.setY(650);
                }            
            }
        }
        if(currentScreen == 2) {
            if(tempX > 1150 && tempX < 1217) {
                if(tempY > 680) {
                    lastScreen = 2;
                    currentScreen = 1;
                    player.setX(1152);
                    player.setY(31);
                }                
            }
            if(tempX > 1210 && tempY > 160 && tempY < 321) {
                lastScreen = 2;
                currentScreen = 3;
                player.setX(50);
                player.setY(318-62);
            }
        }
    }
    // 540 134
    public void potionCollected() {
        if(currentScreen == 0) {
            if(player.getX() > 242  && player.getX() < 260 && player.getY() > 220 && player.getY() < 280 && !collectedP0 ) {
                player.addPotion();
                collectedP0 = true;
                pi0 = p0Image;
            }
        }
        if(currentScreen == 1) {
            if(player.getX() > 538  && player.getX() < 570 && player.getY() > 70 && player.getY() < 150 && !collectedP1 ) {
                player.addPotion();
                collectedP1 = true;
                pi1 = p0Image;
            }
        }
    }
    
    public void wallCollision(int playerX, int playerY) {
       if(player.getX() + player.getDX() > 0 && player.getY() + player.getDY() > 0) {
    	   try {
    	   RGBValue = screen.getBGImage(currentScreen).getRGB((int)(player.getX() + player.getDX()), (int) (player.getY() + player.getDY()));
       
       
	       
	       for(int i=0; i < player.getDX(); i++) {                                                                                                             //Rechts
	           if(player.isDashing()) RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i + 28, (int)(player.getY()));
	           else RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i + 28, (int)(player.getY() - player.getDY()));
	           if(RGBValue == -16777216) {
	               player.setX((int)(player.getX() - player.getDX()) + i);       
	               break;
	           }
	           else {
	               RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i + 28 , player.getY() + 15);
	               if(RGBValue == -16777216) {
	                   player.setX((int)(player.getX() - player.getDX()) + i);      
	                   break;
	               }
	               else {
	                   RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i + 28 , player.getY() + 30);
	                   if(RGBValue == -16777216) {
	                       player.setX((int)(player.getX() - player.getDX()) + i);      
	                       break;
	                   }
	                   else {
	                       RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i + 28 , player.getY() + 45);
	                       if(RGBValue == -16777216) {
	                           player.setX((int)(player.getX() - player.getDX()) + i);      
	                           break;
	                       }
	                       else {
	                           RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i + 28 , player.getY() + 50 );
	                           if(RGBValue == -16777216) {
	                               player.setX((int)(player.getX() - player.getDX()) + i);      
	                               break;
	                           }
	                        }
	                    }
	                }
	           }           
	       } 
	       for(int i=0; i > player.getDX(); i--) {                                                                                                             //links
	    	   if(player.isDashing()) RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i , (int)(player.getY()));
	    	   else RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i , (int)(player.getY() - player.getDY())); //-player.getDY() so that PlayerX still moves when PlayerY is stuck
	           if(RGBValue == -16777216) {
	               player.setX((int)(player.getX() - player.getDX()) + i);    
	               break;
	           }
	           else {
	               RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i , player.getY() + 15);
	               if(RGBValue == -16777216) {
	                   player.setX((int)(player.getX() - player.getDX()) + i);      
	                   break;
	               }
	               else {
	                   RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i, player.getY() + 30);
	                   if(RGBValue == -16777216) {
	                       player.setX((int)(player.getX() - player.getDX()) + i);      
	                       break;
	                   }
	                   else {
	                       RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i, player.getY() + 45);
	                       if(RGBValue == -16777216) {
	                           player.setX((int)(player.getX() - player.getDX()) + i);      
	                           break;
	                       }
	                       else {
	                           RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + i, player.getY() + 50);
	                           if(RGBValue == -16777216) {
	                               player.setX((int)(player.getX() - player.getDX()) + i);      
	                               break;
	                           }
	                        }
	                    }
	                }
	           }           
	       }
	       
	       for(int i=0; i < player.getDY(); i++) {                                                                                                                 //Boden
	           RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX(), (player.getY() + 46 + i + (int) player.getDY()));
	           if(RGBValue == -65536) {
	               player.setY(player.getY() - (int)player.getDY() + i);
	               player.jumpcounterResettet();
	               break;
	           }           
	           else {
	               RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + 26,  player.getY() + 46 + i + (int)player.getDY());
	               if(RGBValue == -65536) {
	                   player.setY(player.getY() - (int) player.getDY() + i);
	                   player.jumpcounterResettet();
	                   break;
	               }
	           }            
	       }
	       for(int i=0; i >= player.getDY(); i--) {                                                                                                             //Decke
	           RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX(), player.getY() - i);
	           if(RGBValue == -65536) {
	               player.setY(player.getY() - i);    
	               player.jumpCounterAuf2();
	               break;
	           }
	           else {
	               RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + 26 , player.getY() - i);
	               if(RGBValue == -65536) {
	                   player.setY(player.getY() - i);      
	                   player.jumpCounterAuf2();
	                   break;
	               }
	           }           
	       }
	       
	       
	       //dropPlatform standing
	       for(int i = 0; i < player.getDY(); i++) {
	           RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + (int)player.getDX(), player.getY() + 46 + i + (int)player.getDY());
	           if(RGBValue == -16776961 && !player.isDowning()) {
	               player.setY(player.getY() - (int)player.getDY() + i);
	               player.jumpcounterResettet();
	               break;
	            }
	           else {
	               RGBValue = screen.getBGImage(currentScreen).getRGB(player.getX() + 26 + (int)player.getDX(), player.getY() + 46 + i  + (int)player.getDY());
	               if(RGBValue == -16776961 && !player.isDowning()) {
	                   player.setY(player.getY() - (int)player.getDY() + i);
	                   player.jumpcounterResettet();
	                   break;
	               }           
	            }        
	        }
    	   } catch(ArrayIndexOutOfBoundsException e) { e.printStackTrace();}
       }
    }
     
    public void healAreaCheck() {
        if(currentScreen == 2 && player.getX() > 666 && player.getX() < 766 && player.getY() > 210 && player.getY() < 320) {
            player.restoreHealth();
        }
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public int getN1() {
        return gameTimer.getNumber1();
    }
    
    public int getN2() {
        return gameTimer.getNumber2();
    }
    public int getN3() {
        return gameTimer.getNumber2();
    }
    public int getN4() {
        return gameTimer.getNumber2();
    }
    public boolean getDifficulty() {
        return hardcore;
    }
    public void checkDeaths() {
        if(enemy[0].hasDied() && !enemyDied[0]) {
            player.addXP(2);
            enemyDied[0] = true;
        }
        if(enemy[1].hasDied() && !enemyDied[1]) {
            player.addXP(2);
            enemyDied[1] = true;
        }
        if(enemy[2].hasDied() && !enemyDied[2]) {
            player.addXP(2);
            enemyDied[2] = true;
        }
        if(enemy[3].hasDied() && !enemyDied[3]) {
            player.addXP(4);
            enemyDied[3] = true;
        }
        if(enemy[4].hasDied() && !enemyDied[4]) {
            player.addXP(4);
            enemyDied[4] = true;
        }
        if(enemy[5].hasDied() && !enemyDied[5]) {
            player.addXP(4);
            enemyDied[5] = true;
        }
        if(enemy[6].hasDied() && !enemyDied[6]) {
            player.addXP(4);
            enemyDied[6] = true;
        }
        if(enemy[7].hasDied() && !enemyDied[7]) {
            player.addXP(4);
            enemyDied[7] = true;
        }
        if(enemy[8].hasDied() && !enemyDied[8]) {
            player.addXP(4);
            enemyDied[8] = true;
        }
        if(enemy[9].hasDied() && !enemyDied[9]) {
            player.addXP(4);
            enemyDied[9] = true;
        }
        if(enemy[10].hasDied() && !enemyDied[10]) {
            player.addXP(5);
            enemyDied[10] = true;
        }
        if(enemy[11].hasDied() && !enemyDied[11]) {
            player.addXP(5);
            enemyDied[11] = true;
        }
        if(enemy[12].hasDied() && !enemyDied[12]) {
            player.addXP(5);
            enemyDied[12] = true;
        }
        
    }
    
    public void restart() {
        currentScreen = 0;
        lastScreen = -1;
        player = new Player();
        sword = new Sword(player.getX(), player.getY(), player.isFacingRight());
        hud = new Hud(hardcore);
        invinc = new Invinc();
        gameTimer = new GameTimer();
        xpBar = new XpBar();
        pi0 = p1Image;
        pi1 = p1Image;
        collectedP0 = false;
        collectedP1 = false;
        for(int i = 0; i < 12; i++) {
        	enemyDied[i] = false;
        }
        if(!hardcore) {
            enemy[0] = new Enemy(1, false, 800, 291, 2, 1, 150, 290, 866);      //Screen 1
            enemy[1] = new Enemy(1, false, 829, 547, 2, 1, 150, 708 , 835);      //Screen 1
            enemy[2] = new Enemy(1, false, 1110, 579, 2, 1, 150, 873, 1215);     //Screen 1
            enemy[3] = new Enemy(1, false, 255, 99, 3, 1, 150, 55, 265);       //Screen 2
            enemy[4] = new Enemy(1, true, 265, 195, 3, 1, 150, 40, 711);       //Screen 2
            enemy[5] = new Enemy(1, false, 620, 291, 5, 1, 150, 160, 713);      //Screen 2
            enemy[6] = new Enemy(1, false, 420, 483, 3, 1, 150, 162, 480);      //Screen 2
            enemy[7] = new Enemy(1, false, 455, 579, 3, 1, 150, 30, 1210);      //Screen 2
            enemy[8] = new Enemy(1, true, 545, 483, 5, 1, 150, 544, 837);       //Screen 2
            enemy[9] = new Enemy(1, false, 1100, 579, 5, 1, 150, 30, 1210);    //Screen 2   
            enemy[10] = new Enemy(1, true, 50, 483, 7, 1, 150, 34, 100);      //Screen 3
            enemy[11] = new Enemy(1, true, 315, 547, 7, 1, 150, 294, 375);      //Screen 3
            enemy[12] = new Enemy(1, true, 455, 579, 7, 1, 150, 450, 1210);      //Screen 3
            //boss = new Enemy(2, false, 480, 280, 22, 1, false, 60);          //Screen 4
            boss = new Boss(false);
        }
        else {      //Bosskoordinaten: 920|60
            enemy[0] = new Enemy(1, false, 800, 291, 4, 2, 150, 290, 866);      //Screen 1
            enemy[1] = new Enemy(1, false, 829, 547, 4, 2, 150, 708 , 835);      //Screen 1
            enemy[2] = new Enemy(1, false, 1110, 579, 4, 2, 150, 873, 1215);     //Screen 1
            enemy[3] = new Enemy(1, false, 255, 99, 7, 2, 150, 55, 265);       //Screen 2
            enemy[4] = new Enemy(1, true, 265, 195, 7, 2, 150, 40, 711);       //Screen 2
            enemy[5] = new Enemy(1, false, 620, 291, 12, 2, 150, 160, 713);      //Screen 2
            enemy[6] = new Enemy(1, false, 420, 483, 7, 2, 150, 162, 480);      //Screen 2
            enemy[7] = new Enemy(1, false, 455, 579, 7, 2, 150, 30, 1210);      //Screen 2
            enemy[8] = new Enemy(1, true, 545, 483, 12, 2, 150, 544, 837);       //Screen 2
            enemy[9] = new Enemy(1, false, 1100, 579, 12, 2, 150, 30, 1210);    //Screen 2   
            enemy[10] = new Enemy(1, true, 50, 483, 2, 17, 150, 34, 100);      //Screen 3
            enemy[11] = new Enemy(1, true, 315, 547, 3, 17, 150, 294, 375);      //Screen 3
            enemy[12] = new Enemy(1, true, 455, 579, 2, 17, 150, 450, 1210);      //Screen 3
            //boss = new Enemy(2, false, 480, 280, 44, 2, true, 60);          //Screen 4
            boss = new Boss(true);
        }
    }
    public void swordCollision() {
        if(player.isSwording()) {
        	for(int i = 0; i < enemy.length; i++) {
        		if((i <= 2 && currentScreen == 0) || 
        			((i >= 3 && i <= 9) && currentScreen == 1) || 
        			((i >= 10 && i <= 12) && currentScreen == 2)) {
	        	if(sword.getY() >= enemy[i].getY() && 
	        		sword.getY() <= enemy[i].getY() + 62 ||
	               	sword.getY() + 62 >= enemy[i].getY() &&
	               	sword.getY() + 62 <= enemy[i].getY() + 62) {
		                if(player.isFacingRight()) {
		                    if(sword.getX() + 25 >= enemy[i].getX() &&
		                        sword.getX() + 25 <= enemy[i].getX() + 26 ||
		                        sword.getX() >= enemy[i].getX() &&
		                        sword.getX() <= enemy[i].getX() + 26 ) {
		                        enemy[i].gotHit(player.getlvl(), player.getX() > enemy[i].getX());                        
		                     }
		                }
		                else {
		                    if(sword.getX() >= enemy[i].getX() &&
		                        sword.getX() <= enemy[i].getX() + 26 ||
		                        sword.getX() + 25 >= enemy[i].getX() &&
		                        sword.getX() + 25 <= enemy[i].getX() + 26) {
		                        enemy[i].gotHit(player.getlvl(), player.getX() > enemy[i].getX());                        
		                    }                
		                }
	               }
        		}
        	}
        }
        if(currentScreen == 3) {
        	if(player.isSwording()) {
	            if(sword.getY() < boss.getY() && sword.getY() + 62 > boss.getY() ||
	               sword.getY() > boss.getY() && sword.getY() + 62 < boss.getY() + boss.getImage().getWidth() ||
	               sword.getY() > boss.getY() && sword.getY() + 62 > boss.getY() + boss.getImage().getWidth() && sword.getY() < boss.getY() + boss.getImage().getWidth()) { 
	                   if(sword.getX() < boss.getX() && sword.getX() + 26 > boss.getX() ||
	                      sword.getX() > boss.getX() && sword.getX() + 26 < boss.getX() + boss.getImage().getWidth() ||
	                      sword.getX() > boss.getX() && sword.getX() + 26 > boss.getX() + boss.getImage().getWidth() && sword.getX() < boss.getX() + boss.getImage().getWidth()) {
	                        if(hardcore) boss.gotHit(player.getlvl(), player.getX());  
	                        else boss.gotHit(player.getlvl(), player.getX());   
	                   }             
	            }   
        	}
        }
    }
     
    public void collisionDetectionNPC() {
        if(currentScreen == 0) {            
            if(player.getX() + 32 >= enemy[0].getX() && player.getX() + 32 <= enemy[0].getX() + 32 ||
                player.getX() >= enemy[0].getX() && player.getX() <= enemy[0].getX() + 32) {
                   if(player.getY() + 62 >= enemy[0].getY() && player.getY() + 62 <= enemy[0].getY() + 62 || 
                    player.getY() >= enemy[0].getY() && player.getY() <= enemy[0].getY() + 62) {
                        player.gotHit(enemy[0].getAmountOfDamage(), enemy[0].getStaggeredTime(), player.getX() - 1 < enemy[0].getX() );  
                    }
            }
            if(player.getX() + 32 >= enemy[1].getX() && player.getX() + 32 <= enemy[1].getX() + 32 ||
                player.getX() >= enemy[1].getX() && player.getX() <= enemy[1].getX() + 32) {
                   if(player.getY() + 62 >= enemy[1].getY() && player.getY() + 62 <= enemy[1].getY() + 62 || 
                    player.getY() >= enemy[1].getY() && player.getY() <= enemy[1].getY() + 62) {
                        player.gotHit(enemy[1].getAmountOfDamage(), enemy[1].getStaggeredTime(), player.getX() - 1 < enemy[1].getX());  
                    }
            }
            if(player.getX() + 32 >= enemy[2].getX() && player.getX() + 32 <= enemy[2].getX() + 32 ||
                player.getX() >= enemy[2].getX() && player.getX() <= enemy[2].getX() + 32) {
                   if(player.getY() + 62 >= enemy[2].getY() && player.getY() + 62 <= enemy[2].getY() + 62 || 
                    player.getY() >= enemy[2].getY() && player.getY() <= enemy[2].getY() + 62) {
                        player.gotHit(enemy[2].getAmountOfDamage(), enemy[2].getStaggeredTime(), player.getX() - 1 < enemy[2].getX());  
                    }
            }
        }
        if(currentScreen == 1) {
            if(player.getX() + 32 >= enemy[3].getX() && player.getX() + 32 <= enemy[3].getX() + 32 ||
                player.getX() >= enemy[3].getX() && player.getX() <= enemy[3].getX() + 32) {
                   if(player.getY() + 62 >= enemy[3].getY() && player.getY() + 62 <= enemy[3].getY() + 62 || 
                    player.getY() >= enemy[3].getY() && player.getY() <= enemy[3].getY() + 62) {
                        player.gotHit(enemy[3].getAmountOfDamage(), enemy[3].getStaggeredTime(), player.getX() - 1 < enemy[3].getX());  
                    }
            }
            if(player.getX() + 32 >= enemy[4].getX() && player.getX() + 32 <= enemy[4].getX() + 32 ||
                player.getX() >= enemy[4].getX() && player.getX() <= enemy[4].getX() + 32) {
                   if(player.getY() + 62 >= enemy[4].getY() && player.getY() + 62 <= enemy[4].getY() + 62 || 
                    player.getY() >= enemy[4].getY() && player.getY() <= enemy[4].getY() + 62) {
                        player.gotHit(enemy[4].getAmountOfDamage(), enemy[4].getStaggeredTime(), player.getX() - 1 < enemy[4].getX());  
                    }
            }
            if(player.getX() + 32 >= enemy[5].getX() && player.getX() + 32 <= enemy[5].getX() + 32 ||
                player.getX() >= enemy[5].getX() && player.getX() <= enemy[5].getX() + 32) {
                   if(player.getY() + 62 >= enemy[5].getY() && player.getY() + 62 <= enemy[5].getY() + 62 || 
                    player.getY() >= enemy[5].getY() && player.getY() <= enemy[5].getY() + 62) {
                        player.gotHit(enemy[5].getAmountOfDamage(), enemy[5].getStaggeredTime(), player.getX() - 1 < enemy[5].getX());  
                    }
            }
            if(player.getX() + 32 >= enemy[6].getX() && player.getX() + 32 <= enemy[6].getX() + 32 ||
                player.getX() >= enemy[6].getX() && player.getX() <= enemy[6].getX() + 32) {
                   if(player.getY() + 62 >= enemy[6].getY() && player.getY() + 62 <= enemy[6].getY() + 62 || 
                    player.getY() >= enemy[6].getY() && player.getY() <= enemy[6].getY() + 62) {
                        player.gotHit(enemy[6].getAmountOfDamage(), enemy[6].getStaggeredTime(), player.getX() - 1 < enemy[6].getX());  
                    }
            }
            if(player.getX() + 32 >= enemy[7].getX() && player.getX() + 32 <= enemy[7].getX() + 32 ||
                player.getX() >= enemy[7].getX() && player.getX() <= enemy[7].getX() + 32) {
                   if(player.getY() + 62 >= enemy[7].getY() && player.getY() + 62 <= enemy[7].getY() + 62 || 
                    player.getY() >= enemy[7].getY() && player.getY() <= enemy[7].getY() + 62) {
                        player.gotHit(enemy[7].getAmountOfDamage(), enemy[7].getStaggeredTime(), player.getX() - 1 < enemy[7].getX());  
                    }
            }
            if(player.getX() + 32 >= enemy[8].getX() && player.getX() + 32 <= enemy[8].getX() + 32 ||
                player.getX() >= enemy[8].getX() && player.getX() <= enemy[8].getX() + 32) {
                   if(player.getY() + 62 >= enemy[8].getY() && player.getY() + 62 <= enemy[8].getY() + 62 || 
                    player.getY() >= enemy[8].getY() && player.getY() <= enemy[8].getY() + 62) {
                        player.gotHit(enemy[8].getAmountOfDamage(), enemy[8].getStaggeredTime(), player.getX() - 1 < enemy[8].getX());  
                    }
            }
            if(player.getX() + 32 >= enemy[9].getX() && player.getX() + 32 <= enemy[9].getX() + 32 ||
                player.getX() >= enemy[9].getX() && player.getX() <= enemy[9].getX() + 32) {
                   if(player.getY() + 62 >= enemy[9].getY() && player.getY() + 62 <= enemy[9].getY() + 62 || 
                    player.getY() >= enemy[9].getY() && player.getY() <= enemy[9].getY() + 62) {
                        player.gotHit(enemy[9].getAmountOfDamage(), enemy[9].getStaggeredTime(), player.getX() - 1 < enemy[9].getX());  
                    }
            }
        }
        if(currentScreen == 2) {            
            if(player.getX() + 32 >= enemy[10].getX() && player.getX() + 32 <= enemy[10].getX() + 32 ||
                player.getX() >= enemy[10].getX() && player.getX() <= enemy[10].getX() + 32) {
                   if(player.getY() + 62 >= enemy[10].getY() && player.getY() + 62 <= enemy[10].getY() + 62 || 
                    player.getY() >= enemy[10].getY() && player.getY() <= enemy[10].getY() + 62) {
                        player.gotHit(enemy[10].getAmountOfDamage(), enemy[10].getStaggeredTime(), player.getX() - 1 < enemy[10].getX() );  
                    }
            }
            if(player.getX() + 32 >= enemy[11].getX() && player.getX() + 32 <= enemy[11].getX() + 32 ||
                player.getX() >= enemy[11].getX() && player.getX() <= enemy[11].getX() + 32) {
                   if(player.getY() + 62 >= enemy[11].getY() && player.getY() + 62 <= enemy[11].getY() + 62 || 
                    player.getY() >= enemy[11].getY() && player.getY() <= enemy[11].getY() + 62) {
                        player.gotHit(enemy[11].getAmountOfDamage(), enemy[11].getStaggeredTime(), player.getX() - 1 < enemy[11].getX());  
                    }
            }
            if(player.getX() + 32 >= enemy[12].getX() && player.getX() + 32 <= enemy[12].getX() + 32 ||
                player.getX() >= enemy[12].getX() && player.getX() <= enemy[12].getX() + 32) {
                   if(player.getY() + 62 >= enemy[12].getY() && player.getY() + 62 <= enemy[12].getY() + 62 || 
                    player.getY() >= enemy[12].getY() && player.getY() <= enemy[12].getY() + 62) {
                        player.gotHit(enemy[12].getAmountOfDamage(), enemy[12].getStaggeredTime(), player.getX() - 1 < enemy[12].getX());  
                    }
            }
        }
        if(currentScreen == 3) {
            if(player.getX() + 32 >= boss.getX() && player.getX() + 32 <= boss.getX() + boss.getImage().getWidth() ||
                player.getX() >= boss.getX() && player.getX() <= boss.getX() + boss.getImage().getWidth()) {
                   if(player.getY() + 62 >= boss.getY() && 
                      player.getY() + 62 <= boss.getY() - 20 + boss.getImage().getWidth() || 
                      player.getY() >= boss.getY() &&
                      player.getY() <= boss.getY() - 20 + boss.getImage().getWidth()) {
                        player.gotHit(boss.getAmountOfDamage(), boss.getStaggeredTime(), player.getX() > boss.getX() );  
                    }
            }
        }
    }
    
    public boolean toHighscore() {
    	return toHighscore;
    }
    
    public boolean toMenu() {
    	return toMenu;
    }

    public int[] getN() {
    	return n;
    }
    
    public HighscoreSave getSave() {
    	return new HighscoreSave(n, playerName, hardcore);
    }
    
    
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }

}
