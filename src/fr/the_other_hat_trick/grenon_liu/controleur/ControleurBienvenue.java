package fr.the_other_hat_trick.grenon_liu.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import fr.the_other_hat_trick.grenon_liu.vue.InterfaceGraphique;
import fr.the_other_hat_trick.grenon_liu.modele.Game;
/**
 * Controler of the setup window
 * @author Grenon
 * @author Runkai
 *
 */
public class ControleurBienvenue {
	/**
	 * The constructor of the controler, with all that he watches
	 * @param start The start button
	 * @param name The name of first player
	 * @param age The age of the first player
	 * @param f1 The frame of the interface
	 * @param f2 The interface itself
	 * @param g a reference to the game
	 * @param name2 The name of the second player
	 * @param age2 The age of the Second player
	 * @param name3 The name of the third player
	 * @param age3 The age of the third player
	 * @param number The number of players
	 */
public ControleurBienvenue(JButton start, JTextField name, JTextField age, JFrame f1, InterfaceGraphique f2, Game g,JTextField name2, JTextField age2, JTextField name3, JTextField age3, JTextField number) {
	start.addActionListener(new ActionListener() {
		/**
		 * Notices a click of a button to start the game
		 * @param e The click
		 */
		public void actionPerformed(ActionEvent e) {// starts the setup of the game
			g.initializeGame(Integer.valueOf(number.getText()), name.getText(), Integer.valueOf(age.getText()),name2.getText(), Integer.valueOf(age2.getText()), name3.getText(), Integer.valueOf(age3.getText()));
			g.setEtat(1);
			start.setVisible(false); // starts the game by showing the right buttons
			name.setVisible(false);
			age.setVisible(false);
			f2.getTurn().setVisible(true);
			f2.getNotturn().setVisible(true);
			f2.getLblAllThingsGo().setText("Now you choose to turn the trick or not");
			f1.setVisible(false);
			f2.getFrame().setVisible(true);
			g.continuerAI();
			g.refresh();
		}
	});
}
}
