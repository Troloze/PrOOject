package game;

import java.awt.Graphics2D;

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
	
	public void drawScore(Graphics2D g2) {
		String str = "SCORE: ";
		
		g2.setColor(TextParameter.NOT_FOCUS_COLOR);
		TextParameter.setFont(g2, TextParameter.SCORE_FONT_SIZE);
		g2.drawString(str + instance.getScore(), TextParameter.STRING_PANEL_X_BEGINNING, TextParameter.STRING_PANEL_Y_BEGINNING);
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
