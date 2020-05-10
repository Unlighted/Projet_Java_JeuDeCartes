package fr.the_other_hat_trick.grenon_liu.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import fr.the_other_hat_trick.grenon_liu.modele.Game;

/**
 * A controller to check if the player can perform
 * @author Grenon
 * @author Runkai
 *
 */
public class ControleurPerform {
	/**
	 * The constructor of the controller
	 * @param perform The button perform pressed
	 * @param keep The button keep to disable
	 * @param forfeit The button forfeit to disable
	 * @param lbl The label to modify its content
	 * @param currentgame A reference to the game
	 */
	public ControleurPerform(JButton perform, JButton keep, JButton forfeit, JLabel lbl, Game currentgame) {
		perform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				perform.setVisible(false);
				forfeit.setVisible(false);
				keep.setVisible(true);
				currentgame.getPlayer().get(currentgame.getTurn()-1).performTrick(currentgame.getTrickPile());
				lbl.setText("Choose 2 props to keep");
				currentgame.setEtat(4); // set the last step of the turn
				currentgame.getUnOwnedProps().get(0).setVisible(true);
				if(currentgame.getTrickPile().getTricksLeftUp()==0) {
	    			currentgame.getTrickPile().turnTrick();
	    		}
				currentgame.refresh();
			}
		});
	}
}
