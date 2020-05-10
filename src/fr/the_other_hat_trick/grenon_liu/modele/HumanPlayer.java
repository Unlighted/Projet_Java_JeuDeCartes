package fr.the_other_hat_trick.grenon_liu.modele;

import java.util.*;
/**
 * The human player seen in the game
 * @author Grenon 
 * @author Runkai
 *
 */

public class HumanPlayer extends Player {
    private int timeLeft;
    
    public HumanPlayer(String name, int age) {
    	super(name, age);
    	timeLeft = 0;
    }

    /**
     * Used for a yet unimplemented feature
     */
    public void startTurn() {
    	super.startTurn();// for future uses
    	timeLeft = 60;
    	
    }

    /**
     * Used for a yet unimplemented feature
     */
   public void chooseRandomCard() {
    }


   /**
    * The decision of the player to decide the trick (console) Needs to be changed to using Strategy pattern
    * @param trickPile A reference to the trickpile
    * @param setrules All the set rules
    */
   public void decideTrick(TrickPile trickpile, HashSet<Integer> setRules) {
	   Iterator<Integer> seeRules = setRules.iterator();
	   boolean hasRule = false;
	   while(seeRules.hasNext()&&hasRule==false)//needs to be replaced by another Strategy
		   if(seeRules.next().intValue()==3)
			   hasRule = true;
	   
	   System.out.println("The trick is "+trickpile.showTrick());
   	
   		int it=this.consoleInput("Do you want to turn a trick? 0 for no, 1 for yes,2 for risk mode", 0, 2);// risk mode rule
   		if (it==2 && hasRule == true) {
   			if(this.getOwnedProps().get(0).getVisibility()==false&&this.getOwnedProps().get(1).getVisibility()==false) {
   				System.out.println("Welcome to risk mode,now all your props are turned on, in exchange, you can see the next trick before you make your decision");
   				System.out.println("the first down Trick is "+trickpile.showTrickDown());
   				this.getOwnedProps().get(0).changeVisibility();
   				this.getOwnedProps().get(1).changeVisibility();
   			this.decideTrick(trickpile, setRules);
   			}
   			else {
   			System.out.println("You must have two invisible props to activate the risk mode");
   			this.decideTrick(trickpile, setRules);
   			}
   		}
   		else if(hasRule == false && it==2){// returns to decideTrick
   			System.out.println("You should have had activated the risk mode");
   			this.decideTrick(trickpile, setRules);
   		}
   	
   		if(it==1) {
   			trickpile.turnTrick();
   			System.out.println("Trick turned, now the trick is "+trickpile.showTrick());
   		}
   	
   	
   	}

   /**
    * The decision of how to rearrange the hand, used by the interface
    * @param prop7 The seventh prop, on top of the unowned props
    * @param d1  the emplacement of the first card
    * @param d2 The emplacement of the second card
    * @return the prop not kept
    */
   public Prop rearrangeHand(Prop prop7, int d1, int d2) {
	   ArrayList<Prop> tempList = new ArrayList<>();
		tempList.addAll(getOwnedProps());
		tempList.add(prop7);
		this.setOwnedProps(0, tempList.get(d1-1));// set the cards in the order given
		this.setOwnedProps(1, tempList.get(d2-1));
		int indice, returnValue=0;
		for(indice=1; indice<=3; indice++) {
			if(indice != d1 && indice != d2)
				returnValue = indice;
		}
		return tempList.get(returnValue-1);
   }
   
   

   /**
    * The decision of how to rearrange the hand, used by the console
    * @param prop7 The seventh prop, on top of the unowned props
    * @return The unneeded prop
    */
   public Prop rearrangeHand(Prop prop7) {
  
	System.out.println("The 7th prop is "+prop7.getName()+". Which two would you like to keep?");
    int choice1 =this.consoleInput("first card", 1, 3);
    int choice2 = choice1;
    while(choice2==choice1) {
    	choice2=this.consoleInput("second card", 1, 3);
    }
    ArrayList<Prop> tempList = new ArrayList<>();
	tempList.addAll(getOwnedProps());
	tempList.add(prop7);
	this.setOwnedProps(0, tempList.get(choice1-1));// set the cards in the order given
	this.setOwnedProps(1, tempList.get(choice2-1));
	int indice, returnValue=0;
	for(indice=1; indice<=3; indice++) {
		if(indice != choice1 && indice != choice2)
			returnValue = indice;
	}
	
	
	
	this.getOwnedProps().get(0).setVisible(false);
	this.getOwnedProps().get(1).setVisible(false);
	return tempList.get(returnValue-1);
   	}
   
   
   
   

   /**
    * Chooses if the player want to perform or not
    * @param trp A reference to the trickpile
    * @return The choice made
    */
	public boolean decidePerform(TrickPile trp) {
		boolean ret=false;
		if (this.ableToPerform(trp)) {
			int decision=this.consoleInput("You have all the props necessary, 0 to perform,1 to not", 0, 1);
			if (decision==0) {//decision=0, perform the trick, decision=1, not perform
				this.performTrick(trp);
		  		ret=true;
			}
		}
		else {
			this.chooseChangeVisibility();
		}
		return ret;
    }

	  /**
	   * The choice of which card to swap with who
	   * @param players All the players in the game
	   * @param trickPile A reference to the trickPile
	   */
	public void chooseSwap(ArrayList<Player> players, TrickPile trickPile) {
	  	System.out.println(players.get(0).getName()+" has "+players.get(0).getOwnedProps().get(0).toString()+" "+players.get(0).getOwnedProps().get(1).toString());// show known cards
	  	System.out.println(players.get(1).getName()+" has "+players.get(1).getOwnedProps().get(0).toString()+" "+players.get(1).getOwnedProps().get(1).toString());
	  	System.out.println(players.get(2).getName()+" has "+players.get(2).getOwnedProps().get(0).toString()+" "+players.get(2).getOwnedProps().get(1).toString());
	  	boolean rate=true;
	  	int propgiven=this.consoleInput("choose one of your prop to swap, 1 or 2", 1, 2);
	  	String otherplayer=this.consoleInput("choose a player to swap, with the name");
	  	int propexchange=this.consoleInput("choose one of his prop to swap, 1 or 2", 1, 2);
	  	for(Player o: players) {
	  		if(o.getName().equals(otherplayer) && otherplayer!=this.getName()) {
	  			System.out.println("success");
	  			this.swapCard(propgiven, o, propexchange);
	  			rate = false;
	  		}
	  	}
	  	if(rate)
	  		this.chooseSwap(players, trickPile);
	}


	   /**
	    * The choice of which card to change visibility
	    */
    public void chooseChangeVisibility() {
    	Iterator<Prop> oneVisible = super.getOwnedProps().listIterator();
    	int cardVisible=0, counter=0, propsUp=0;
    	while(oneVisible.hasNext()) { // counts visible cards
    		if(oneVisible.next().getVisibility()) {
    			if(cardVisible!=0)
    				propsUp=3;
    			cardVisible = counter+1;
    		}
    		counter++;
    	}
    	if(cardVisible==2 && propsUp == 0) 
    		this.forfeit(1);
    	else if(cardVisible==1 && propsUp == 0)
    		this.forfeit(2);
    	else if(propsUp!=3) { // If not all cards are visible
    		propsUp=this.consoleInput(this.getName()+" forfeit, choose one of your props to turn up", 1, 2);
			this.forfeit(propsUp);
    	}
    	else
    		this.forfeit(propsUp);
    	
    }

}
