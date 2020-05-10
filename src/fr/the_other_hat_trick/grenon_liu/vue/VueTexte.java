package fr.the_other_hat_trick.grenon_liu.vue;

import java.awt.AWTException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import com.sun.glass.events.KeyEvent;
import com.sun.glass.ui.Robot;

import fr.the_other_hat_trick.grenon_liu.modele.AI;
import fr.the_other_hat_trick.grenon_liu.modele.Game;
import fr.the_other_hat_trick.grenon_liu.modele.HumanPlayer;
import fr.the_other_hat_trick.grenon_liu.modele.Player;
/**
 * Allow to artificially play with the console and the interface, still without added rules
 * Can only work when you come back the same turn you left
 * @author Grenon
 * @author Runkai
 *
 */
public class VueTexte extends Thread implements Observer{
	private Scanner consoleEntryInt;
    
    private Scanner consoleEntryString;
    
    private Game currentgame;
    
    private boolean end=false;
    
    private java.awt.Robot robot1;
    
    public int consoleInput(String print, int valmin, int valmax) {
    	int checker=valmax+1;
    	
    		System.out.println(print);
    		checker = consoleEntryInt.nextInt();
    		
    	return checker;
    }
    
    
    public String consoleInput(String print) {
    	System.out.println(print);
    	
    	return consoleEntryString.nextLine();
    	}
    /**
     * The constructor of the CMD view
     * @param game A reference to the current game
     */
    public VueTexte(Game game) {
    	consoleEntryInt = new Scanner(System.in); // creates console entries
    	consoleEntryString = new Scanner(System.in);
    	this.currentgame=game;
    	currentgame.addObserver(this);
	}
	
    /**
     * Allows to decide which trick to perform
     */
    public void decideTurn() {
		System.out.println("Now the trick is ");
		int d=consoleInput("1 to turn, 0 for not turn", 0, 1);
		if (d==1) {
			currentgame.getTrickPile().turnTrick();
		}
		currentgame.setEtat(2); // refreshes the interface
		currentgame.refresh();
		
		
		
	}
	public void chooseSwap() {
		
	}
	
	/**
	 * Getting notified by the model about the trick
	 */
	public void update(Observable o, Object arg) {
		if(o instanceof Game) {
			Game temporary=((Game)o);
			System.out.println("The current Trick is "+temporary.getTrickPile().showTrick());
			
			
		}
		
		
	}


	/**
	 * The thread to run the text interface
	 */
	public void run() {
		do {
			if (currentgame.getLastTurn()==3) {
				end=true; // if run when it's already over
			}
		
			
		switch (currentgame.getEtat()) { // for each states waits for an answer, uses the functions of the model
		case 1:
			System.out.println(currentgame.getPlayer().get(currentgame.getTurn()-1));
			this.decideTurn();
			if(currentgame.getTrickPile().getTricksLeftDown()==0) {
    			System.out.println("Final Turn !!! The current Trick is " + currentgame.getTrickPile().showTrick());
				currentgame.setLastTurn(currentgame.getLastTurn() + 1);
    		}
			currentgame.refresh();;
			break;
		case 2:
			currentgame.getPlayer().get(currentgame.getTurn()-1).chooseSwap(currentgame.getPlayer(), currentgame.getTrickPile()); 
			currentgame.setEtat(3);
			currentgame.refresh();
		case 3:
			
			boolean performed=currentgame.getPlayers().get(currentgame.getTurn()-1).decidePerform(currentgame.getTrickPile());
    		if(performed) {
    			currentgame.setEtat(4);
    		}else {
    			currentgame.setTurn(currentgame.getTurn()+1);
				if(currentgame.getTurn()>3) {
					currentgame.setTurn(1);
				}
				currentgame.continuerAI();// let the AI play
				currentgame.setEtat(1);
			}
    		if(currentgame.getLastTurn()==3||(currentgame.getTrickPile().getTricksLeftDown()==0&&currentgame.getTrickPile().showTrick().getName()!="The Other Hat Trick")) {// if it was the last turn
    			currentgame.endGame();
    		}
			currentgame.refresh();
			break;
		case 4:
			currentgame.changeUnownedProps(); // chooses the card to keep
			currentgame.setTurn(currentgame.getTurn()+1);
			if(currentgame.getTurn()>3) {
				currentgame.setTurn(1);
			}
			currentgame.continuerAI();
			if(currentgame.getLastTurn()==3||(currentgame.getTrickPile().getTricksLeftDown()==0&&currentgame.getTrickPile().showTrick().getName()!="The Other Hat Trick")) {
    			currentgame.endGame();
    		}
			currentgame.setEtat(1);
			currentgame.refresh();
			break;
			
			
			
		
		default:
			break;
		
		}
		
		}while(this.end==false); // as long as the step hasn't ended
	}


	
	

}
