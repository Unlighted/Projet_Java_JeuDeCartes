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
 * Checks if the card chosen to reveal is correct
 * @author Grenon
 * @author Runkai
 *
 */
public class ControleurForfeit {
/**
 * The constructor of the controller, with all references
 * @param forfeit The forfeit button pressed
 * @param currentgame A reference to the game
 * @param playerx All the cards that players own
 * @param turn Setup for the next turn to allow the button
 * @param notturn Setup for the next turn to allow the button
 * @param lbl The label to change its content
 */
public ControleurForfeit(JButton forfeit,Game currentgame, Ilabel[] playerx, JButton turn, JButton notturn, JLabel lbl) {
	forfeit.addActionListener(new ActionListener() {
		/**
		 * A click on the button
		 * @param the click
		 */
		public void actionPerformed(ActionEvent arg0) {
			int count=0;
			ArrayList<Ilabel> choosed= new ArrayList<>();
			for(int i=0;i<playerx.length;i++) {
				if(playerx[i].getChoosed()) {// get the players chosen
					choosed.add(playerx[i]);
					count++;
				}
			}
			for(int i=0;i<playerx.length;i++) { // reset them
				playerx[i].setChoosed(false);
			}
			currentgame.refresh();// if the choices are right
			if(count==1&&choosed.get(0).getPlayer()==(currentgame.getTurn()-1)&&(currentgame.getPlayer().get(currentgame.getTurn()-1).getOwnedProps().get(choosed.get(0).getNum()-1).getVisibility()==false||(currentgame.getPlayer().get(currentgame.getTurn()-1).getOwnedProps().get(0).getVisibility()==true&&currentgame.getPlayer().get(currentgame.getTurn()-1).getOwnedProps().get(1).getVisibility()==true))) {
				currentgame.getPlayer().get(currentgame.getTurn()-1).forfeit(choosed.get(0).getNum());
				
				currentgame.setTurn(currentgame.getTurn()+1);
				if(currentgame.getTurn()>3) {
					currentgame.setTurn(1);
				}
				currentgame.continuerAI();// make the robots play their turn
				currentgame.setEtat(1);
				turn.setVisible(true);
				notturn.setVisible(true);
				forfeit.setVisible(false);
				lbl.setText("Now you choose to turn the trick or not");// prepare the beginning of the next turn
				if(currentgame.getLastTurn()==3||(currentgame.getTrickPile().getTricksLeftDown()==0&&currentgame.getTrickPile().showTrick().getName()!="The Other Hat Trick")) {
					ArrayList<Player> endPlayers= currentgame.endGameInterface();
					lbl.setText(endPlayers.get(0).getName()+", he got "+endPlayers.get(0).getTotalPoints()+"  "+endPlayers.get(1).getName()+", he got "+endPlayers.get(1).getTotalPoints()+"   "+endPlayers.get(2).getName()+", he got "+endPlayers.get(2).getTotalPoints()+"\n");
					turn.setVisible(false);
					notturn.setVisible(false);
					currentgame.setEtat(0);
					currentgame.refresh();
					
				}
				
			}else {
				lbl.setText("Please choose the right prop to forfeit");
			}
			if(currentgame.getTrickPile().getTricksLeftDown()==0) {
				turn.setVisible(false);
				;
			}
		}
	});
}
}
