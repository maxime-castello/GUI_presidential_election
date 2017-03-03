package tp1;

import java.util.Comparator;

public class Comparateur implements Comparator<Candidat> {

	@Override
	public int compare(Candidat o1, Candidat o2) {
		
		return ((Double)o2.getPourCentVoix()).compareTo((Double)o1.getPourCentVoix());
		
	}
    
}
	



