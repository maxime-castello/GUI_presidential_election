package tp1;

import java.util.Comparator;

public class Comparateur  {

public static final Comparator<Candidat> POURCENTVOIX = new Comparator<Candidat>(){
    
	@Override
	public int compare(Candidat o1, Candidat o2)
	{
		if ((o1 != null) && (o2 != null) && (o1 instanceof Candidat) && (o2 instanceof Candidat))
			return ((Double)o2.getPourCentVoix()).compareTo((Double)o1.getPourCentVoix());
		else
			throw new ClassCastException();           
	}
};
}
	



