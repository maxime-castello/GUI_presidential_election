package tp1;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;



public class ElectionGui extends JFrame  {
	
	private Election election;	
	private String imageAccueil;
	
    //Déclarations pour le menu
    JMenuBar menuBar = new JMenuBar();
    
    JMenu onglet1 = new JMenu("Résultat Election");
    JMenu onglet2 = new JMenu("Préférence");
    JMenu onglet3 = new JMenu("Gestion d'un scrutin");
    JMenu item3 = new JMenu("Ordre d'affichage des résultats");
    
	JMenuItem item1= new JMenuItem("Après simulation");
    JMenuItem item2 = new JMenuItem("Après gestion d'un scrutin");
    JMenuItem item4 = new JMenuItem("Couleurs des libellés");
    JMenuItem item5 = new JMenuItem("Selon ordre alphabétique");
    JMenuItem item6 = new JMenuItem("Selon ordre décroissant de résultats");
    JMenuItem item7 = new JMenuItem("Couleur du panneau West");
    JMenuItem item8 = new JMenuItem("Taille police libellés");
    
	JPanel panneau = new JPanel();
	JPanel west = new JPanel();
	JPanel center = new JPanel();

	
	List<Candidat> candidats=new ArrayList<Candidat>();
	DisplayOrder displayOrder;

	
	public ElectionGui(String s,Election election, String imageAccueil) {
		this.election = election;
		this.imageAccueil = imageAccueil;
		this.setTitle(s); 		
		mise_en_forme();
		//mise_en_page();
		menu();
		ecoute();
	}
	
	private void mise_en_forme(){
		
		panneau.setBackground(Color.red);	
		add(panneau);
		
		panneau.setLayout(new BorderLayout());
		
		JLabel image = new JLabel(new ImageIcon(imageAccueil));
		panneau.add(image,BorderLayout.CENTER);
		
		
	}
	
	public void menu() {
                
		//Menu
		menuBar.add(onglet1);
		menuBar.add(onglet2);
		menuBar.add(onglet3);
		
		// Sous menu
		onglet1.add(item1);
	    onglet1.add(item2);
		onglet2.add(item3);
		onglet2.add(item4); 
		onglet2.add(item7);
		onglet2.add(item8);
		
        //Sous sous menu 
		item3.add(item5);
		item3.add(item6);
		onglet1.add(item1); 
		 		
		//Affichage
		setJMenuBar(menuBar);
		setVisible(true); 	
		
		
	}
	
	private void mise_en_page(List<Candidat> candidats){
		
		panneau.removeAll();
		add(panneau);
		panneau.setLayout(new BorderLayout());
		
		// WEST
		JLabel text=new JLabel("West");
		west.setBackground(Color.GRAY);
		west.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		west.add(text);
		panneau.add(west,BorderLayout.WEST);
		west.removeAll();
		
		contenuWest(west,candidats,election.newMapCandidatImage(),election.getDateSrutin());
		panneau.add(west,BorderLayout.WEST);
		west.revalidate();
		
		
		// CENTER 
		center.setBackground(Color.RED);
		center.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		GridLayout decoupageCentre = new GridLayout(2,2); //2 Lines 2 colonnes
        center.setLayout(decoupageCentre);
        contenuCenter1(center,candidats, election.getDateSrutin(), "Repartition des candidats du scrutin "+election.getDateSrutin());
        contenuCenter2(center, election.newMapPartiPourcent(), election.getDateSrutin(), "Repartition du scrutin "+election.getDateSrutin());
        contenuCenter3(center, election.newMapCiviliteCandidats(), election.getDateSrutin(), "Repartition des candidats du scrutin "+election.getDateSrutin()+" par Sexe/Candidat");
        contenuCenter4(center, election.newMapCiviliteCandidats(), election.getDateSrutin(), "Repartition des candidats du scrutin "+election.getDateSrutin()+" par Candidat/Sexe");

        
		panneau.add(center,BorderLayout.CENTER);
		panneau.revalidate();
		
	}
	
