package fr.the_other_hat_trick.grenon_liu.modele;
import java.util.*;
/**
 * The trickpile of the game
 * @author Grenon Maxence Liu Runkai
 * 
 */
public class TrickPile {
    private int tricksLeftUp;

    private int tricksLeftDown;
    
    private LinkedList<Trick> tricksUp;
    
    private LinkedList<Trick> tricksDown;
    
    /**
     * The constructor of the trickpile
     * @param setRules Rules in place
     */
    public TrickPile(HashSet<Integer> setRules) {
    	tricksUp = new LinkedList<>();
    	tricksDown = new LinkedList<>();
    	this.tricksLeftUp=0;
    	tricksDown.add(new Trick("The Hungry Rabbit", 1, "Carrot", "Lettuce", "Rabbit", "Other Rabbit")); // all the basic cards
    	tricksDown.add(new Trick("The Bunch Of Carrots", 2, "Carrot", "Carrot", "Carrot", "Carrot"));
    	tricksDown.add(new Trick("The Vegetable Patch", 3, "Carrot", "Carrot", "Lettuce", "Lettuce"));
    	tricksDown.add(new Trick("The Rabbit That Didn't Like Carrots", 4, "Rabbit", "Other Rabbit", "Lettuce", "Lettuce"));
    	tricksDown.add(new Trick("The Pair Of Rabbits", 5, "Rabbit", "Rabbit", "Other Rabbit", "Other Rabbit"));
    	tricksDown.add(new Trick("The Vegetable Hat Trick", 2, "Hat", "Hat", "Carrot", "Lettuce"));
    	tricksDown.add(new Trick("The Carrot Hat Trick", 3, "Hat", "Hat", "Carrot", "Carrot"));
    	tricksDown.add(new Trick("The Slightly Easier Hat Trick", 4, "Hat", "Hat", "Rabbit", "Other Rabbit"));
    	tricksDown.add(new Trick("The Hat Trick", 5, "Hat", "Hat", "Rabbit", "Rabbit"));
    	
    	this.tricksLeftDown = 10;
    	
    	this.addTricks(setRules); // we add cards according to the set rules. Works because done only once
    	
    	Collections.shuffle(tricksDown);

    	tricksDown.addLast(new Trick("The Other Hat Trick", 6+3+3, "Hat", "Hat", "Other Rabbit", "Other Rabbit"));// we make sure the other hat trick is at the bottom
    	
    	this.turnTrick();
    	
    }
    /**
     * Adds the tricks depending of the rules
     * @param setRules The rules in place
     */
    public void addTricks(HashSet<Integer> setRules) {
    	Iterator<Integer> itRules = setRules.iterator();
    	while(itRules.hasNext()) {
    		if(itRules.next().intValue()==1) {
        		tricksDown.add(new Trick("The Flagged Hat", 5, "Flag", "Flag", "Hat", "Hat"));
        		tricksDown.add(new Trick("The Rabbit Holding A Flag", 4, "Flag", "Flag", "Rabbit", "Other Rabbit"));
        		tricksDown.add(new Trick("The Spotted Meal", 2, "Flag", "Flag", "Carrot", "Lettuce"));
            	
            	this.tricksLeftDown += 3;
    		}
        	
    	}
    	itRules = setRules.iterator();
    	while(itRules.hasNext()) {
    		if(itRules.next().intValue()==2) {
        		tricksDown.add(new Trick("The Chocolate Rabbit", 3, "Sweet", "Sweet", "Rabbit", "Other Rabbit"));
        		tricksDown.add(new Trick("The Halloween Preparations", 2, "Sweet", "Sweet", "Sweet", "Sweet"));
        		tricksDown.add(new Trick("The Lucky Rabbit", 4, "Sweet", "Sweet", "Rabbit", "Rabbit"));
        		tricksDown.add(new Trick("The Sweet Hat Trick", 4, "Sweet", "Sweet", "Hat", "Hat"));
        	
        		this.tricksLeftDown += 4;
    		}
        	
    	}
    	
    }
    /**
     * Turn a trick on the trickpile
     */
    public void turnTrick() {
    	if(tricksLeftDown!=0) {
    		System.out.println("Trick Turned !!!"); // flips a card
    		this.tricksUp.addFirst(tricksDown.poll());
    		this.tricksLeftDown--;
    		this.tricksLeftUp++;
    	}
    }
    /**
     * Removing a card from the trickpile
     * @return The card removed
     */
    public Trick performedTrick() {
    	this.tricksLeftUp--;
    	return this.tricksUp.poll();
    }
    
    public int getTricksLeftUp() {
    	return this.tricksLeftUp;
    }
    
    public int getTricksLeftDown() {
    	return this.tricksLeftDown;
    }
    /**
     * Show the card at the top
     * @return The card at the top
     */
    public Trick showTrick() {
    	return tricksUp.get((0));
    }
    public Trick showTrickDown() {
    	return this.tricksDown.get(0);
    }
}
