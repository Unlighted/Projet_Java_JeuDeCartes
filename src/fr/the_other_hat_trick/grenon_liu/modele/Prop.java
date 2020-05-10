package fr.the_other_hat_trick.grenon_liu.modele;
/**
 * The different props of the game
 * @author Grenon Maxence Liu Runkai
 *
 */
public class Prop extends Card {
    private boolean visible;

    private boolean isImportant;
    /**
     * The constructor of the Props
     * @param name The name of the prop
     * @param isImportant States if the card is important
     */
    public Prop(String name, boolean isImportant) {
    	super(name);
    	this.visible = false;
    	this.isImportant = isImportant;
    }
    /**
     * toggles the visibility of the card
     */
    public void changeVisibility() {
    	this.visible = !this.visible;
    }
    /**
     * set the card to visible
     */
    public void putVisible() {
    	this.visible = true;
    }
    
    public boolean getVisibility() {
    	return this.visible;
    }
    public boolean getIsImportant() {
    	return this.isImportant;
    }
    public String toString() {
    	String s;
    	if (this.visible==true) {
    		s=this.getName();
    	}else { // the card is not visible then not shown
    		s="unknownCard";
    	}
    	return s;
    }
    
    /**
     * set the card to the specified visibility
     * @param v The visibility wanted
     */
    public void setVisible(boolean v) {
    	this.visible=v;
    }
}
