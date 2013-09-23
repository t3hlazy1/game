package javaGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {
int ammo = 30; //ammo in gun
int totalAmmo = 200; //total ammo not in gun
int shotDelta = 200; //time between shots in ms
//positions
//we need to figure out a better way to have object positions. Do we use an array/linked list?
//like each object has an id, and it's position is held in an int array positions
//so object 2 would be drawn at (positionX[2],positionY[2])?
//prob should create a topic on github
int posX = 250; 
int posY = 250;
int boxX = 200;
int boxY = 200;
int menu1x = 350; 
int menu1y = 100;
int menu2x = 350; 
int menu2y = 150;
int menu3x = 350; 
int menu3y = 200;
int menu4x = 350; 
int menu4y = 250;
int money = 1000;
int moneyLeft = 0;  
int movAmount = 1; //ammount char moves a frame
long lastMovement = System.currentTimeMillis();
long lastShot = System.currentTimeMillis(); 
boolean mouseUp = true; //if mouse was released
boolean auto = true; //automatic gun or not (hold mouse to shoot, or click to shoot)
boolean noAmmo = false; //if you have ammo or not
boolean menuOpen = false; 
String[] GunArray = {"M4","AK", "trest", "asdf"}; 

Image character = null;
  public Play(int state){
    
  }
  
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
    character = new Image("res/buckysFront.png");
  }
  
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{ 
	  g.setColor(Color.white); 
	  g.drawRect(245, 360, 220, 50); 
	  g.drawString("Press V for weapon type", 250, 375); 
    g.drawString("Ammo: " + ammo + "/" + totalAmmo, 10, 30);
    g.drawString("" + System.currentTimeMillis(), 50, 50);
    if (noAmmo){
      g.drawString("No Ammo!", 300, 200);
    }
    if(auto){
    	g.drawString("Gun type: Automatic", 100, 100); 
    }
    else{
    	g.drawString("Gun type: Manual", 100, 100); 
    }
    if(menuOpen){
    	g.setColor(Color.red); 
    	g.fillRect(menu1x, menu1y, 95, 45);
    	g.setColor(Color.black); 
    	g.drawString(GunArray[0], 385, 115); 
    	g.setColor(Color.red);
    	g.fillRect(menu2x, menu2y, 95, 45);
    	g.setColor(Color.black); 
    	g.drawString(GunArray[1], 385, 160);
    	g.setColor(Color.red);
    	g.fillRect(menu3x, menu3y, 95, 45);
    	g.setColor(Color.black); 
    	g.drawString(GunArray[2], 385, 215);
    	g.setColor(Color.red);
    	g.fillRect(menu4x, menu4y, 95, 45);
    	g.setColor(Color.black); 
    	g.drawString(GunArray[3], 385, 270);
    }
    g.setColor(Color.white); 
    character.draw(posX, posY);
    g.drawLine(posX, posY+20, Mouse.getX(), 500-Mouse.getY());
    g.drawRect(boxX, boxY, 50, 50);
    g.drawString("posX,posY" + posX + "," + posY, 300,10);
    g.drawString("boxX,boxY" + boxX + "," + boxY, 300,30);
  }
  
  public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
    
    Input input = gc.getInput();
    if(input.isKeyPressed(Input.KEY_V)){
    	if(auto == false){
    		auto = true; 
    	}
    	else{
    		auto = false; 
    	}
    }
    if(input.isKeyDown(Input.KEY_M)){
    	menuOpen = true; 
    }
    else{
    	menuOpen = false; 
    }
    if(Mouse.isButtonDown(0) && ((long)(System.currentTimeMillis() - lastShot) > shotDelta) && (auto || mouseUp)){
      if (ammo > 0){
      ammo--;
      }
      lastShot = System.currentTimeMillis();
      mouseUp = false;
    }
    if (Mouse.isButtonDown(0)){
      mouseUp = false;
    }
    else{
      mouseUp = true;
    }
    if (input.isKeyPressed(Input.KEY_R) || ammo == 0){
      int ammoNeeded = 30 - ammo;
      if (totalAmmo >= ammoNeeded){
        totalAmmo -= ammoNeeded;
        ammo += ammoNeeded;
      }
      else{
        ammo += totalAmmo;
        totalAmmo = 0;
      }
      if (ammo < 0){
        ammo = 0;
      }
      if (totalAmmo < 0){
        totalAmmo = 0;
      }
      if (ammo == 0 && totalAmmo == 0){
        noAmmo = true;
      }
    }
    if (System.currentTimeMillis() - lastMovement > 15){
    if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)){
      posY -= movAmount;
      character = new Image("res/buckysBack.png");
    }
    if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)){
      posY += movAmount;
      character = new Image("res/buckysFront.png");
    }
    if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)){
      posX -= movAmount;
      character = new Image("res/buckysLeft.png");
    }
    if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)){
      posX += movAmount;
      character = new Image("res/buckysRight.png");
    }
    lastMovement = System.currentTimeMillis();
    }
    
    if (input.isKeyDown(Input.KEY_E) && (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))){
      if (posX-boxX == -40){
        boxX -= movAmount;
      }
    }
    if (input.isKeyDown(Input.KEY_E) && (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))){
      if (posX-boxX == -40){
        boxX += movAmount;
      }
    }
  }
  
  public int getID(){
    return 1;
  }
  
}
//testttt
//test22