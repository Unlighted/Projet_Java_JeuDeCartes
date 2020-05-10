package fr.the_other_hat_trick.grenon_liu.modele;
import java.util.*;
/**
 * The Player in general in the game
 * @author Grenon Maxence Liu Runkai
 *
 */
abstract public class Player {
    private int age;

    private String name;

    private int lostPoints;
    
    private int totalPoints;
    
    private ArrayList<Prop> ownedProps;
    
    private ArrayList<Trick> hasWon;
    
    private Iterator<Trick> allTrick;
    
    private Scanner consoleEntryInt;
    
    private Scanner consoleEntryString;
    
    public ArrayList<Prop> getOwnedProps(){
    	return this.ownedProps;
    }
    
    public void setOwnedProps(int index,Prop prop) {
    	this.ownedProps.set(index, prop);
    }
    
    /**
     * A console input of a value
     * @param print The text to show
     * @param valmin The minimum value
     * @param valmax The maximum value
     * @return The value obtained
     */
    public int consoleInput(String print, int valmin, int valmax) {
    	int checker=valmax+1;
    	while(checker<valmin || checker>valmax ) {
    		System.out.println(print);
    		checker = consoleEntryInt.nextInt();
    	}
    	return checker;
    }
    
    /** 
     * A console input of a String
     * @param print The text to show
     * @return The string obtained
     */
    public String consoleInput(String print) {
    	System.out.println(print);
    	return consoleEntryString.nextLine();
    	}
    
    /**
     * The constructor of a player
     * @param name The name of the player
     * @param age The given age
     */
    public Player(String name, int age) {
    	this.name = name;
    	this.age = age;
    	this.lostPoints = 0;
    	this.totalPoints = 0;
    	this.ownedProps = new ArrayList<>();
    	this.hasWon = new ArrayList<>();
    	this.allTrick = hasWon.iterator();
    	consoleEntryInt = new Scanner(System.in);
    	consoleEntryString = new Scanner(System.in);
    }
    
  
    public String getName() {
    	return this.name;
    }
    /**
     * give the starting cards to the player
     * @param prop1 The first card given
     * @param prop2 The second card given
     */
    public void getStartingCards(Prop prop1, Prop prop2) {
    	this.ownedProps.add(prop1);
    	this.ownedProps.add(prop2);
    }
    
    public int getAge() {
    	return this.age;
    }
    
    public int getTotalPoints() {
    	return this.totalPoints;
    }
    /**
     * The result of the player forfeiting
     * @param chosenProp The chosen prop to give up its visibility
     */
    public void forfeit(int chosenProp) {
    	System.out.println(this.name + " forfeit.");
    	if(chosenProp!=3) {
    		this.ownedProps.get(chosenProp-1).changeVisibility();
        	System.out.println(" He chose his prop " + chosenProp);
    		
    	}
    }
    /**
     * The result of the player deciding to perform
     * @param trp A reference to the trickpile
     */
    public void performTrick(TrickPile trp) {
    	Iterator<Prop> visibleUp = ownedProps.iterator();
    	
    	Trick newTrick = trp.performedTrick();
    	this.hasWon.add(newTrick);   	
    	this.totalPoints += newTrick.getPoints();
    	while(visibleUp.hasNext()) { //shows that he won
    		Prop newprop=visibleUp.next();
    		newprop.putVisible();
    		System.out.println("Look, he has "+newprop);
    	}
    	visibleUp = ownedProps.iterator(); // hides the cards again
    	while(visibleUp.hasNext()) {
    		visibleUp.next().changeVisibility();
    	}
    	System.out.println("He has successfully performed "+newTrick);
    
    	
    }
    /**
     * Swaps the card with other players
     * @param propGiven The prop given up
     * @param otherPlayer The other player
     * @param propExchanged The other player's card
     */
    public void swapCard(int propGiven, Player otherPlayer, int propExchanged) {
    	Prop tp=this.ownedProps.get(propGiven-1);
    	this.ownedProps.set(propGiven-1, otherPlayer.swapCard(tp, propExchanged));
    	System.out.println("You have used your prop "+propGiven+" to change with "+propExchanged+", prop of "+otherPlayer.getName());
    }
    /**
     * Swaps the card with an already chosen prop. Used after the other SwapCard
     * @see swapCard(int propGiven, Player otherPlayer, int propExchanged)
     * @param propGiven The prop given to this player
     * @param propExchanged The prop he has to give up
     * @return
     */
    public Prop swapCard(Prop propGiven, int propExchanged) {
    	Prop buffer = this.ownedProps.get(propExchanged-1);
    	ownedProps.set(propExchanged-1, propGiven);
    	return buffer; 	
    }
    /**
     * The computations of the end of the game
     * @param p A reference to the trickpile
     * @return The total points of the player
     */
    public int lastTurn(TrickPile p) {
    	if(this.ownedProps.get(0).getName()=="Hat" || this.ownedProps.get(1).getName()=="Hat") { // computes the losses due to owned cards
    		this.lostPoints+=3;
    	}
    	if(this.ownedProps.get(0).getName()=="Other Rabbit" || this.ownedProps.get(1).getName()=="Other Rabbit") {
    		this.lostPoints+=3;
    	}
    	this.totalPoints -= this.lostPoints;  
    	System.out.println(this.name + " has " + this.totalPoints + " points");
    	return this.totalPoints;
    }
    
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("This player is a");
    	if(this instanceof HumanPlayer)
    		sb.append(" Human Player ");
    	else
    		sb.append("n IA ");
    	sb.append("named ");
    	sb.append(name);
    	sb.append(". His props are ");
    	sb.append(ownedProps.get(0).getName());
    	sb.append(" and ");
    	sb.append(ownedProps.get(1).getName());
    	sb.append(". He won these tricks : ");
    	this.allTrick = hasWon.iterator();
    	while(allTrick.hasNext()) {
    		sb.append(allTrick.next().getName());
    		sb.append("  , ");
    	}
    	sb.append("This makes a total of ");
    	sb.append(this.totalPoints);
    	sb.append(" points.");
    	return sb.toString();
    }
    /**
     * prints the beginning of the turn for the player
     */
    public void startTurn() {
    	System.out.println("Start of the turn for "+this.name);
    	System.out.println(this.toString());
    	
    	}
    /**
     * Checks if the player is able to perform the trick
     * @param trp A reference to the trickpile
     * @return The result of the check
     */
    public boolean ableToPerform(TrickPile trp) {
    	boolean re=false;
    	Trick tr=trp.showTrick();
    	if (tr.match(this.ownedProps.get(0), this.ownedProps.get(1))==true) {
    		re=true;
    	}
    	System.out.println("You were not able to perform the Trick");
    	return re;
    }
    
    
    abstract public void decideTrick(TrickPile p, HashSet<Integer> setRules);
    abstract public void chooseSwap(ArrayList<Player> players, TrickPile trickPile);
    abstract public boolean decidePerform(TrickPile p);
    abstract public Prop rearrangeHand(Prop prop7);
    abstract public void chooseChangeVisibility();
}
