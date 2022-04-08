package game;

public class RankInfo {
	private static RankInfo instance;
	
	private String name;
	private int score;
	
	private RankInfo() {
		
	}
	
	public RankInfo(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public static RankInfo getInstance() {
		if(instance == null) {
			instance = new RankInfo();
		}
		
		return instance;
	}
	
	public void setDefault() {
		name = "\0";
		score = 0;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
