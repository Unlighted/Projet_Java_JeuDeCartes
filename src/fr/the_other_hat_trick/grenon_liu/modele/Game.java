package fr.the_other_hat_trick.grenon_liu.modele;
import java.util.*;

import com.sun.glass.ui.TouchInputSupport;

/**
 * The game and all of its components
 * @author Grenon
 * @author Runkai
 *
 */
public class Game extends Observable{
    private int turn;
    
    private int totalTurn;

    private int realPlayers;

    private HashSet<Integer> setRules;
    
    private ArrayList<Player> players;
    
    private LinkedList<Prop> unOwnedProps;
    
    private Scanner consoleEntryInt;
    
    private Scanner consoleEntryString;
    
    private TrickPile trickPile;
    
    private int lastTurn=0;
    
    private int etat;
    
    
    public ArrayList<Player> getPlayer(){
    	return this.getPlayers();
    }
    
    public int getEtat() {
    	return this.etat;
    }
    
    public void setEtat(int a) {
    	this.etat=a;
    }
    

    /**
     * The constructor of the game itself
     */
    public Game() {
    	this.setTurn(1);//initialize all
    	this.setTotalTurn(1);
    	this.etat=0;
    	consoleEntryInt = new Scanner(System.in);
    	consoleEntryString = new Scanner(System.in);
    	setSetRules(new HashSet<Integer>());
    	setPlayers(new ArrayList<>());
    	setUnOwnedProps(new LinkedList<>());
    }

    /**
     * A console input of an integer
     * @param print The message to show
     * @param valmin The minimal value
     * @param valmax The maximal value
     * @return The chosen value
     */
    public int consoleInput(String print, int valmin, int valmax) {
    	int checker=valmax+1;
    	while(checker<valmin || checker>valmax ) {// asks for value as long as the entry is wrong
    		System.out.println(print);
    		try {
    			checker = consoleEntryInt.nextInt();
    		}
    		catch(Exception e){ // we consider all exceptions can be solved by giving a new value
    			checker = -1;
    		}
    	}
    	return checker;
    }
   
    
    
    public String consoleInput(String print) {
    	System.out.println(print);
    	return consoleEntryString.nextLine();
    	}
    

    /**
     * refreshes the graphical interface
     */
    public void refresh() {
    	this.setChanged();
    	this.notifyObservers();
    }
 

    /**
     * Starts the game in console mode
     */
	public void initializeGame() {
		int playerCount, playerCountBis;
		int age, countRules=1;
		String name = new String();
		System.out.println("Rules 1 : Flag Party, Rules 2 : Magician of Sweets, Rules 3 : Risk Mode");
		while(countRules!=0)
			setRules.add(countRules = consoleInput("Give the rules to apply, then enter 0 to continue", 0, 3));
		this.realPlayers = consoleInput("Give the number of real players ", 0, 3);// Get all the player's info
		for(playerCount = 1; playerCount <= this.realPlayers; playerCount++) {
			age = consoleInput("Give the age of the player "+playerCount, 1, 99);
			name = consoleInput("Now give the name of the player "+playerCount);
			this.players.add(new HumanPlayer(name, age));
			
		}
		while(playerCount<=3) {//fills the rest with AI
			this.players.add(new AI());
			playerCount++;
		}
		trickPile = new TrickPile(setRules);
		
		this.unOwnedProps.add(new Prop("Carrot", false));
		this.unOwnedProps.add(new Prop("Carrot", false));
		this.unOwnedProps.add(new Prop("Carrot", false));
		this.unOwnedProps.add(new Prop("Hat", true));
		this.unOwnedProps.add(new Prop("Rabbit", true));
		this.unOwnedProps.add(new Prop("Other Rabbit", true));
		this.unOwnedProps.add(new Prop("Lettuce", false));
		
		addProps(); // Special rules
		
		
		Collections.shuffle(unOwnedProps);
		for(playerCount=1; playerCount<=3; playerCount++) {
			this.players.get(playerCount-1).getStartingCards(unOwnedProps.poll(), unOwnedProps.poll());// distribute cards to players
		}
	
		for(playerCount=2; playerCount>0; playerCount--) {
			for(playerCountBis=0; playerCountBis<playerCount; playerCountBis++) { // reorder players
				if(this.players.get(playerCountBis).getAge()>this.players.get(playerCountBis+1).getAge())
					Collections.swap(this.players,  playerCountBis,  playerCountBis+1);
			}
		}
		
		System.out.println("Game successfully generated!!!");
	}


