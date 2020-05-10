package fr.the_other_hat_trick.grenon_liu.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import fr.the_other_hat_trick.grenon_liu.vue.Ilabel;
import fr.the_other_hat_trick.grenon_liu.modele.Game;
/***
 * Verifies the choices made after validation of the chosen cards
 * @author Grenon
 * @author Runkai
 *
 */
public class ControleurChange {
	/**
	 * The constructor of the controller, with all that he watches
	 * @param change The change button pressed
	 * @param lbl The label to change the content
	 * @param playerx A reference to all the cards
	 * @param currentgame A reference to the game
	 * @param perform A reference to the perform button to setup the next turn
	 * @param forfeit A reference to the forfeit button to setup the next turn
	 */
	public ControleurChange(JButton change, JLabel lbl, Ilabel[] playerx,Game currentgame, JButton perform, JButton forfeit) {
		change.addActionListener(new ActionListener() {
			/**
			 * Verifies the click of the button
			 * @param arg0 the click
			 */
			public void actionPerformed(ActionEvent arg0) {
				int count=0;
				ArrayList<Ilabel> choosed= new ArrayList<>();
				for(int i=0;i<playerx.length;i++) {
					if(playerx[i].getChoosed()) { // adds the players chosen
						choosed.add(playerx[i]);
						count++;
					}
				}
				for(int i=0;i<playerx.length;i++) {
					playerx[i].setChoosed(false); // reset the chosen cards
				}
				currentgame.refresh();// checks if the choices are right
				if(count==2&&choosed.get(0).getPlayer()!=choosed.get(1).getPlayer()&&(choosed.get(0).getPlayer()==(currentgame.getTurn()-1)||choosed.get(1).getPlayer()==(currentgame.getTurn()-1))&&choosed.get(0).getPlayer()<3&&choosed.get(1).getPlayer()<3) {
					currentgame.getPlayer().get(choosed.get(0).getPlayer()).swapCard(choosed.get(0).getNum(), currentgame.getPlayer().get(choosed.get(1).getPlayer()) ,choosed.get(1).getNum());
					currentgame.refresh();
					lbl.setText("All things go well");
					change.setVisible(false); // allows or not the performing
					if (currentgame.getPlayer().get(currentgame.getTurn()-1).ableToPerform(currentgame.getTrickPile())) {
						currentgame.setEtat(3);
						perform.setVisible(true);
						forfeit.setVisible(true);
						lbl.setText("Now you can perform the trick or choose a prop to forfeit");
					}else {
						currentgame.setEtat(3);
						forfeit.setVisible(true);
						lbl.setText("You forfeit, Please choose 1 prop to turn up. Ready and press 'Forfeit'");
					}
				}else {
					lbl.setText("Please choose 2 props correctly");
				}
				
			}
		});
	}
}
