package tp1;

abstract class Bulletin implements Vote {

	protected HommePolitique hommepolitique;
	protected int DateVote;
	protected int DateScrutin;
	
	
	public Bulletin(HommePolitique hommepolitique,int DateVote,int DateScrutin)
	 {
		this.hommepolitique = (HommePolitique) hommepolitique.clone() ;
		this.DateVote=DateVote;
		this.DateScrutin=DateScrutin;
	 }
	@Override
	public HommePolitique getHommePolitique() {
		
		
		return hommepolitique;
	}

	@Override
	public int getDate() {
		
		
		return DateVote;
	}
	
	public abstract String getValidite();
	
	public String toString() {
		
		return "Vote par "+getClass().getSimpleName()+" pour "+hommepolitique.toString()+" -> "+this.getValidite()+"";
		
	}
	
	
	
	
	
	
}
