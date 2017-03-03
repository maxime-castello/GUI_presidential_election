package tp1;

/**
 * @author francoise.perrin
 *
 */


/**
 * Vue sur un candidatScrutin ne donnant l'acc�s que en lecture 
 * aux donn�es priv�es d'un candidat
 */
public class Candidat implements Comparable<Candidat>, Cloneable{

	private CandidatScrutin candidatScrutin;
	private  double pourCentVoix;
	
	public Candidat(CandidatScrutin candidatScrutin, int nbVotesValides) {
		super();
		this.candidatScrutin = candidatScrutin;
		pourCentVoix = candidatScrutin.getNbVoix()*100/(float)nbVotesValides;	
	}
	
	public  Civilite getCivilite() {
		return this.candidatScrutin.getCivilite();
	}
	
	public  String getNom() {
		return this.candidatScrutin.getNom();
	}

	public  String getParti() {
		return this.candidatScrutin.getParti();
	}
	
	public int getDateScrutin() {
		return candidatScrutin.getDateScrutin();
	}

	public double getPourCentVoix() {
		return pourCentVoix;
	}
	
	public boolean containsHommePolitique(HommePolitique hommePolitique) {
		return this.candidatScrutin.containsHommePolitique(hommePolitique);    		
	}


	@Override
	public String toString() {		
		String ret = "[" ;
		ret +=  this.getNom();
		ret +=  ", ";
		ret +=  this.getParti();
		ret +=  ", Scrutin du ";
		ret +=  this.getDateScrutin();
		ret += String.format(" -> %2.1f", this.getPourCentVoix());
		ret +="% des électeurs]";
		return ret;
	}

	@Override
	public int compareTo(Candidat candidat) {
		int comp = 0;
		if ((candidat != null))
			comp = this.candidatScrutin.compareTo(candidat.candidatScrutin);
		else
			throw new NullPointerException();
		return comp;
	}


	
	
	public static void main(String[] args){

	}

}
