package fr.the_other_hat_trick.grenon_liu.modele;

import java.util.ArrayList;

/**
 * The strategy of the AI
 * @author Grenon Maxence Liu Runkai
 * 
 */
public interface Strategy {
	/**
	 * Chooses the card to swap
	 * @param exchangeProp The prop to exchange
	 * @param neededProp The list of the props to exchange
	 * @param otherPlayer1 The first other player
	 * @param otherPlayer2 The second other player
	 * @return The result of the choice
	 */
    public int chooseCardToSwap(Prop exchangeProp, ArrayList<String> neededProp, Player otherPlayer1, Player otherPlayer2/* , ArrayList<Prop> seenImportantCards */);

    /**
     * Chooses the trick to turn
     * @param trickPile A reference to the trickpile
     * @param ownedProps The owned props of the AI
     * @return The choice made
     */
    public boolean chooseTurnTrick(TrickPile trickPile, ArrayList<Prop> ownedProps);

    /**
     * chooses the trick to perform
     * @return The choice made
     */
    public boolean choosePerformTrick();

    /**
     * Chooses how to rearrange the hand
     * @param listProp the list of the props
     * @return The list with the right order
     */
    public ArrayList<Prop> chooseRearrangeHand(ArrayList<Prop> listProp);

    /**
     * Chooses which card to reveal
     * @param ownedProps the props owned
     * @return the choice made
     */
    public int chooseChangeVisibility(ArrayList<Prop> ownedProps);

}
