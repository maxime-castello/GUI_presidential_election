package tp1;

/**
 * @author francoise.perrin
 * inspiration EPFL
 *
 */


import java.util.ArrayList;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
//import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;




/**
  * Classe pour tester la simulation
  */

public class Election {

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {

		Scrutin scrutin;
		int dateSrutin;	
		int population;
		int votants;
		int dateBulletin;
		List< HommePolitique> hommePolitiques;
		List<Candidat> candidats;
		Map<Civilite,List<String>> map = new TreeMap<Civilite,List<String>>();
		
		
		hommePolitiques = new ArrayList< HommePolitique>();
		hommePolitiques.add(new HommePolitique(Civilite.HOMME, "Tarek Oxlama", "parti1"));
		hommePolitiques.add(new HommePolitique(Civilite.HOMME,"Nicolai Tarcozi", "parti2"));
		hommePolitiques.add(new HommePolitique(Civilite.HOMME,"Vlad Imirboutine", "parti3"));
		hommePolitiques.add(new HommePolitique(Civilite.FEMME,"Angel Anerckjel", "parti4"));
		
		scrutin = null;
		dateSrutin = 15;		
		population = 30;
		votants = 20;

		/**
		 * simulation de votes 
		 * - tous sont envoyÈs à la même date 
		 * - Tous passent le check de date
		 * - 1 bulletins papier sur 2 passe check signature
		 */				
		
		System.out.println("\n\t1ère simulation \n" );
		dateBulletin = 13;	
		// simulation votes
		scrutin = simulerVotes(hommePolitiques, votants, dateSrutin, dateBulletin, population);
		
		// Traitement après vote
		
		scrutin.countTheVotes();
		ArrayList<Candidat> candidats1 = scrutin.resultList(); 
		
		//Collections.sort(candidats); // tri par ordre alphabétique
		
		Collections.sort(candidats1,new Comparateur());  // tri par percentage décroissant
		//Collections.reverse(candidats);				// tri par ordre croissant de pourcentage
		
		//Affichage résultat brut du scrutin
		
		
		afficheScrutin(candidats1);
		
		System.out.print("\nCandidat ayant le meilleur résultat: ");
		System.out.println(candidats1.get(0));
		System.out.println("");
		
		

		
		ArrayList<Candidat> clonelist=(ArrayList<Candidat>)candidats1.clone();
		Iterator<Candidat> i=clonelist.iterator();
		
		
		while(i.hasNext()){
            Candidat candidat = (Candidat) i.next();
            if(candidat.getPourCentVoix()<(double)20){
            	i.remove();
            }
		}
		
		
		afficheScrutin(clonelist);
		/**
		 * simulation de votes 
		 * - tous sont envoyés à la même date invalide
		 * - Seuls les bulletins papier passent le check
		 * - 1 bulletins papier sur 2 passe check signature
		 *
		 */		
		alimenteMap(map,candidats1);
		System.out.println(map);
		System.out.println("");
		
		System.out.println("Vue de la Map dans un SET");
		Set < Map.Entry<Civilite, List<String>>> set = map.entrySet();
		System.out.println("Map :\n" +set +"\n");
		System.out.println("");
		
		
		
		//affichage stylisé
		System.out.println("affichage stylisé \n");
		afficheStylise(map);
		
		
		Set <Civilite> set2=map.keySet();
		System.out.println("Map :\n" +set2 +"\n");
		
		Set <Civilite> set3=new TreeSet<Civilite>();
		set3.addAll(set2);
		set3.add(Civilite.FEMME);
		
		Set <Civilite> set4=new TreeSet<Civilite>();
		set4=((TreeSet) set3).descendingSet();
		set4.remove(Civilite.FEMME);
		System.out.println(set3);
		System.out.println(set4);
		
		
		
	
		
		
		System.out.println("\n\t2ème simulation \n" );
		dateBulletin = 16;		
		// simulation votes
		scrutin = simulerVotes(hommePolitiques, votants, dateSrutin, dateBulletin, population);	
		// Traitement après vote
		scrutin.countTheVotes();
		// Affichage résultat brut du scrutin
		candidats = scrutin.resultList(); 
		afficheScrutin(candidats);
		//System.out.println(scrutin);
	}
	