	private void contenuWest(JPanel west, List<Candidat> candidats, Map<Candidat, String> map, int dateScrutin ){
		
		west.removeAll();
		Box VerticaleBox=Box.createVerticalBox();
		west.add(VerticaleBox);
		Box title=Box.createHorizontalBox();
		JLabel titre= new JLabel("Résultat du scrutin du"+ dateScrutin+"");
		title.add(titre);
		VerticaleBox.add(title);
		
		for (Candidat candidat:candidats)
			{ 
				Box HorizontaleBox=Box.createHorizontalBox();
				Box VerticaleBox1=Box.createVerticalBox();
				Box VerticaleBox2=Box.createVerticalBox();
				
				JLabel civlite=new JLabel(""+candidat.getCivilite()+"");
				JLabel nom=new JLabel(""+candidat.getNom()+"");
				JLabel parti=new JLabel(""+candidat.getParti()+"");
				String str=String.format( "%2.1f", candidat.getPourCentVoix());
				JLabel pourcentage=new JLabel(""+str+"% des voix");
				JLabel image=new JLabel(new ImageIcon(map.get(candidat)));
				
				HorizontaleBox.add(VerticaleBox1);
				HorizontaleBox.add(VerticaleBox2);
				
				VerticaleBox1.add(image);
				
				VerticaleBox2.add(civlite);
				VerticaleBox2.add(nom);
				VerticaleBox2.add(parti);
				VerticaleBox2.add(pourcentage);
				VerticaleBox.add(HorizontaleBox);
				
			}
		west.revalidate();
		
	}
	
	private void contenuCenter1(JPanel center,List<Candidat> candidats, int dateScrutin,String titre ){
		
		center.removeAll();
        // create a dataset...
        DefaultPieDataset dataset =new DefaultPieDataset();
        
        for(Candidat candidat:candidats){
        	dataset.setValue(candidat.getNom(), candidat.getPourCentVoix());
        }
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
                titre,  // chart title
                dataset,                // data
                true,                   // include legend
                true,
                false
            );
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        center.add(chartPanel);
        center.revalidate();

	}
	
	private void contenuCenter2(JPanel center, Map<String, Double> map, int dateScrutin,String titre ){
		
		
        // create a dataset...
        DefaultPieDataset dataset =new DefaultPieDataset();
        
        for (Map.Entry<String, Double> entree : map.entrySet()) {
        	dataset.setValue(entree.getKey(), entree.getValue());
        }
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
                titre,  // chart title
                dataset,                // data
                true,                   // include legend
                true,
                false
            );
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        center.add(chartPanel);
        center.revalidate();

	}
	
	private void contenuCenter3(JPanel center, Map<Civilite, List<Candidat>> map, int dateScrutin,String titre ){
		
		
        // create a dataset...
		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
        
        for (Map.Entry<Civilite, List<Candidat>> entree : map.entrySet()) {
        	List<Candidat> candidats=entree.getValue();
        	for (Candidat candidat: candidats)
        	{dataset.addValue(candidat.getPourCentVoix(), entree.getKey(), candidat.getNom());}
        }
        
        final JFreeChart chart = ChartFactory.createBarChart3D(
                titre,      // chart title
                "",               // domain axis label
                "Pourcentage de voix",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL, // orientation
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
            );
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        center.add(chartPanel);
        center.revalidate();

	}
	
	private void contenuCenter4(JPanel center, Map<Civilite, List<Candidat>> map, int dateScrutin,String titre ){
		
		
        // create a dataset...
		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
        
        for (Map.Entry<Civilite, List<Candidat>> entree : map.entrySet()) {
        	List<Candidat> candidats=entree.getValue();
        	for (Candidat candidat: candidats)
        	{dataset.addValue(candidat.getPourCentVoix(), candidat.getNom(), entree.getKey());}
        }
        
        final JFreeChart chart = ChartFactory.createBarChart3D(
                titre,      // chart title
                "",               // domain axis label
                "Pourcentage de voix",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL, // orientation
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
            );
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        center.add(chartPanel);
        center.revalidate();

	}
        
        
        
    
	
	private void ecoute () {
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				candidats=ElectionGui.this.election.simulation(displayOrder);
				mise_en_page(candidats);
			}
		});
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				candidats=election.sortCandidats(DisplayOrder.ALPHA);
				mise_en_page(candidats);
			}
		});
		item6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				candidats=election.sortCandidats(DisplayOrder.POURCENT);
				mise_en_page(candidats);
			}
		});
		
	}
}
	
	