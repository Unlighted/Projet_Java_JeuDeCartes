package fr.the_other_hat_trick.grenon_liu.vue;



import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.the_other_hat_trick.grenon_liu.modele.Card;
import fr.the_other_hat_trick.grenon_liu.modele.Game;
import fr.the_other_hat_trick.grenon_liu.modele.HumanPlayer;
import fr.the_other_hat_trick.grenon_liu.modele.Player;
import fr.the_other_hat_trick.grenon_liu.modele.Prop;

import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.the_other_hat_trick.grenon_liu.controleur.ControleurChange;
import fr.the_other_hat_trick.grenon_liu.controleur.ControleurForfeit;
import fr.the_other_hat_trick.grenon_liu.controleur.ControleurKeep;
import fr.the_other_hat_trick.grenon_liu.controleur.ControleurPerform;
import fr.the_other_hat_trick.grenon_liu.controleur.ControleurTurn;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * The graphical interface for the The Other Hat Trick model, to be coupled with the controller. Allows to play the game with graphics
 * Cannot work on it's own at all.
 * @author Grenon
 * @author Runkai
 *
 */

public class InterfaceGraphique implements Observer, Runnable{
	
	private Game currentGame;
	private JTextField name;
	private JTextField age;
	private JButton start;
	private JButton turn;
	private JButton notturn;
	private JFrame frame;
	private JLabel lblAllThingsGo = new JLabel("All things go well");
	private JButton btnChange;
	
	
	
	
	private Ilabel[] playerx= {new Ilabel(0, 1),new Ilabel(0, 2), new Ilabel(1, 1),new Ilabel(1, 2),new Ilabel(2, 1),new Ilabel(2, 2),new Ilabel(3, 3)};

	private Ilabel trickpile = new Ilabel(3, 4);
	private final JLabel lblPlayer = new JLabel("Player 1");
	private final JLabel lblPlayer_1 = new JLabel("Player 3");
	private final JLabel lblPlayer_2 = new JLabel("Player 2");
	
	private final JLabel lblControlPad = new JLabel("Control Pad");
	private JButton btnPerform;
	private JButton btnForfeit;
	private JButton btnKeep;
	private JLabel lblTrickLeftDown = new JLabel("Trick left down");

	
	
	public Ilabel[] getPlayerx() {
		return this.playerx;
	}
	


