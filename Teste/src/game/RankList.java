package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.FileHandler;

public class RankList {
	private static RankList instance;
	
	private static final String DIRECTORY = "rank.txt";
	
	private FileHandler file;
	
	private RankInfo mainInfo;
	private ArrayList<RankInfo> rankList;
	
	private RankList() {
		file = FileHandler.getInstance();
		mainInfo = RankInfo.getInstance();
		rankList = new ArrayList<RankInfo>();
		
		formatRankList();
	}
	
	public static RankList getInstance() {
		if(instance == null) {
			instance = new RankList();
		}
		
		return instance;
	}
	
	public void formatRankList() {
		if(!rankList.isEmpty()) return;
		
		String name;
		
		ArrayList<String> fileStr = file.readFile(DIRECTORY);
		String[] str;
		
		for(int i = 0; i < fileStr.size(); i++) {
			str = fileStr.get(i).split(":");
			
			if(str[0].indexOf("\0") > 0) {
				name = str[0].substring(0, str[0].indexOf("\0"));
			} else {
				name = str[0];
			}
			
			rankList.add(new RankInfo(name, Integer.parseInt(str[1])));
		}
	}
	
	public void sortRankList() {
		if(rankList.size() < 10) {
			rankList.add(new RankInfo(mainInfo.getName(), mainInfo.getScore()));
			System.out.println("Nome: " + rankList.get(0).getName() + " Score: " + rankList.get(0).getScore());
			return;
		}
		
		for(int i = 0; i < rankList.size(); i++) {
			if(rankList.get(i).getScore() < mainInfo.getScore()) {
				rankList.add(i, new RankInfo(mainInfo.getName(), mainInfo.getScore()));
				if(rankList.size() > 10) rankList.remove(10);
				return;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		TextParameter.setFont(g2, TextParameter.RANKING_FONT_SIZE);
		
		String name;
		String score;

		int x, y;
		
		g2.setColor(Color.white);
		
		if(rankList.isEmpty()) {
			g2.drawString("EMPTY", TextParameter.getXForCenteredText(g2, "VAZIO"), TextParameter.STRING_PANEL_Y_BEGINNING + TextParameter.getStringHeight(g2, "EMPTY"));
		}
		
		for(int i = 0; i < rankList.size(); i++) {
			name = rankList.get(i).getName();
			score = String.valueOf(rankList.get(i).getScore());
			
			y = ((i + 1) * TextParameter.RANKING_FONT_SIZE) + TextParameter.STRING_PANEL_Y_BEGINNING;
			x = TextParameter.getXForCenteredText(g2, ":");
			
			g2.drawString(":", x, y);
			g2.drawString(name, x - TextParameter.getStringWidth(g2, name), y);
			g2.drawString(score, x + TextParameter.getStringWidth(g2, ":"), y);
			
		}
	}
	
	public boolean isEligible() {
		if(rankList.size() < 10) {
			return true;
		}
		
		for(int i = 0; i < rankList.size(); i++) {
			if(mainInfo.getScore() > rankList.get(i).getScore()) {
				return true;
			}
		}
		
		return false;
	}
	
	public void save() {
		file.rewriteFile(DIRECTORY, rankList);
	}
	
	public void reset() {
		rankList.clear();
		file.deleteFile(DIRECTORY);
	}
	
	public ArrayList<RankInfo> getRankList() {
		return rankList;
	}
}