	private static void afficheScrutin(List<Candidat> candidats){
		
		for(Candidat candidat:candidats)
		{
			System.out.println(candidat);
		}
	}

	private static Scrutin simulerVotes(List< HommePolitique> hommePolitiques, int votants,
			int dateSrutin, int dateBulletin, int population) {

		Scrutin scrutin = new Scrutin(hommePolitiques, population, dateSrutin);

		// ou bien
		//		scrutin = new Scrutin(population, dateSrutin);
		//		for (HommePolitique hommePolitique : hommePolitiques )
		//			scrutin.addCandidat(hommePolitique);

		//System.out.println(scrutin);

		if (hommePolitiques!=null){
			for (int i = 0; i < votants; ++i) {

				int candNum = Utils.randomInt(hommePolitiques.size());
				Vote vote = null;

				// bulletins papiers impairs sont signÈs, pairs sont non signÈs
				boolean signature = true;
				if ((i % 2) == 0) {
					signature = false;
				}

				// simulation crÈation bulletins de vote
				switch (i % 3) {
				case 0:{
					vote = new BulletinElectronique(hommePolitiques.get(candNum), dateBulletin, dateSrutin);			
					break;
				}			
				case 1:{
					vote = new BulletinPapier(hommePolitiques.get(candNum), dateBulletin, dateSrutin, signature);
					break;
				}
				case 2:{
					vote = new BulletinCourrier(hommePolitiques.get(candNum), dateBulletin, dateSrutin, signature);
				}
				default: // nothing			
				}
			//	System.out.println(vote);		// pour vÈrif ToString() des classes qui implÈmentent Vote
				scrutin.addBulletin(vote);				
			}
		}
		return scrutin;
	}
	
	
	private static void alimenteMap(Map<Civilite,List<String>> map,List<Candidat> candidats) {
		
		List<String> listhomme=estListHomme(candidats);
		List<String> listfemme=estListFemme(candidats);

		map.put(Civilite.HOMME, listhomme);
		map.put(Civilite.FEMME, listfemme);
			
	}
	
	private static List<String> estListHomme(List<Candidat> candidats){
		
		List<String> listhomme=new ArrayList<String>();
		
		for(Candidat candidat: candidats)
		{
			if(candidat.getCivilite()==Civilite.HOMME){
				
				listhomme.add(candidat.getNom());
			}
			
		}
	 return listhomme;
	}
	
	private static List<String> estListFemme(List<Candidat> candidats){
		
		List<String> listfemme=new ArrayList<String>();
		for(Candidat candidat: candidats)
		{
			if(candidat.getCivilite()==Civilite.FEMME){
				
				listfemme.add(candidat.getNom());
			}
			
		}
	 return listfemme;
	}
	
	private static void afficheStylise(Map<Civilite,List<String>> map){
		
		for(Map.Entry<Civilite, List<String>> e : map.entrySet()){
			System.out.println("Civilité :\n" +e.getKey());
			System.out.println("Noms :\n" +e.getValue()+"");
			//nb d'hommes
			System.out.println("nombre de "+e.getKey()+" : \n"+e.getValue().size());
			System.out.println("");
		}
	}
	

	
}


/**
 * Classe utilitaire
 *
 */
class Utils {

	private static final Random RANDOM = new Random();

	// initialise le gÈnÈrateur de nombres alÈatoires
	public static void setSeed(long seed) {
		RANDOM.setSeed(seed);
	}

	// gÈnËre un entier entre 0 et max (max non compris)
	public static int randomInt(int max) {
		return RANDOM.nextInt(max);
	}
}