	/**
	 * 
	 * @param number The number of players in the game
	 * @param name1 The name of the first player
	 * @param age1 The age of the first player
	 * @param name2 The name of the second player
	 * @param age2 The age of the second player
	 * @param name3 The name of the third player
	 * @param age3 The age of the third player
	 */
	public void initializeGame(int number, String name1, int age1, String name2, int age2, String name3, int age3) {
		int playerCount=1, playerCountBis;
		
		
			setRules.add(0);
		this.realPlayers = number;
		
		switch (number) {
		case 1: //creates players
			this.players.add(new HumanPlayer(name1, age1));
			playerCount++;
			break;
		case 2:
			this.players.add(new HumanPlayer(name1, age1));
			this.players.add(new HumanPlayer(name2, age2));
			playerCount=playerCount+2;
			break;
		case 3:
			this.players.add(new HumanPlayer(name1, age1));
			this.players.add(new HumanPlayer(name2, age2));
			this.players.add(new HumanPlayer(name3, age3));
			playerCount=playerCount+3;
			break;
		default:
			break;
		}
		while(playerCount<=3) {// fills the rest with AI
			this.players.add(new AI());
			playerCount++;
		}
		trickPile = new TrickPile(setRules);
		
		this.unOwnedProps.add(new Prop("Carrot", false));
		this.unOwnedProps.add(new Prop("Carrot", false));
		this.unOwnedProps.add(new Prop("Carrot", false));
		this.unOwnedProps.add(new Prop("Hat", true));
		this.unOwnedProps.add(new Prop("Rabbit", true));
		this.unOwnedProps.add(new Prop("Other Rabbit", true));
		this.unOwnedProps.add(new Prop("Lettuce", false));
		
		addProps(); // other rules (not in place)
		
		
		Collections.shuffle(unOwnedProps);
		for(playerCount=1; playerCount<=3; playerCount++) {
			this.players.get(playerCount-1).getStartingCards(unOwnedProps.poll(), unOwnedProps.poll()); // distribute cards
		}
	
		for(playerCount=2; playerCount>0; playerCount--) { // reorder the players
			for(playerCountBis=0; playerCountBis<playerCount; playerCountBis++) {
				if(this.players.get(playerCountBis).getAge()>this.players.get(playerCountBis+1).getAge())
					Collections.swap(this.players,  playerCountBis,  playerCountBis+1);
			}
		}
		
		System.out.println("Game successfully generated!!!");
	}

    /**
     * Change the unowned props when performed a trick
     * @param d1 The first chosen card
     * @param d2 The second chosen card
     */
    public void changeUnownedProps(int d1, int d2) {
    	this.getUnOwnedProps().add(((HumanPlayer)(this.getPlayers().get(this.getTurn()-1))).rearrangeHand(this.getUnOwnedProps().poll(),d1,d2));
    }
    
    
    /**
     * Change the unowned props when performed a trick (console gameplay)
     */
   public void changeUnownedProps() {
    	this.getUnOwnedProps().add(this.getPlayers().get(this.getTurn()-1).rearrangeHand(this.getUnOwnedProps().poll()));
    }
   
