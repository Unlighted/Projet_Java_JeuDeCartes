package fr.the_other_hat_trick.grenon_liu.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import fr.the_other_hat_trick.grenon_liu.modele.AI;
import fr.the_other_hat_trick.grenon_liu.modele.Game;
/**
 * The controller of the Turn buttons
 * @author Grenon
 * @author Runkai
 *
 */
public class ControleurTurn {
	/**
	 * The constructor of the controller
	 * @param g A reference to the game
	 * @param turn The button turn maybe pressed
	 * @param not The button Notturn maybe pressed
	 * @param change The button change to prepare for the next step
	 * @param lbl The label to modify its content
	 */
	public ControleurTurn(Game g, JButton turn, JButton not, JButton change, JLabel lbl) {
		turn.addActionListener(new ActionListener() {
			/**
			 * The listener to the button
			 * @param The event of click
			 */
			public void actionPerformed(ActionEvent e) {
				
				g.getTrickPile().turnTrick();
				g.setEtat(2); // Set the second step for after
				g.refresh();
				turn.setVisible(false);
				not.setVisible(false);
				if(g.getTrickPile().getTricksLeftDown()==0) {
					g.setLastTurn(g.getLastTurn() + 1);
	    		}
				change.setVisible(true);
				lbl.setText("Now you choose 1 of your props and 1 of others to change");
			}
		});
		not.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.setEtat(2); // also sets the second step
				g.refresh();
				turn.setVisible(false);
				not.setVisible(false);
				change.setVisible(true);
				lbl.setText("Now you choose 1 of your props and 1 of others to change");
				if(g.getTrickPile().getTricksLeftDown()==0) {// if there is no more cards
	    			System.out.println("Final Turn !!! The current Trick is " + g.getTrickPile().showTrick());
					g.setLastTurn(g.getLastTurn() + 1);
	    		}
			}
		});
		
		
	}
}