	/**
	 * Test main. Doesn't completely generate the graphical interface
	 */
	public static void main(String[] args) {
		Game currentGame=new Game();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphique window = new InterfaceGraphique(currentGame);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}
		
		);
		
	
	
	}

	/**
	 * Generates the graphical interface and all of its components, calling it to be initialized, then calling the buttons controllers
	 * @param currentGame A reference to the game
	 */
	public InterfaceGraphique(Game currentGame) {
		initialize();
		this.currentGame=currentGame;   
		currentGame.addObserver(this);
		new ControleurTurn(currentGame, getTurn(), getNotturn(),btnChange, getLblAllThingsGo()); // Controllers for all the buttons of the game, visible or not
		new ControleurChange(btnChange, getLblAllThingsGo(), getPlayerx(), currentGame,btnPerform,btnForfeit);
		new ControleurForfeit(btnForfeit, currentGame, getPlayerx(), getTurn(), getNotturn(), getLblAllThingsGo());
		new ControleurPerform(btnPerform, btnKeep, btnForfeit, getLblAllThingsGo(),currentGame);
		new ControleurKeep(btnKeep, currentGame, getTurn(), getNotturn(), getPlayerx(), getLblAllThingsGo());
		
		
		lblTrickLeftDown.setBounds(76, 582, 148, 15);
		getFrame().getContentPane().add(lblTrickLeftDown);
		
		
		
		}

    	

	/**
	 * Initialize the contents of the frame. Called only once
	 */
	
	private void initialize() {
		
		
		setFrame(new JFrame("The other hat trick"));
		getFrame().setBounds(100, 100, 1180, 755);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		
		getLblAllThingsGo().setBounds(133, 658, 1002, 48);
		getFrame().getContentPane().add(getLblAllThingsGo());
		
		btnChange = new JButton("Change");
		btnChange.setBounds(960, 157, 93, 23);
		getFrame().getContentPane().add(btnChange);
		btnChange.setVisible(false);
		
		
		btnPerform = new JButton("Perform");
		
		btnPerform.setBounds(960, 263, 93, 23);
		getFrame().getContentPane().add(btnPerform);
		btnPerform.setVisible(false);
		
		btnKeep = new JButton("Keep");
		
		btnKeep.setBounds(960, 356, 93, 23);
		getFrame().getContentPane().add(btnKeep);
		btnKeep.setVisible(false);
		
		btnForfeit = new JButton("Forfeit");
		btnForfeit.setBounds(960, 309, 93, 23);
		getFrame().getContentPane().add(btnForfeit);
		btnForfeit.setVisible(false);
		
		
		
		playerx[0].setBounds(404, 422, 138, 192);//235, 32, 138, 192
		playerx[0].setIcon(new ImageIcon("back.jpg"));
		getFrame().getContentPane().add(playerx[0]);
		
		
		playerx[1].setBounds(584, 422, 138, 192);//235, 234, 138, 192
		playerx[1].setIcon(new ImageIcon("back.jpg"));
		getFrame().getContentPane().add(playerx[1]);
		
		
		playerx[4].setBounds(235, 32, 138, 192);//761, 32, 138, 192
		playerx[4].setIcon(new ImageIcon("back.jpg"));
		getFrame().getContentPane().add(playerx[4]);
		
		
		playerx[5].setBounds(235, 234, 138, 192);//761, 234, 138, 192
		playerx[5].setIcon(new ImageIcon("back.jpg"));
		getFrame().getContentPane().add(playerx[5]);
		
		
		playerx[2].setBounds(761, 32, 138, 192);//404, 422, 138, 192
		playerx[2].setIcon(new ImageIcon("back.jpg"));
		getFrame().getContentPane().add(playerx[2]);
		
		playerx[3].setBounds(761, 234, 138, 192);//584, 422, 138, 192
		playerx[3].setIcon(new ImageIcon("back.jpg"));
		getFrame().getContentPane().add(playerx[3]);
		
		
		playerx[6].setBounds(488, 139, 138, 192);
		playerx[6].setIcon(new ImageIcon("back.jpg"));
		getFrame().getContentPane().add(playerx[6]);
		
		
		trickpile.setBounds(32, 434, 192, 138);
		trickpile.setIcon(new ImageIcon("back2.jpg"));
		getFrame().getContentPane().add(trickpile);
		
		
		lblPlayer.setBounds(256, 601, 192, 15);
		
		getFrame().getContentPane().add(lblPlayer);
		lblPlayer_1.setBounds(726, 10, 181, 15);
		
		getFrame().getContentPane().add(lblPlayer_1);
		lblPlayer_2.setBounds(160, 10, 269, 15);
		
		getFrame().getContentPane().add(lblPlayer_2);
		
		lblControlPad.setBounds(960, 32, 145, 15);
		getFrame().getContentPane().add(lblControlPad);
		
		setTurn(new JButton("Turn trick"));
		getTurn().setBounds(959, 65, 93, 23);		
		getFrame().getContentPane().add(getTurn());
		getTurn().setVisible(false);
		
		setNotturn(new JButton("Not Turn"));
		getNotturn().setBounds(960, 98, 93, 23);
		getFrame().getContentPane().add(getNotturn());
		getNotturn().setVisible(false);
		
		
		playerx[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (playerx[0].getChoosed()==false) {
				playerx[0].setPictureOriginal(((ImageIcon)(playerx[0].getIcon())).getImage());;
				playerx[0].setIcon(new ImageIcon("front.jpg"));
				playerx[0].setChoosed(true);
				
			}else {
				playerx[0].setIcon(new ImageIcon(playerx[0].getPictureOriginal()));
				playerx[0].setChoosed(false);
			}
				
			}
		});
		
		playerx[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (playerx[1].getChoosed()==false) {
				playerx[1].setPictureOriginal(((ImageIcon)(playerx[1].getIcon())).getImage());;
				playerx[1].setIcon(new ImageIcon("front.jpg"));
				playerx[1].setChoosed(true);
				
			}else {
				playerx[1].setIcon(new ImageIcon(playerx[1].getPictureOriginal()));
				playerx[1].setChoosed(false);
			}
				
			}
		});

		playerx[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg2) {
				if (playerx[2].getChoosed()==false) {
				playerx[2].setPictureOriginal(((ImageIcon)(playerx[2].getIcon())).getImage());;
				playerx[2].setIcon(new ImageIcon("front.jpg"));
				playerx[2].setChoosed(true);
				
			}else {
				playerx[2].setIcon(new ImageIcon(playerx[2].getPictureOriginal()));
				playerx[2].setChoosed(false);
			}
				
			}
		});
		
		playerx[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg3) {
				if (playerx[3].getChoosed()==false) {
				playerx[3].setPictureOriginal(((ImageIcon)(playerx[3].getIcon())).getImage());;
				playerx[3].setIcon(new ImageIcon("front.jpg"));
				playerx[3].setChoosed(true);
				
			}else {
				playerx[3].setIcon(new ImageIcon(playerx[3].getPictureOriginal()));
				playerx[3].setChoosed(false);
			}
				
			}
		});
		
		
		playerx[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg4) {
				if (playerx[4].getChoosed()==false) {
				playerx[4].setPictureOriginal(((ImageIcon)(playerx[4].getIcon())).getImage());;
				playerx[4].setIcon(new ImageIcon("front.jpg"));
				playerx[4].setChoosed(true);
				
			}else {
				playerx[4].setIcon(new ImageIcon(playerx[4].getPictureOriginal()));
				playerx[4].setChoosed(false);
			}
				
			}
		});
		
		playerx[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg5) {
				if (playerx[5].getChoosed()==false) {
				playerx[5].setPictureOriginal(((ImageIcon)(playerx[5].getIcon())).getImage());;
				playerx[5].setIcon(new ImageIcon("front.jpg"));
				playerx[5].setChoosed(true);
				
			}else {
				playerx[5].setIcon(new ImageIcon(playerx[5].getPictureOriginal()));
				playerx[5].setChoosed(false);
			}
				
			}
		});
		
		playerx[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg6) {
				if (playerx[6].getChoosed()==false) {
				playerx[6].setPictureOriginal(((ImageIcon)(playerx[6].getIcon())).getImage());;
				playerx[6].setIcon(new ImageIcon("front.jpg"));
				playerx[6].setChoosed(true);
				
			}else {
				playerx[6].setIcon(new ImageIcon(playerx[6].getPictureOriginal()));
				playerx[6].setChoosed(false);
			}
				
			}
		});
		
	
		
	}
	/**
	 * Show the card even though it's not visible
	 * @param p The card to show
	 * @param b The corresponding label of the card
	 */
	public void forcedShow(Card p, Ilabel b) {
		String name=p.getName();
		b.setIcon(new ImageIcon(name.toLowerCase()+".jpg"));
	}
	/**
	 * Updates the view according to the model
	 * @param p The prop to update
	 * @param b The corresponding label of the card
	 */
	public void miseajour(Prop p, Ilabel b) {
		if (p.getVisibility()) {
			forcedShow(p, b);
		}else {
			b.setIcon(new ImageIcon("back.jpg"));
		}
	}
	
	
	
	/**
	 * Used along Observable interface of the model. Updates every card according to the model
	 */
	public void update(Observable o, Object args) {
		if (o instanceof Game) { // all changes seen in the game
			ArrayList<Player> gotplayers= ((Game)o).getPlayer();
			for(int i=0;i<gotplayers.size();i++) { // updates cards of all players
				switch (i) {
				case 0:
					miseajour(gotplayers.get(i).getOwnedProps().get(0), playerx[0]);
					miseajour(gotplayers.get(i).getOwnedProps().get(1), playerx[1]);
					lblPlayer.setText(gotplayers.get(i).getName()+" points:"+gotplayers.get(i).getTotalPoints());
					break;
				case 1:
					miseajour(gotplayers.get(i).getOwnedProps().get(0), playerx[2]);
					miseajour(gotplayers.get(i).getOwnedProps().get(1), playerx[3]);
					lblPlayer_1.setText(gotplayers.get(i).getName()+" points:"+gotplayers.get(i).getTotalPoints());
					break;
				case 2:
					miseajour(gotplayers.get(i).getOwnedProps().get(0), playerx[4]);
					miseajour(gotplayers.get(i).getOwnedProps().get(1), playerx[5]);
					lblPlayer_2.setText(gotplayers.get(i).getName()+" points:"+gotplayers.get(i).getTotalPoints());
					break;
				default:
					break;
				}
			}
			int c=((Game)o).getTurn()-1;
			if (gotplayers.get(c) instanceof HumanPlayer) {
			switch (c) {
			case 0: 
				forcedShow(gotplayers.get(c).getOwnedProps().get(0), playerx[0]);
				forcedShow(gotplayers.get(c).getOwnedProps().get(1), playerx[1]);
				
				break;
			case 1:
				forcedShow(gotplayers.get(c).getOwnedProps().get(0), playerx[2]);
				forcedShow(gotplayers.get(c).getOwnedProps().get(1), playerx[3]);
				break;
			case 2:
				forcedShow(gotplayers.get(c).getOwnedProps().get(0), playerx[4]);
				forcedShow(gotplayers.get(c).getOwnedProps().get(1), playerx[5]);
				break;
			default:
				break;
			}}
			forcedShow(((Game)o).getTrickPile().showTrick(),trickpile);
			lblTrickLeftDown.setText("Trick left down: "+((Game)o).getTrickPile().getTricksLeftDown());
			miseajour(((Game)o).getUnOwnedProps().get(0), playerx[6]);
			if (((Game)o).getLastTurn()!=3) {
			switch (((Game)o).getEtat()) {
			case 1: // setup the corresponding turn
				this.turn.setVisible(true);
				this.notturn.setVisible(true);
				this.btnChange.setVisible(false);
				this.btnPerform.setVisible(false);
				this.btnForfeit.setVisible(false);
				this.btnKeep.setVisible(false);
				break;
			case 2:
				this.turn.setVisible(false);
				this.notturn.setVisible(false);
				this.btnChange.setVisible(true);
				this.btnPerform.setVisible(false);
				this.btnForfeit.setVisible(false);
				this.btnKeep.setVisible(false);
				break;
			case 3:
				this.turn.setVisible(false);
				this.notturn.setVisible(false);
				this.btnChange.setVisible(false);
				this.btnKeep.setVisible(false);
				if(gotplayers.get(c).ableToPerform(((Game)o).getTrickPile())) {
					this.btnPerform.setVisible(true);
				}
				this.btnForfeit.setVisible(true);
				break;
			case 4:
				this.turn.setVisible(false);
				this.notturn.setVisible(false);
				this.btnChange.setVisible(false);
				this.btnKeep.setVisible(true);
				this.btnForfeit.setVisible(false);
				this.btnPerform.setVisible(false);
				forcedShow(currentGame.getUnOwnedProps().get(0), playerx[6]);
			break;
				
			default:
				break;
			}
			
		}else {
			this.lblPlayer.setVisible(false);
			this.lblPlayer_1.setVisible(false);
			this.lblPlayer_2.setVisible(false);
		}
			}
		
	}



	/**
	 * Generates the interface using a separated Thread
	 */
	public void run() {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphique window = new InterfaceGraphique(currentGame);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}
		
		);
		
	}



	public JButton getTurn() {
		return turn;
	}



	public void setTurn(JButton turn) {
		this.turn = turn;
	}



	public JButton getNotturn() {
		return notturn;
	}



	public void setNotturn(JButton notturn) {
		this.notturn = notturn;
	}



	public JLabel getLblAllThingsGo() {
		return lblAllThingsGo;
	}



	public void setLblAllThingsGo(JLabel lblAllThingsGo) {
		this.lblAllThingsGo = lblAllThingsGo;
	}



	public JFrame getFrame() {
		return frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