   /**
    * Permet de terminer la partie avec l'interface
    * @return Les joueurs dans le bon ordre
    */
   public ArrayList<Player> endGameInterface(){
	
   	Iterator<Player> endPlayers = getPlayers().iterator();
   	ArrayList<Integer> ranks = new ArrayList<>();
   	Player bufferPlayer;
   
   	int playerCount, playerCountBis;
   	while(endPlayers.hasNext()) {
		bufferPlayer = endPlayers.next();
		ranks.add(bufferPlayer.lastTurn(this.getTrickPile()));// give the ranks
		
    	}
		for(playerCount=2; playerCount>0; playerCount--) {// reorder the players
			for(playerCountBis=0; playerCountBis<playerCount; playerCountBis++) {
				if(ranks.get(playerCountBis).intValue()<ranks.get(playerCountBis+1).intValue())
					Collections.swap(getPlayer(),  playerCountBis,  playerCountBis+1);
					Collections.swap(ranks,  playerCountBis,  playerCountBis+1);
			}
		}
   	endPlayers = getPlayer().iterator();
   	ArrayList<Player> playerw=new ArrayList<>();
   	while(endPlayers.hasNext()) {
   		playerw.add(endPlayers.next()); // the right order
   		
   	}
   	return playerw;
   }
   
   /**
    * The end of the game and all of its calculations
    */
    public void endGame() {
    	this.setChanged();
    	this.notifyObservers();
    	Iterator<Player> endPlayers = getPlayers().iterator();
    	ArrayList<Integer> ranks = new ArrayList<>();
    	Player bufferPlayer;
    	int counter = 1;
    	int playerCount, playerCountBis;
    	while(endPlayers.hasNext()) {
    		bufferPlayer = endPlayers.next();
    		ranks.add(bufferPlayer.lastTurn(this.getTrickPile())); // ranks of the players
        	}
		for(playerCount=2; playerCount>0; playerCount--) { // reorder the player
			for(playerCountBis=0; playerCountBis<playerCount; playerCountBis++) {
				if(ranks.get(playerCountBis).intValue()<ranks.get(playerCountBis+1).intValue())
					Collections.swap(this.getPlayers(),  playerCountBis,  playerCountBis+1);
					Collections.swap(ranks,  playerCountBis,  playerCountBis+1);
			}
		}
    	endPlayers = getPlayers().iterator();
    	while(endPlayers.hasNext()) {
    		System.out.println("The rank " + counter + " is given to " + endPlayers.next().getName());
    		counter++;
    	}
    }

    /**
     * Adds the props to the game depending of the rules set
     */
    private void addProps() {
    	Iterator<Integer> itRules = this.getSetRules().iterator();
    	while(itRules.hasNext()) {
    		if(itRules.next().intValue()==1) {
        		this.getUnOwnedProps().add(new Prop("Flag", true));
    		}
    	}
    	itRules = this.getSetRules().iterator();
    	while(itRules.hasNext()) {
    		if(itRules.next().intValue()==2) {
    			this.getUnOwnedProps().add(new Prop("Sweet", false));
    			this.getUnOwnedProps().add(new Prop("Sweet", false));
    		}
    	}
    	// depends on rules
    }

    public void disconnectPlayer() {
    }


    public void changeRules() {
    }
    
