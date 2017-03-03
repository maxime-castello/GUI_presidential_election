package tp1;

public class BulletinCourrier extends Bulletin implements Vote, CheckDateBulletin, CheckSigneBulletin {

	private boolean signature;
	
	public BulletinCourrier(HommePolitique homme_pol,int dateVote,int dateScrutin,boolean signature){
		super(homme_pol,dateVote,dateScrutin);
		this.signature=signature;
	}
	
	public boolean checkSigne() {
		
		return signature;
		
	}

	@Override
	public boolean checkDate() {
		
		return(DateVote<=DateScrutin);
		
	}

	@Override
	public boolean estInvalide() {
		
		return  !(checkDate() && checkSigne()); // Si la date et la signature valides
											    // On renvoie false car c'est valide
	}


	
	public String getValidite(){
		if(estInvalide()) return "invalide";
		else return "valide";
	}
	

}
