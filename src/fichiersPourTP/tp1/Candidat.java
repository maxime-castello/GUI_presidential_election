package fichiersPourTP.tp1;

public class Candidat {

		private CandidatScrutin candidat;
		private double pourcentage;
		
		@Override
		public String toString() {
			return "Candidat [candidat=" + candidat + ", pourcentage=" + pourcentage + "]";
		}
		
		public Candidat(CandidatScrutin candidat, int nbVotesValides){
			
			this.candidat=candidat;
			this.pourcentage= ((double)this.candidat.getNbVoix()/(double) nbVotesValides)*100;	
			
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((candidat == null) ? 0 : candidat.hashCode());
			long temp;
			temp = Double.doubleToLongBits(pourcentage);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			Candidat other = (Candidat) obj;
			if (candidat == null) {
				if (other.candidat != null)
					return false;
			} else if (!candidat.equals(other.candidat))
				return false;
			if (Double.doubleToLongBits(pourcentage) != Double.doubleToLongBits(other.pourcentage))
				return false;
			return true;
		}
		

		public double getPourcentage (){
			return pourcentage;
		}
		
		public int getNbVoix (){
			return candidat.getNbVoix();
		}
		
		public int getDateScrutin (){
			return candidat.getDateScrutin();
		}
		
		public String getParti (){
			return candidat.getParti();
		}
		
		public Civilite getCivilite (){
			return candidat.getCivilite();
		}
		
		public String getNom (){
			return candidat.getNom();
		}
		
		public int compareCandidat(Candidat c){
			return candidat.compareInstance(c.candidat);
		}
	
}
