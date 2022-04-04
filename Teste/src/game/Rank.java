package game;

import java.util.ArrayList;

import engine.FileHandler;

public class Rank {
	private static Rank instance;
	
	private FileHandler file;
	
	private ArrayList<Rank> rankList;
	private String name;
	private int score;
	
	private Rank() {
		rankList = new ArrayList<Rank>();
		file = FileHandler.getInstance();
		
		ArrayList<String> fileStr = file.readFile("rank.txt");
		String[] str;
		
		for(int i = 0; i < fileStr.size(); i++) {
			str = fileStr.get(i).split(":");
			rankList.add(new Rank(str[0], Integer.parseInt(str[1])));
		}
	}
	
	private Rank(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public static Rank getInstance() {
		if(instance == null) {
			instance = new Rank();
		}
		
		return instance;
	}
	
	public void sortRankList() {
		if(rankList.isEmpty() || rankList.size() < 10) {
			rankList.add(new Rank(getName(), getScore()));
			return;
		}
		
		for(int i = 0; i < rankList.size(); i++) {
			if(rankList.get(i).getScore() < getScore()) {
				rankList.add(i, new Rank(getName(), getScore()));
				if(rankList.size() > 10) rankList.remove(10);
				return;
			}
		}
	}
	
	public String toString() {
		return name + ":" + score;
	}
	
	public ArrayList<Rank> getRankList() {
		return rankList;
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
