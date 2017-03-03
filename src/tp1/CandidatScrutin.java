package tp1;



/**
 * Les candidats � l'�lection 
 */
public class CandidatScrutin implements Comparable<CandidatScrutin>{

	private HommePolitique hommePolitique;
	private int nbVoix; // nb de voix en faveur du candidat
	int dateScrutin;	// date du scrutin pour lequel il a obtenu ce nb de voix



	public CandidatScrutin(HommePolitique hommePolitique, int dateScrutin) {
		//this.hommePolitique = (HommePolitique) hommePolitique.clone();
		this.hommePolitique = new HommePolitique(hommePolitique.getCivilite(),hommePolitique.getNom(), hommePolitique.getParti()) ;
		this.dateScrutin = dateScrutin;
		nbVoix = 0;
	}

	/**
	 * V�rifie que les attributs de l'HommePolitique pass� en param�tres sont ceux 
	 * les m�mes que ceux de la donn�e interne
	 */
	public boolean containsHommePolitique(HommePolitique hommePolitique) {
		return this.hommePolitique.equals(hommePolitique);    		
	}

	/**
	 * Ajoute une voix en faveur du candidat
	 */
	public void addVoix() {
		++nbVoix;
	} 

	/**
	 * @return the civilit� de l'HommePolitique envelopp�
	 */
	public  Civilite getCivilite() {
		return this.hommePolitique.getCivilite();
	}

	/**
	 * @return the nom de l'HommePolitique envelopp�
	 */

	public  String getNom() {
		return this.hommePolitique.getNom();
	}

	/**
	 * @return the parti de l'HommePolitique envelopp�
	 */

	public  String getParti() {
		return this.hommePolitique.getParti();
	}

	/**
	 * @return le nombre de votes en faveur du candidat
	 */
	public int getNbVoix() {
		return nbVoix;

	}

	/**
	 * @return the dateScrutin
	 */
	public int getDateScrutin() {
		return dateScrutin;
	}

	@Override
	public String toString() {
		/*
		return "[hommePolitique = " + hommePolitique + ", nbVoix = "
				+ getNbVoix() + ", date scrutin = "	+ getDateScrutin() + "]";
		 */
		return "[[civilité` = " + getCivilite() + ", nom = " + getNom() + ", parti = " + getParti() + "]" 
		+ ", nbVoix = "	+ getNbVoix() + ", date scrutin = "	+ getDateScrutin() + "]";

	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dateScrutin;
		result = prime * result
		+ ((hommePolitique == null) ? 0 : hommePolitique.hashCode());
		result = prime * result + nbVoix;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidatScrutin other = (CandidatScrutin) obj;
		if (dateScrutin != other.dateScrutin)
			return false;
		if (hommePolitique == null) {
			if (other.hommePolitique != null)
				return false;
		} else if (!hommePolitique.equals(other.hommePolitique))
			return false;
		if (nbVoix != other.nbVoix)
			return false;
		return true;
	}

	@Override
	public int compareTo(CandidatScrutin candidat) {
		int comp = 0;
		if ((candidat != null)){
			comp = this.hommePolitique.compareTo(candidat.getHommePolitique());
		}
		else
			throw new NullPointerException();
		return comp;
	}


	private HommePolitique getHommePolitique() {
		return hommePolitique;
	}


	public static void main(String[] args){
		CandidatScrutin candidat1 = new CandidatScrutin(new HommePolitique(Civilite.FEMME, "nom1", "parti1"), 10);
		System.out.println(candidat1);
		candidat1.addVoix();
		System.out.println(candidat1);
		CandidatScrutin candidat2 = new CandidatScrutin(new HommePolitique(Civilite.HOMME, "nom2", "parti2"), 10);
		System.out.println(candidat2);
		CandidatScrutin candidat3 = new CandidatScrutin(new HommePolitique(Civilite.HOMME, "nom2", "parti2"), 10);
		System.out.println(candidat3);
		System.out.println("Test ordre alpha < " + candidat1.compareTo(candidat2));
		System.out.println("Test ordre alpha = " + candidat2.compareTo(candidat3));

	}

}
