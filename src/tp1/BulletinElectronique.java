package tp1;

public class BulletinElectronique extends Bulletin implements CheckDateBulletin, Vote {

	
	public BulletinElectronique(HommePolitique homme_pol,int dateVote,int dateScrutin){
		super(homme_pol,dateVote,dateScrutin);
		
	}
	@Override
	public boolean estInvalide() {
		
		return  !(checkDate()); // Si la date et la signature valides
											    // On renvoie false car c'est valide
	}



	@Override
	public boolean checkDate() {
		return ( DateVote>=(DateScrutin-2) && DateVote<=DateScrutin );
	
	}
	
	public String getValidite(){
		if(estInvalide()) return "invalide";
		else return "valide";
	}

}
