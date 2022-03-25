package game;

import java.awt.Graphics2D;

public class Game {
	private static Game instance;
	
	private MainCharacter mainCharacter;
	
	private Game() {
		mainCharacter = MainCharacter.getInstance();
	}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		
		return instance;
	}
	
	public void gameUpdate(double delta) {
		mainCharacter.move(delta);
	}
	
	public void gamePaint(Graphics2D g2) {
		mainCharacter.draw(g2);
	}
}
