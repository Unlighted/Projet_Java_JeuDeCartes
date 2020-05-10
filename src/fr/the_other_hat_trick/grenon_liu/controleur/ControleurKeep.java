package fr.the_other_hat_trick.grenon_liu.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import fr.the_other_hat_trick.grenon_liu.vue.Ilabel;
import fr.the_other_hat_trick.grenon_liu.modele.Game;
import fr.the_other_hat_trick.grenon_liu.modele.Player;
/**
 * Checks if the cards chosen to keep are right if performed trick
 * @author Grenon
 * @author Runkai
 *
 */
public class ControleurKeep {
	/**
	 * The constructor of the controller
	 * @param keep The keep button
	 * @param currentgame A reference to the game
	 * @param turn The button turn to prepare next turn
	 * @param notturn The button notturn to prepare next turn
	 * @param playerx A reference to the cards of the players
	 * @param lbl The label to modify its content
	 */
	public ControleurKeep(JButton keep, Game currentgame, JButton turn, JButton notturn, Ilabel[] playerx, JLabel lbl) {
		keep.addActionListener(new ActionListener() {
			/**
			 * A press on the button
			 * @param The click
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				int count=0;
				ArrayList<Ilabel> choosed= new ArrayList<>(); // get all the players chosen
				for(int i=0;i<playerx.length;i++) {
					if(playerx[i].getChoosed()) {
						choosed.add(playerx[i]);
						count++;
					}
				}
				for(int i=0;i<playerx.length;i++) { // remove them from being chosen
					playerx[i].setChoosed(false);
				}
				currentgame.refresh();
				// if the choices are conform
				if(count==2&&(choosed.get(0).getPlayer()==(currentgame.getTurn()-1)||choosed.get(0).getPlayer()==3)&&(choosed.get(1).getPlayer()==(currentgame.getTurn()-1)||choosed.get(1).getPlayer()==3)) {
					currentgame.changeUnownedProps(choosed.get(0).getNum(), choosed.get(1).getNum());
					
					currentgame.refresh();
					
					currentgame.setEtat(1);
					currentgame.getUnOwnedProps().get(0).setVisible(false);
					currentgame.getPlayer().get(currentgame.getTurn()-1).getOwnedProps().get(0).setVisible(false);
					currentgame.getPlayer().get(currentgame.getTurn()-1).getOwnedProps().get(1).setVisible(false);
					currentgame.refresh();
					currentgame.setTurn(currentgame.getTurn()+1);// end of the turn
					if(currentgame.getTurn()>3) {
						currentgame.setTurn(1);
					}
					currentgame.continuerAI();
					turn.setVisible(true);// prepare the next turn
					notturn.setVisible(true);
					keep.setVisible(false);
					
					lbl.setText("Now you choose to turn the trick or not");
					
					if(currentgame.getTrickPile().getTricksLeftDown()==0) {
						turn.setVisible(false);
					}
				}else {
					lbl.setText("Please choose the right props to keep");
				}
				if(currentgame.getLastTurn()==3||(currentgame.getTrickPile().getTricksLeftDown()==0&&currentgame.getTrickPile().showTrick().getName()!="The Other Hat Trick")) {
					ArrayList<Player> endPlayers= currentgame.endGameInterface();// displays that he got the right combination
					lbl.setText(endPlayers.get(0).getName()+", he got "+endPlayers.get(0).getTotalPoints()+"  "+endPlayers.get(1).getName()+", he got "+endPlayers.get(1).getTotalPoints()+"   "+endPlayers.get(2).getName()+", he got "+endPlayers.get(2).getTotalPoints()+"\n");
					turn.setVisible(false);
					notturn.setVisible(false);
					currentgame.setEtat(0);
				}
				if(currentgame.getTrickPile().getTricksLeftDown()==0) {
					turn.setVisible(false);
					
				}
				
				
				
				
				
			}
		});
	}
}
