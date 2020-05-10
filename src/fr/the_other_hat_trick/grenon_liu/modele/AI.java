package fr.the_other_hat_trick.grenon_liu.modele;
import java.util.*;

/**
 * An AI with simple strategy designed to play with other players
 * @author Grenon Maxence Liu Runkai
 * 
 */

public class AI extends Player {
	
	private Strategy currentStrategy; // strategy used by IA
	private static int instance=0; // gives a name

    // private ArrayList<Prop> seenImportantCards;
    /**
     * Creates an instance of the robot
     * 
     */
    public AI() {
    	super("Robot "+(instance+1), 100);
    	currentStrategy = new StrategyLosing(); // Acts for the long game by default
    	instance++;
    }
    
    /**
     * Allows the IA to change its strategy
     * @param allPlayers All the players in the game
     */
    public void changeStrategy(ArrayList<Player> allPlayers) {
    	int counter=0;
    	Iterator<Player> seeAllPlayers = allPlayers.listIterator();
    	while(seeAllPlayers.hasNext()) { // checks if he is first
    		if(super.getTotalPoints()>seeAllPlayers.next().getTotalPoints())
    			counter++;
    	}
    	if(counter == 2) { // choice of strategy depending on classment
    		this.currentStrategy = new StrategyWinning();
    	}
    	else {
    		this.currentStrategy = new StrategyLosing();
    	}
    }

    public void startTurn() { //for further uses
    	super.startTurn();
    }
    
    /**
     * Allows the AI to decide the trick to play
     * @param trickPile A reference to the trickpile
     * @param setRules The current rules
     */
    public void decideTrick(TrickPile trickPile, HashSet<Integer> setRules) {
    	boolean choice = currentStrategy.chooseTurnTrick(trickPile, super.getOwnedProps()); // gets the choice to turn the trick or not
    	if(choice == true)
    		trickPile.turnTrick();
    	System.out.println("The current Trick is " + trickPile.showTrick());
    }
    /**
     * Allows the AI to decide to perform
     * @param trickPile A reference to the trickpile
     * @return The choice he made
     */
    public boolean decidePerform(TrickPile trickPile) {
    	boolean choice=false;
    	if(ableToPerform(trickPile)) // he decides if he can
    		choice = currentStrategy.choosePerformTrick();
    	if(choice) {
    		this.performTrick(trickPile);
    	}
    	else { // he forfeit
    		this.chooseChangeVisibility();
    	}
    	return choice;
    }
    /**
     * Allows the AI to choose which card to swap
     * @param players All the players in the game
     * @param trickPile A reference to the trickPile
     */
 	public void chooseSwap(ArrayList<Player> players, TrickPile trickPile) {
 		Player buffer;
 		Iterator<Player> seePlayers = players.listIterator();
 		ArrayList<Player> otherPlayers = new ArrayList<>();
 		while(seePlayers.hasNext()) { // adds the other players to the list
 			if(!(buffer = seePlayers.next()).getName().equals(this.getName()))
 				otherPlayers.add(buffer);
 		}
 		Trick toPerform = trickPile.showTrick();
 		int requiProp=1, requiTrick=1, counter=1, choice=0;
 		ArrayList<String> checkHave1 = toPerform.getRequi(1); // checks if he stands a chance to perform the trick
 		ArrayList<String> checkHave2 = toPerform.getRequi(1);
 		Iterator<String> itCheckProps1 = checkHave1.iterator();
 		Iterator<String> itCheckProps2 = checkHave2.iterator();
 		Iterator<Prop> itOwnedProps;
 		String bufferString;
 		while(itCheckProps1.hasNext()) { // checks if he has the first
 			bufferString = itCheckProps1.next();
 			itOwnedProps = getOwnedProps().iterator();
 			while(itOwnedProps.hasNext()) {
 				if(bufferString.equals(itOwnedProps.next().getName())) {
 					requiProp=counter;
 					requiTrick=2;
 				}
 			}
 			counter++;
 		}
 		while(itCheckProps2.hasNext()) { // checks if he has the second
 			bufferString = itCheckProps2.next();
 			itOwnedProps = getOwnedProps().iterator();
 			while(itOwnedProps.hasNext()) {
 				if(bufferString.equals(itOwnedProps.next().getName())) {
 					requiProp=counter;
 					requiTrick=1;
 				}
 			}
 			counter++;
 		}
 		if(requiProp==1) // put the card to change
 			requiProp=2;
 		else
 			requiProp=1;
 		choice = currentStrategy.chooseCardToSwap(this.getOwnedProps().get(requiProp-1), toPerform.getRequi(requiTrick), otherPlayers.get(0), otherPlayers.get(1) /*, seenImportantCards */);
 		this.swapCard(requiProp, otherPlayers.get((choice-1)/2), (choice-1)%2+1); // get the corresponding players'cards
    }
 	/**
 	 * Allows the AI to rearrange the cards after playing a trick
 	 * @param prop7 A reference to the top of the unowned props
 	 * @return the prop he leaves
 	 */
    public Prop rearrangeHand(Prop prop7) {
    	ArrayList<Prop> listProp = new ArrayList<Prop>();
    	listProp.addAll(getOwnedProps());
    	listProp.add(prop7);
    	ArrayList<Prop> choice = currentStrategy.chooseRearrangeHand(listProp); // chooses the card to discard and place it at the end
    	this.setOwnedProps(0, choice.get(0));
    	this.setOwnedProps(1, choice.get(1));
    	return choice.get(2);
    	}
    /**
     * Allows the AI to choose which card to change visibility
     * 
     */
    public void chooseChangeVisibility() {
    	Iterator<Prop> oneVisible = super.getOwnedProps().listIterator();
    	int cardVisible=0, counter=0, propsUp=0;
    	while(oneVisible.hasNext()) { // checks if his cards are visible
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
    	else if(propsUp!=3) { // if none of them are visible, he chooses according to Strategy
        	int choice = currentStrategy.chooseChangeVisibility(getOwnedProps());
			this.forfeit(choice);
    	}
    	else
    		this.forfeit(propsUp);
    	
    }
}
