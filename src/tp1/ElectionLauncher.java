package tp1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;


public class ElectionLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File g=new File("");
		String imageAccueil = g.getAbsolutePath()+"/gif/felixCat.gif" ; // ‡ personnaliser
		
		Election election = new Election();
		
		JFrame frame;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.height -= 30;
		frame = new ElectionGui("Résultat des élections", election, imageAccueil);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setPreferredSize(dim);
		frame.pack();
		frame.setVisible(true);
	}
}
