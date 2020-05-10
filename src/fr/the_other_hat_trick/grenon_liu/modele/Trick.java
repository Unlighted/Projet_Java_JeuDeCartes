package fr.the_other_hat_trick.grenon_liu.modele;
import java.util.*;
/**
 * The different tricks of the game
 * @author Grenon Maxence Liu Runkai
 *
 */
public class Trick extends Card {
    private int points;
    
    private ArrayList<String> requi1;
    
    private ArrayList<String> requi2;
    /**
     * The constructor of a trick
     * @param name The name of the trick
     * @param points The points it gives
     * @param requi1a The first card required
     * @param requi1b The alternative to the first card. If the same, put the same card
     * @param requi2a The second card required
     * @param requi2b The alternative to the second card.
     */
    public Trick(String name, int points, String requi1a, String requi1b, String requi2a, String requi2b) {
    	super(name);
    	this.points = points;
    	requi1 = new ArrayList<>();
    	requi2 = new ArrayList<>();
    	requi1.add(requi1a);
    	requi2.add(requi2a);
    	if (!requi1a.equals(requi1b)){ // add the other choice if there is one
    		requi1.add(requi1b);
    	}
    	if (!requi2a.equals(requi2b)){
    		requi2.add(requi2b);
    	}
    }
    /**
     * Returns the requirements of the card
     * @param which the slot of the card to get
     * @return the requirements to this slot
     */
    public ArrayList<String> getRequi(int which){
    	if(which == 1)
    		return requi1;
    	return requi2;
    }
    
    public void setPoints(int points) {
    	this.points = points;
    }
    
    public int getPoints() {
    	return this.points;
    }
    
    public String toString() {
    	return this.getName()+" "+requi1+" "+requi2;
    }
    /**
     * check if the props match the trick
     * @param p1 the first card
     * @param p2 the second card
     * @return The result of the check
     */
    public boolean match(Prop p1, Prop p2) {
    	boolean re=false; // if the props matches the trick
    	if((this.requi1.contains(p1.getName())&&this.requi2.contains(p2.getName()))||(this.requi1.contains(p2.getName())&&this.requi2.contains(p1.getName()))) {
    		re=true;
    	}
    	return re;
    }

    /**
     * Check if a slot contains a specific prop
     * @param prop the prop to check
     * @param list The slot of the prop
     * @return The result of the check
     */
    public boolean containsProp(Prop prop, int list) {
    	boolean compteur = false;
    	if (list == 0) {
    	Iterator<String> it1 = requi1.listIterator();
    	while(it1.hasNext())
    		if(it1.next()==prop.getName())
    			compteur = true;
    	}
    	else {
    		Iterator<String> it2 = requi2.listIterator();
    		while(it2.hasNext())
    			if(it2.next()==prop.getName())
    				compteur = true;
    	}
		return compteur;
    }
}
