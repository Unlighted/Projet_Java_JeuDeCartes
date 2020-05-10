package fr.the_other_hat_trick.grenon_liu.modele;
import java.util.*;

/**
 * The strategy of the AI in case he's losing
 * @author Grenon Maxence Liu Runkai
 * 
 */
public class StrategyWinning implements Strategy {
	/**
	 * Chooses the card to swap
	 * @param exchangeProp The prop to exchange
	 * @param neededProp The list of the props to exchange
	 * @param otherPlayer1 The first other player
	 * @param otherPlayer2 The second other player
	 * @return The result of the choice
	 */
    public int chooseCardToSwap(Prop exchangeProp, ArrayList<String> neededProp, Player otherPlayer1, Player otherPlayer2/* , ArrayList<Prop> seenImportantCards */) {
    	
    	ArrayList<Prop> allProps = new ArrayList<>();
    	allProps.addAll(otherPlayer1.getOwnedProps());
    	allProps.addAll(otherPlayer2.getOwnedProps());
    	Iterator<Prop> itAllProps = allProps.iterator();
    	Iterator<String> itNeededProps;
    	int counter=0, choice=0;
    	Prop buffer;
    	while(itAllProps.hasNext() && choice != 0) {
    		counter++;
    		buffer = itAllProps.next();
    		itNeededProps = neededProp.iterator();
    		while(itNeededProps.hasNext() && choice != 0) { // checks if he sees the card that he needs
    			if(buffer.getVisibility() == true && buffer.getName().equals(itNeededProps.next())) {
    				choice=counter;
    			}
    		}
    	}
    	if(choice != 0)
    		return choice;
    	Random r = new Random();
    	counter=0;
    	itAllProps = allProps.iterator();
    	while(itAllProps.hasNext()) 
    		if(itAllProps.next().getVisibility() == true)
    			counter++;
    	if(counter == 4) // if no one has it and everything is seen choose a card at random
    		return r.nextInt(4)+1;
    	while(counter<100) { // returns an unseen card
    		choice = r.nextInt(4) + 1;
    		if(allProps.get(choice-1).getVisibility() == false)
    			return choice;
    		counter++;
        }
    	return choice;
    }
    /**
     * Chooses the trick to turn
     * @param trickPile A reference to the trickpile
     * @param ownedProps The owned props of the AI
     * @return The choice made
     */
    public boolean chooseTurnTrick(TrickPile trickPile, ArrayList<Prop> ownedProps) {
    	ArrayList<Prop> ownedPropsBis = new ArrayList<>();
    	ownedPropsBis.addAll(ownedProps);
    	Iterator<Prop> seeAll = ownedPropsBis.listIterator();
    	int compteur = 0, ca = 0, cb = 0;
    	Prop buffer1, buffer2;
    	while(seeAll.hasNext()&& ca == 0) {// checks how many cards are already owned
    		buffer1 = seeAll.next();
    		if(trickPile.showTrick().containsProp(buffer1, 0)) {
    			ca++;
    			ownedPropsBis.remove(buffer1);
    		}
    	}
    	seeAll = ownedProps.iterator();
    	while(seeAll.hasNext()&& cb == 0) {
    		buffer2 = seeAll.next();
    		if(trickPile.showTrick().containsProp(buffer2, 1))
    			cb++;
    	}
    	compteur = ca+cb;
    	if(compteur == 0 || compteur == 2 || trickPile.showTrick().getPoints()<3) // plays greedy
    		return true;
    	return false;
    }
    /**
     * chooses the trick to perform
     * @return The choice made
     */
    public boolean choosePerformTrick() {
    	return true;
    }
    /**
     * Chooses how to rearrange the hand
     * @param listProp the list of the props
     * @return The list with the right order
     */
    public ArrayList<Prop> chooseRearrangeHand(ArrayList<Prop> listProp) {
    	Iterator<Prop> seeAll = listProp.listIterator();
    	Prop buffer;
    	ArrayList<Prop> newListKeep = new ArrayList<>();
    	Prop newOut = null;
    	while(seeAll.hasNext()) { // discard the most important card
    		buffer = seeAll.next();
    		if(buffer.getIsImportant()&&newOut==null)
    			newOut = buffer;
    		else
    			newListKeep.add(buffer);
    	}
    	Collections.shuffle(newListKeep);
    	newListKeep.add(newOut);
    	return newListKeep;
    }
    

    /**
     * Chooses which card to reveal
     * @param ownedProps the props owned
     * @return the choice made
     */
    public int chooseChangeVisibility(ArrayList<Prop> ownedProps) {
    	Iterator<Prop> seeOwnedProps = ownedProps.listIterator();
    	int counter=1, result=1;
    	while(seeOwnedProps.hasNext()) { // returns an unimportant card
    		if(seeOwnedProps.next().getIsImportant()==false)
    			result = counter;
    		counter++;
    	}
    	return result;
    }
}
