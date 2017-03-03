package fichiersPourTP.tp1;


/**
 * @author Maxime Castello
 *
 */
public class HommePolitique implements Comparable <HommePolitique>, Cloneable {
	private Civilite civilite;
	private String nom;
	private String parti;
	
	public  HommePolitique (Civilite civilite, String nom, String parti) {
		this.civilite= civilite;
		this.nom=nom;
		this.parti=parti;
	}
	
	public String toString(){
		return "[civilite = " + this.civilite + ", nom = "+ this.nom + ", parti = "+this.parti +"]"; 
	}
	
	
	public Civilite getCivilite (){
		return civilite;
	}
	public void setCivilite (Civilite c) {
		this.civilite=c;		
	}
	
	public String getNom (){
		return nom;
	}
	public void setNom (String n) {
		this.nom=n;		
	}
	
	public String getParti (){
		return parti;
	}
	public void setParti (String p) {
		this.parti=p;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((parti == null) ? 0 : parti.hashCode());
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
		HommePolitique other = (HommePolitique) obj;
		if (civilite != other.civilite)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (parti == null) {
			if (other.parti != null)
				return false;
		} else if (!parti.equals(other.parti))
			return false;
		return true;
	}

	@Override
	public int compareTo(HommePolitique o)    {
        // Comparaison sur le nom
        int compNom = this.getNom().compareTo(o.getNom());
        if(compNom != 0) { return compNom; }

        // Comparaison sur le parti
        int compParti = this.getParti().compareTo(o.getParti());
        if(compParti != 0) { return compParti; }

        // Comparaison sur la civilite
        int compCivilite = this.getCivilite().compareTo(o.getCivilite());
        if(compCivilite != 0) { return compCivilite; }

        // Les deux hommes politiques sont égaux
        return 0;
    }
	
	
	/*
	public boolean equals(HommePolitique h){
	
	return( this.nom == h.nom && this.parti == h.parti )

	}
	*/
	
	public Object clone(){
		Object h = new HommePolitique(this.civilite, this.nom, this.parti);
		return h;
	}	
	
	
	
}
