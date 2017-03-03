package tp1;

import java.util.LinkedList;
import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.List;
// import java.util.Random;

public class Scrutin {
	
	private List<CandidatScrutin> candidatScrutins;
	private List<Vote> votes;
	private int population;
	private int votants;
	private int dateScrutin;
	
	public Scrutin(int population, int dateScrutin){
	
		this.population=population;
		this.dateScrutin=dateScrutin;
		this.votes=new LinkedList <Vote>();
		this.candidatScrutins=new LinkedList <CandidatScrutin>();
	}
	
	public Scrutin(List<HommePolitique> hommesPolitiques,int population, int dateScrutin){
		
		this.votes=new LinkedList <Vote>();
		this.candidatScrutins=new LinkedList <CandidatScrutin>();		
		this.population=population;
		this.dateScrutin=dateScrutin;
		this.initListCandidatScrutins(hommesPolitiques);
		
	}
	
	private void initListCandidatScrutins(List<HommePolitique> hommesPolitiques){
		if (hommesPolitiques !=null){
			for (HommePolitique hommePolitique : hommesPolitiques){
				CandidatScrutin candidatScrutin = null;
				candidatScrutin = new CandidatScrutin (hommePolitique, this.getDateScrutin());
			this.candidatScrutins.add(candidatScrutin);
			}
		}
	}
	

	public void addCandidat (HommePolitique hommePolitique,int dateScrutin){
		
		CandidatScrutin candidatScrutin= new CandidatScrutin(hommePolitique,dateScrutin);
		this.candidatScrutins.add(candidatScrutin);
	}
	
	public void addBulletin (Vote bulletin){
		
		this.votes.add(bulletin);
	}
	
	@Override
	public String toString() {
		
		return "Scrutin [dateScrutin=" + dateScrutin + ", " +
		"population=" + population + ", " +
		"totalVotants="	+ votants + "," +
		String.format("Taux de participation avec vote valide=%2.1f", this.tauxParticipation())+ "%" +
		"\ncandidatScrutins =" + candidatScrutins +"]";
		
	}
	
	// taux de participation
	
	public double tauxParticipation() {
		
		return votants * 100.0 / getPopulation();
		
	}
	
	// compteurs de votes
	
	public void countTheVotes () {
		
		for (Vote vote: votes){
			
			if(!(vote.estInvalide()))
			{
				this.votants++; // on incrémente le nb de voix valides
				HommePolitique hommePolitique=vote.getHommePolitique();
				
				for (CandidatScrutin candidatscrutin: candidatScrutins)
				{
					if(  candidatscrutin.getNom().equals(hommePolitique.getNom())  ) 
					{		
						candidatscrutin.addVoix(); // on incrémente le nb de voix pour cet hp
				    }  
				}
			}
				
		}
	}
	
	// retourner ArrayList de Candidat
	
	public ArrayList<Candidat> resultList() {
		ArrayList <Candidat> candidats = new ArrayList <Candidat>();
		for (CandidatScrutin candidatScrutin : this.candidatScrutins) {
			candidats.add(new Candidat(candidatScrutin, this.votants));
		}
		return candidats;
	}
	
	
	
	
	
	// GETTERS
	public int getVotants(){
		
		return this.votants;
	}
	
	public int getDateScrutin(){
		
		return this.dateScrutin;
	}
	
	public List<CandidatScrutin> getCandidatScrutins() {
		
		return this.candidatScrutins;
	}
	
	public List<Vote> getVotes() {
		
		return this.votes;
	}

	public int getPopulation() {
		
		return this.population;
	}
	
	
	

}
