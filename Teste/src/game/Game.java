package game;

import java.awt.Graphics2D;

import engine.InputHandler;

public class Game {
	private static Game instance;
	
	private InputHandler input;
	private MainCharacter mainCharacter;
	private GameStateHandler stateHandler;
	
	private Game() {
		input = InputHandler.getInstance();
		stateHandler = GameStateHandler.getInstance();
		mainCharacter = MainCharacter.getInstance();
	}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		
		return instance;
	}
	
	public void gameUpdate(double delta) {
		if(stateHandler.getState() == GameStateHandler.STATE_PLAYING) {
			if(input.getInput(InputHandler.KEY_PAUSE) == 0) stateHandler.setState(GameStateHandler.STATE_PAUSED);
			mainCharacter.move(delta);
		} else {
			stateHandler.updateState();
		}
	}
	
	public void gamePaint(Graphics2D g2) {
		if(stateHandler.getState() == GameStateHandler.STATE_PLAYING || stateHandler.getState() == GameStateHandler.STATE_PAUSED) {
			mainCharacter.draw(g2);
		}
		
		if(stateHandler.getState() != GameStateHandler.STATE_PLAYING) {
			stateHandler.draw(g2);
		}
		
		g2.dispose();
	}
}
