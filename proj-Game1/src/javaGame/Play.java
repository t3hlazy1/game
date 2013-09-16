package javaGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {
int ammo = 30;
int totalAmmo = 200;
int shotDelta = 200;
int posX = 250;
int posY = 250;
int boxX = 200;
int boxY = 200;
int movAmount = 1;
long lastMovement = System.currentTimeMillis();
long lastShot = System.currentTimeMillis();
boolean mouseUp = true;
boolean auto = false;
boolean noAmmo = false;

Image character = null;
  public Play(int state){
    
  }
  
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
    character = new Image("res/buckysFront.png");
  }
  
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
    
    g.drawString("Ammo: " + ammo + "/" + totalAmmo, 10, 30);
    g.drawString("" + System.currentTimeMillis(), 50, 50);
    if (noAmmo){
      g.drawString("No Ammo!", 300, 200);
    }
    character.draw(posX, posY);
    g.drawLine(posX, posY+20, Mouse.getX(), 500-Mouse.getY());
    g.drawRect(boxX, boxY, 50, 50);
    g.drawString("posX,posY" + posX + "," + posY, 300,10);
    g.drawString("boxX,boxY" + boxX + "," + boxY, 300,30);
  }
  
  public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
    
    Input input = gc.getInput();
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
