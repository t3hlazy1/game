package javaGame;
//test
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
  
  public static final String gameName = "Java Game!";
  public static final int menu = 0;
  public static final int play = 1;
  
  public Game(String gameName){
    super(gameName);
    this.addState(new Menu(menu));
    this.addState(new Play(play));
  }
  
  public void initStatesList(GameContainer gc) throws  SlickException{
    this.getState(menu).init(gc, this);
    this.getState(play).init(gc, this);
    this.enterState(menu);
    
  }
  
	public static void main(String[] args){
	  AppGameContainer appGc;
	  try{
	    appGc = new AppGameContainer(new Game(gameName));
	    appGc.setDisplayMode(500, 500, false);
	    appGc.start();
	  }catch(SlickException e){
	    e.printStackTrace();
	  }
	}
//test2
	//test ajsdhfjaksdfkasd
	//test 3sda:LKSfjalk;sdhfklasdhfk;lashdf;ashdfasdf
	//HELLO, IS THIS WORKING
	//another test
}
