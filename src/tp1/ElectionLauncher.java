package tp1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;

/**
 * @author francoise.perrin
 * Inspiration MOOC sur Coursera "Introduction ‡ la POO (en Java)"
 * by Jamila Sam, Jean-CÈdric Chappelier - EPFL 
 */

public class ElectionLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File g=new File("");
		String imageAccueil = g.getAbsolutePath()+"\\images\\felixCat.gif" ; // ‡ personnaliser
		
		Election election = new Election();
		
		JFrame frame;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.height -= 30;
		frame = new ElectionGui("RÈsultat des Èlections", election, imageAccueil);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setPreferredSize(dim);
		frame.pack();
		frame.setVisible(true);
	}
}
