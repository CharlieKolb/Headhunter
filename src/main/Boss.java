package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Boss {

	private enum bState_ {
		
		Idyll(1, 0, 0),
		Started(0, 6, -5),
		Phase1(60, 4, 0),
		Phase1Hit(0, 7, 0),
		Phase2(60, 8, 0),
		Phase2Hit(0, 13, 0),
		Dead(0, 0, 0),
		Falling(0, 0, 10);
		
		private int staggeredCD;
		private double dx, dy;
		
		
		bState_(int cooldown, double dx, double dy) {
			staggeredCD = cooldown;
			this.dx = dx;
			this.dy = dy;
		}
	}
	
	private bState_ bState;
	
	private double x, y, dx, dy;
	private int maxLifepoints;
	private int lifepoints;
	
	private boolean moveRight;
	
	private boolean isStaggered;
	private int staggeredTime;
	private int frameCounter;
	
	private boolean initiateEnd;
	
	private String[][] bosssprites;
	private BufferedImage[][] bossImages;
	private BufferedImage deadImage;
	private BufferedImage currentImage;
	
	public Boss(boolean hardmode) {
		if(hardmode) maxLifepoints = 44;
		else maxLifepoints = 22;
		
		lifepoints = maxLifepoints;
		
		x = 480;
		y = 280;
		
		bState = bState_.Idyll;
		
		bosssprites = new String[2][7];
        bossImages= new BufferedImage[2][7];   
        
        bosssprites[0][0] = "boss_0_0_0.png";
        bosssprites[0][1] = "boss_0_0_1.png";
        bosssprites[0][2] = "boss_0_0_2.png";
        bosssprites[0][3] = "boss_0_0_3.png";
        bosssprites[0][4] = "boss_0_S_0.png";
           
        bosssprites[1][0] = "boss_1_0_0.png";
        bosssprites[1][1] = "boss_1_0_1.png";
        bosssprites[1][2] = "boss_1_0_2.png";
        bosssprites[1][3] = "boss_1_0_3.png";
        bosssprites[1][4] = "boss_1_S_0.png";
        
        String deadSprite = "boss_defeated.png";
            
        try {
        bossImages[0][0] = ImageIO.read(this.getClass().getResource(bosssprites[0][0]));
        bossImages[0][1] = ImageIO.read(this.getClass().getResource(bosssprites[0][1]));
        bossImages[0][2] = ImageIO.read(this.getClass().getResource(bosssprites[0][2]));
        bossImages[0][3] = ImageIO.read(this.getClass().getResource(bosssprites[0][3]));
        bossImages[0][4] = ImageIO.read(this.getClass().getResource(bosssprites[0][4])); 
            
        bossImages[1][0] = ImageIO.read(this.getClass().getResource(bosssprites[1][0]));
        bossImages[1][1] = ImageIO.read(this.getClass().getResource(bosssprites[1][1]));
        bossImages[1][2] = ImageIO.read(this.getClass().getResource(bosssprites[1][2]));
        bossImages[1][3] = ImageIO.read(this.getClass().getResource(bosssprites[1][3]));
        bossImages[1][4] = ImageIO.read(this.getClass().getResource(bosssprites[1][4]));
        
        deadImage = ImageIO.read(this.getClass().getResource(deadSprite));
            
        } catch (Exception e) { e.printStackTrace();}
            
        currentImage = bossImages[0][0];
	}
	
	public void move() {
		
		if(frameCounter > 0) frameCounter--;
		if(staggeredTime > 0) {
			isStaggered = true;
			staggeredTime--;
		} else isStaggered = false;
		
		spin();
		
		handleStates();
		handleMovement();
	}
	
	private void spin() {
		if((bState == bState_.Phase1 || bState == bState_.Phase2) && frameCounter-- == 0) {
			if(moveRight) {
				if(currentImage == bossImages[0][0]) currentImage = bossImages[0][1];
				else if(currentImage == bossImages[0][1]) currentImage = bossImages[0][2];
				else if(currentImage == bossImages[0][2]) currentImage = bossImages[0][3];
				else if(currentImage == bossImages[0][3]) currentImage = bossImages[0][0];
				
				if(currentImage == bossImages[1][0]) currentImage = bossImages[1][1];
				else if(currentImage == bossImages[1][1]) currentImage = bossImages[1][2];
				else if(currentImage == bossImages[1][2]) currentImage = bossImages[1][3];
				else if(currentImage == bossImages[1][3]) currentImage = bossImages[1][0];
			}
			else {
				if(currentImage == bossImages[0][0]) currentImage = bossImages[0][3];
				else if(currentImage == bossImages[0][1]) currentImage = bossImages[0][0];
				else if(currentImage == bossImages[0][2]) currentImage = bossImages[0][1];
				else if(currentImage == bossImages[0][3]) currentImage = bossImages[0][2];
				
				if(currentImage == bossImages[1][0]) currentImage = bossImages[1][3];
				else if(currentImage == bossImages[1][1]) currentImage = bossImages[1][0];
				else if(currentImage == bossImages[1][2]) currentImage = bossImages[1][1];
				else if(currentImage == bossImages[1][3]) currentImage = bossImages[1][2];
			}
			
			if(bState == bState_.Phase1) frameCounter = 30;
			else frameCounter = 20;

		} 
	}
	
	private void handleStates() {
		switch(bState) {
		case Idyll: 
			if(isStaggered) {
				bState = bState_.Started;
				currentImage = bossImages[0][4];
			}
			break;
		case Started:
			if(y <= 50) {
				bState = bState_.Phase1;
				currentImage = bossImages[0][0];
			}
			break;
		case Phase1:
			if(isStaggered) {
				bState = bState_.Phase1Hit;
				currentImage = bossImages[0][4];
			}
			break;
		case Phase1Hit:
			if(!isStaggered) {
				if(lifepoints < maxLifepoints/2) {
					bState = bState_.Phase2;
					currentImage = bossImages[1][0];
				}
				else {
					bState = bState_.Phase1;
					currentImage = bossImages[0][0];
				}
			}
			break;
		case Phase2:
			if(isStaggered) {
				if(lifepoints <= 0) {
					currentImage = deadImage;
					bState = bState_.Dead;
					frameCounter = 45;
					break;
				}
				bState = bState_.Phase2Hit;
				currentImage = bossImages[1][4];
			}
			break;
		case Phase2Hit:
			if(!isStaggered) {
				if(lifepoints <= 0) {
					currentImage = deadImage;
					bState = bState_.Dead;
					frameCounter = 45;
				}
				else {
					bState = bState_.Phase2;
					currentImage = bossImages[1][0];
				}
			}
			break;
		case Dead:
			currentImage = deadImage;
			if(frameCounter == 1) bState = bState_.Falling;
			break;
		case Falling:
			if(y > 1000) initiateEnd = true;
			break;
			
		}
	}
	
	private void handleMovement() {
		if(x > 1000 || x < 100) moveRight = !moveRight;
		
		if(moveRight) dx = bState.dx;
		else dx = -bState.dx;
		
		dy = bState.dy;
		
		x += dx;
		y += dy;
	}
	
	public void gotHit(int damage, int playerX) {
		if(!isStaggered) {
			switch(bState) {
			case Idyll:
				lifepoints = lifepoints - damage;
				staggeredTime = 10;
				if(playerX < x) moveRight = true;
				else moveRight = false;
				currentImage = bossImages[0][4];
				break;
			case Phase1:
				lifepoints = lifepoints - damage;
				staggeredTime = bState.staggeredCD;
				if(playerX < x) moveRight = true;
				else moveRight = false;
				currentImage = bossImages[0][4];
				break;
			case Phase2: 
				lifepoints = lifepoints - damage;
				staggeredTime = bState.staggeredCD;
				if(playerX < x) moveRight = true;
				else moveRight = false;
				currentImage = bossImages[0][4];
				break;
			case Started: case Phase1Hit: case Phase2Hit: break;
			default:
				break;
			}
		}
	}
	
	public int getAmountOfDamage() {
		if(maxLifepoints == 44) return 2;
		else return 1;
	}
	
	public int getStaggeredTime() {
		return 60;
	}
	
	public BufferedImage getImage() {
		return currentImage;
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public int getLifepoints() {
		return lifepoints;
	}
	
	public boolean initiateEnd() {
		return initiateEnd;
	}
}
