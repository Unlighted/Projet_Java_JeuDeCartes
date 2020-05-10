package fr.the_other_hat_trick.grenon_liu.vue;

import javax.swing.JLabel;

import com.sun.prism.Image;

import fr.the_other_hat_trick.grenon_liu.modele.Player;
/**
 * To create more complex labels, like images which you can click on
 * @author Grenon
 * @author Runkai
 *
 */
public class Ilabel extends JLabel{
	private int player;
	private int num;
	private boolean choosed;
	private java.awt.Image pictureOriginal;
	/**
	 * The constructor of the label
	 * @param p The player who has the label
	 * @param num The number to design his owned cards
	 */
	public Ilabel(int p, int num) {
		super("");
		this.num=num;
		this.player=p;
		this.choosed=false;
		
	}
	public int getPlayer() {
		return this.player;
	}
	public int getNum() {
		return this.num;
	}
	public boolean getChoosed() {
		return this.choosed;
	}
	public void setChoosed(boolean b) {
		this.choosed=b;
	}
	public java.awt.Image getPictureOriginal() {
		return pictureOriginal;
	}
	public void setPictureOriginal(java.awt.Image image) {
		this.pictureOriginal = image;
	}
}
