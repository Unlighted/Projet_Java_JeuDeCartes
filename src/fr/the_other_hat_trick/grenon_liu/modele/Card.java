package fr.the_other_hat_trick.grenon_liu.modele;

/**
 * The general Card of the game
 * @author Grenon Maxence Liu Runkai
 *
 */
abstract public class Card {
    private String name;
    
    public Card(String name) {
    	this.name=name;
    }

    public void setName(String name) {
    	this.name=name;
    }
    
    public String getName() {
    	return this.name;
    }
    /**
     * A surcharge of equals
     * @param equiTest the card to check the equality with
     * @return the result of the comparison
     */
    public boolean equals(Card equiTest) { // checks equality by the card
    	if(this.name==equiTest.name) {
    		return true;
    	}
    return false;
    }
    /**
     * Another surcharge of equals
     * @param name The name of the card to check
     * @return the result of comparison
     */
    public boolean equals(String name) { // checks equality by name
    	if(this.name==name) {
    		return true;
        }
    return false;
    }
   
    }
