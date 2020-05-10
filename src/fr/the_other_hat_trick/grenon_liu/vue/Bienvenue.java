package fr.the_other_hat_trick.grenon_liu.vue;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.sun.glass.ui.TouchInputSupport;

import fr.the_other_hat_trick.grenon_liu.controleur.ControleurBienvenue;
import fr.the_other_hat_trick.grenon_liu.modele.Game;
/**
 * The interface to choose the setup of the game
 * @author Grenon
 * @author Runkai
 *
 */
public class Bienvenue {

	private JFrame frame;
	private JButton start;
	private JTextField name;// player 1
	private JTextField age;
	private Game currentgame;
	private JTextField txtNomberOfHumain;
	private JTextField txtName;// player 2
	private JTextField txtAge;
	private JTextField txtName_1; // player 3
	private JTextField txtAge_1;
	
	
	/**
	 * Starts the game by asking the configs and creating interfaces and game
	 */
	public static void main(String[] args) {
		Game currentgame=new Game();
		Bienvenue bienvenue=new Bienvenue(currentgame); // to setup the game from this interface
		bienvenue.frame.setVisible(true);
		InterfaceGraphique window=new InterfaceGraphique(currentgame); // visible after setting up
		window.getFrame().setVisible(false);
		VueTexte vTexte=new VueTexte(currentgame);
		vTexte.start();
		// to make the changes
		new ControleurBienvenue(bienvenue.start, bienvenue.name, bienvenue.age, bienvenue.frame, window, bienvenue.currentgame, bienvenue.txtName, bienvenue.txtAge, bienvenue.txtName_1, bienvenue.txtAge_1,bienvenue.txtNomberOfHumain);
	}

	/**
	 * Creates the Welcoming interface
	 */
	public Bienvenue(Game currentgame) {
		initialize();
		this.currentgame=currentgame;
	}

	/**
	 * Initialize the contents of the frame, with all the choices.
	 */
	private void initialize() {
		frame = new JFrame("Welcome to game");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		name = new JTextField();
		name.setText("name1");
		name.setBounds(42, 101, 66, 21);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		age=new JTextField();
		age.setText("1");
		age.setBounds(42, 144, 66, 21);
		frame.getContentPane().add(age);
		age.setColumns(10);
		
		start=new JButton("start");// to launch the game
		start.setBounds(132, 195, 93, 23);
		frame.getContentPane().add(start);
		
		txtNomberOfHumain = new JTextField(); // this value can make other entries useless
		txtNomberOfHumain.setText("number of humain");
		txtNomberOfHumain.setBounds(156, 28, 147, 21);
		frame.getContentPane().add(txtNomberOfHumain);
		txtNomberOfHumain.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("name2");
		txtName.setBounds(156, 101, 66, 21);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setText("2");
		txtAge.setBounds(156, 144, 66, 21);
		frame.getContentPane().add(txtAge);
		txtAge.setColumns(10);
		
		txtName_1 = new JTextField();
		txtName_1.setText("name3");
		txtName_1.setBounds(267, 101, 66, 21);
		frame.getContentPane().add(txtName_1);
		txtName_1.setColumns(10);
		
		txtAge_1 = new JTextField();
		txtAge_1.setText("3");
		txtAge_1.setBounds(267, 144, 66, 21);
		frame.getContentPane().add(txtAge_1);
		txtAge_1.setColumns(10);
	}
}
