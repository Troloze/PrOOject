package game;

import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private static Game instance;
	
	private MainCharacter mainCharacter;
	
	private List<Entity> entities;
	
	private Game() {
		entities = new ArrayList<>();
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
		entities.forEach(ent -> {ent.update();});
	}
	
	public void gamePaint(Graphics2D g2) {
		mainCharacter.draw(g2);
	}
}
