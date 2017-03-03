package fichiersPourTP.tp1;

public class CandidatScrutin {
	
	private HommePolitique homme;
	private int nbVoix;
	private int dateScrutin;
	
	@Override
	public String toString() {
		return "CandidatScrutin [h=" + homme + ", nbVoix=" + nbVoix + ", dateScrutin=" + dateScrutin + "]";
	}
	
	public CandidatScrutin( HommePolitique h, int dateScrutin) {

		this.homme= (HommePolitique) h.clone();
		this.nbVoix=0;
		this.dateScrutin=dateScrutin;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dateScrutin;
		result = prime * result + ((homme == null) ? 0 : homme.hashCode());
		result = prime * result + nbVoix;
		return result;
	}

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
		if (homme == null) {
			if (other.homme != null)
				return false;
		} else if (!homme.equals(other.homme))
			return false;
		if (nbVoix != other.nbVoix)
			return false;
		return true;
	}
	
	public int getNbVoix (){
		return nbVoix;
	}
	
	public int getDateScrutin (){
		return dateScrutin;
	}
	
	public String getParti (){
		return homme.getParti();
	}
	
	public Civilite getCivilite (){
		return homme.getCivilite();
	}
	
	public String getNom (){
		return homme.getNom();
	}
	
	public void incNbVoix(int nbVoix) {
		nbVoix+=1;
	}
	
	public boolean verifAttrib(HommePolitique h) {
		return(this.getCivilite()==h.getCivilite() && this.getNom()==h.getNom() && this.getParti()==h.getParti());
	}
	
	public int compareInstance(CandidatScrutin c){
		return homme.compareTo(c.homme);
	}
	
	
	
	
	
}
