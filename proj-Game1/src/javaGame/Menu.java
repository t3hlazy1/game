package javaGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {

  public Menu(int state){
    
  }
  
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
    
  }
  
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
    int mouseX = Mouse.getX();
    int mouseY = Mouse.getY();g.drawRect(180, 185, 125, 50);
    g.drawString("Play Game", 200, 200);
    g.drawString("X: " + mouseX + " Y: " + mouseY, 10, 40);
  }
  
  public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
    int mouseX = Mouse.getX();
    int mouseY = Mouse.getY();
    if (mouseX >= 180 && mouseX <= 305 && mouseY >= 265 && mouseY <= 315){
      if (Mouse.isButtonDown(0)){
        sbg.enterState(1);;
      }
    }
  }
  
  public int getID(){
    return 0;
  }
  
}
