package misc;

public class GetGoodException extends Exception {

	private static final long serialVersionUID = -5927423448771076349L;
	
	private int plrScore;
	private int minScore;
	
	public GetGoodException(int score) {
		this.plrScore = score;
		this.minScore = 100000;
	}
	
	public int getPlrScore() {
		return plrScore;
	}

	public int getMinScore() {
		return minScore;
	}
}
