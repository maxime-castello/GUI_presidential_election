package tp1;

public class BulletinPapier extends Bulletin implements CheckSigneBulletin, CheckDateBulletin, Vote {

	private boolean signature;
	
	public BulletinPapier(HommePolitique homme_pol,int dateVote,int dateScrutin,boolean signature){
		super(homme_pol,dateVote,dateScrutin);
		this.signature=signature;
	}
	@Override
	public boolean estInvalide() {
		
		// return  !(checkDate() && checkSigne());                // Si la date et la signature valides
		return  !(checkSigne());								    // On renvoie false car c'est valide
	}

	public String getValidite(){
		if(estInvalide()) return "invalide";
		else return "valide";
	}
	


	@Override
	public boolean checkDate() {
		
		return(DateVote==DateScrutin);
		
	}

	public boolean checkSigne() {
		
		return signature;
		
	}

}
