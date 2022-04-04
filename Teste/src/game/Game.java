package game;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

public final class Game {
	private static Game instance;
	private static EntityInstancer eI;
	private List<Entity> entities;
		
	private boolean test = true;
	
	private Game() {
		entities = new ArrayList<>();
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
	}
	
	public void addEntity(Entity ent) {
		if (ent == null) return;
		entities.add(ent);
	}
}
