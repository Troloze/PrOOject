package game;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import engine.InputHandler;

public final class Game {
	private static Game instance;
	private static EntityInstancer eI;
	
	private InputHandler input;
	private GameStateHandler stateHandler;
	
	private List<Entity> entities;
		
	private boolean test = true;
	
	private Game() {
		input = InputHandler.getInstance();
		stateHandler = GameStateHandler.getInstance();
		
		entities = new ArrayList<>();
	}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
			eI = EntityInstancer.getInstance();
		}
		
		return instance;
	}
	
	public void gameUpdate(double delta) {
		
		if(stateHandler.getState() == GameStateHandler.STATE_PLAYING) {
			if(input.getInput(InputHandler.KEY_PAUSE) == 0) stateHandler.setState(GameStateHandler.STATE_PAUSED);
			if (test) {
				InstanceParams iP = new InstanceParams();
				eI.instance(EntityInstancer.ENT_PLAYER, iP);
				test = false;
				for (int i = 0; i < 300; i++) {
					iP.rotation = 0;
					iP.position = new Point2D.Double(-300 + Math.random() * 600, -200 + Math.random() * 400);
					iP.scale = new Point2D.Double(50 , 43);

					eI.instance(EntityInstancer.ENT_TEST, iP);
				}
			}
			
			entities.forEach(ent -> {ent.update(delta);});
		} else {
			stateHandler.updateState();
		}
	}
	
	public void addEntity(Entity ent) {
		if (ent == null) return;
		entities.add(ent);
	}
}