    /**
     * Allows the IA to play if effectively an IA
     */
    public void continuerAI() {
    	boolean performed=false;
    	Iterator<Player> AIStrategy;
    	Player testAI;
    	this.setChanged();
    	this.notifyObservers(); // reload the interface
    	while((this.getTrickPile().getTricksLeftDown()!=0 || (this.getLastTurn() != 3 && this.getTrickPile().showTrick().getName()=="The Other Hat Trick"))&&(this.getPlayer().get(this.getTurn()-1) instanceof AI)) {
    		System.out.println("Turn " + this.getTotalTurn());
    		this.getPlayers().get(this.getTurn()-1).decideTrick(this.getTrickPile(), this.getSetRules()); // plays the game normally
    		this.getPlayers().get(this.getTurn()-1).chooseSwap(this.getPlayer(), this.getTrickPile());
    		performed=this.getPlayers().get(this.getTurn()-1).decidePerform(this.getTrickPile());
    		if(performed) { // tests if the AI need to change their strategy
    			this.changeUnownedProps();
    			AIStrategy = this.getPlayers().listIterator();
    			while(AIStrategy.hasNext()) {
    				testAI = AIStrategy.next();
    				if(testAI instanceof AI)
    					((AI) testAI).changeStrategy(this.getPlayers());
    			}
    		}
    		
    		if(this.getTrickPile().getTricksLeftUp()==0) {
    			this.getTrickPile().turnTrick();
    		}
    		
    		if(this.getTrickPile().getTricksLeftDown()==0) {
				this.setLastTurn(this.getLastTurn() + 1);// counter for the last turn
    		}
    		this.setTurn(this.getTurn() + 1);
    		this.setTotalTurn(this.getTotalTurn() + 1);
    		if(this.getTurn()>3) {
    			this.setTurn(1);
    		}
    		this.setChanged();
    		this.notifyObservers();
    	}
    	
    }
    /**
     * The main for the console game
     * @param args args given with console at launch
     */
    public static void main(String[] args) {
    	
    	Game currentGame = new Game();
    	currentGame.initializeGame();
    	boolean performed=false;
    	int lastTurn = 0;
    	Iterator<Player> AIStrategy;
    	Player testAI;
    	currentGame.setChanged(); // still upload the interface
    	currentGame.notifyObservers();
    	
    	while(currentGame.getTrickPile().getTricksLeftDown()!=0 || (lastTurn != 3 && currentGame.getTrickPile().showTrick().getName()=="The Other Hat Trick")) {
    		System.out.println("Turn " + currentGame.getTotalTurn());
    		currentGame.getPlayers().get(currentGame.getTurn()-1).startTurn();// the order of the functions
    		currentGame.getPlayers().get(currentGame.getTurn()-1).decideTrick(currentGame.getTrickPile(), currentGame.getSetRules());
    		currentGame.getPlayers().get(currentGame.getTurn()-1).chooseSwap(currentGame.getPlayer(), currentGame.getTrickPile());
    		performed=currentGame.getPlayers().get(currentGame.getTurn()-1).decidePerform(currentGame.getTrickPile());
    		if(performed) { // change all IA if needed
    			currentGame.changeUnownedProps(1, 2);
    			AIStrategy = currentGame.getPlayers().listIterator();
    			while(AIStrategy.hasNext()) {
    				testAI = AIStrategy.next();
    				if(testAI instanceof AI)
    					((AI) testAI).changeStrategy(currentGame.getPlayers());
    			}
    		}
			System.out.println("There are " + currentGame.getTrickPile().getTricksLeftDown()+ " Tricks left down !");
    		
    		if(currentGame.getTrickPile().getTricksLeftUp()==0) { // at least a card up
    			currentGame.getTrickPile().turnTrick();
    		}
    		
    		if(currentGame.getTrickPile().getTricksLeftDown()==0) { // start of the counter
    			System.out.println("Final Turn !!! The current Trick is " + currentGame.getTrickPile().showTrick());
				lastTurn++;
    		}
    		currentGame.setTurn(currentGame.getTurn() + 1);
    		currentGame.setTotalTurn(currentGame.getTotalTurn() + 1);
    		if(currentGame.getTurn()>3) {
    			currentGame.setTurn(1);
    		}
    	}
    	currentGame.endGame();
    	
    }

	public TrickPile getTrickPile() {
		return trickPile;
	}

	public void setTrickPile(TrickPile trickPile) {
		this.trickPile = trickPile;
	}

	public int getTotalTurn() {
		return totalTurn;
	}

	public void setTotalTurn(int totalTurn) {
		this.totalTurn = totalTurn;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public HashSet<Integer> getSetRules() {
		return setRules;
	}

	public void setSetRules(HashSet<Integer> setRules) {
		this.setRules = setRules;
	}

	public LinkedList<Prop> getUnOwnedProps() {
		return unOwnedProps;
	}

	public void setUnOwnedProps(LinkedList<Prop> unOwnedProps) {
		this.unOwnedProps = unOwnedProps;
	}

	public int getLastTurn() {
		return lastTurn;
	}

	public void setLastTurn(int lastTurn) {
		this.lastTurn = lastTurn;
	}
}
