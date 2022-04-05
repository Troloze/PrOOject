package game;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import engine.InputHandler;
import misc.InstanceParams;

public final class Game {
	private static Game instance;
	private static EntityInstancer eI;
	private List<Entity> entities;
	private List<Entity> destroyQueue;
		
	private boolean test = true;
	
	private Game() {
		entities = new ArrayList<>();
		destroyQueue = new ArrayList<>();
		//mainCharacter = MainCharacter.getInstance();
	}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
			eI = EntityInstancer.getInstance();
		}
		
		return instance;
	}
	
	public void gameUpdate(double delta) {
		InstanceParams iP = new InstanceParams();
		if (test) {
			eI.instance(EntityInstancer.ENT_PLAYER, iP);
			test = false;
			for (int i = 0; i < 300; i++) {
				iP.rotation = 0;
				iP.position = new Point2D.Double(-300 + Math.random() * 600, -200 + Math.random() * 400);
				iP.scale = new Point2D.Double(50 , 43);

				eI.instance(EntityInstancer.ENT_TEST, iP);
			}
		}
		
		if (InputHandler.getInstance().getInput(InputHandler.KEY_FOCUS) == 0) {
			for (int i = 0; i < 300; i++) {
				iP.rotation = 0;
				iP.position = new Point2D.Double(-300 + Math.random() * 600, -200 + Math.random() * 400);
				iP.scale = new Point2D.Double(50 , 43);

				eI.instance(EntityInstancer.ENT_TEST, iP);
			}
		}
		
		for (Entity ent : entities) {
			if (ent.isDestroyed()) {
				addToDestroy(ent);
				continue;
			}
			ent.update(delta);	
		}
		
		
	}
	
	public void addEntity(Entity ent) {
		if (ent == null) return;
		entities.add(ent);
	}
	
	public void addToDestroy(Entity ent) {
		destroyQueue.add(ent);
	}
	
	public void destroy() {
		for (Entity ent : destroyQueue) {
			if (ent == null) continue;
			entities.remove(ent);
		}
		destroyQueue.clear();
	}
}
