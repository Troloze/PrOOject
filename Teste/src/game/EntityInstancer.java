package game;

import game.bullets.PlayerBullet;
import game.enemies.Enemy;
import misc.InstanceParams;

public final class EntityInstancer {
	
	public static final int ENT_PLAYER = 0;
	public static final int ENT_PLAYER_BULLET = 1;
	public static final int ENT_TEST_ENEMY = 2;
	
	private static EntityInstancer instance;
	private static Game game;
		
	private EntityInstancer () {
		game = Game.getInstance();
	}
	
	public static EntityInstancer getInstance() {
		if (instance == null) {
			instance = new EntityInstancer();
		}
		return instance;
	}
	
	public void instance(int type, InstanceParams params) {
		Entity ent = null;
		switch (type) {
			case ENT_PLAYER:
				ent = Player.newInstance();
			break;
			case ENT_PLAYER_BULLET:
				ent = PlayerBullet.newInstance(params);
			break;
			case ENT_TEST_ENEMY:
				ent = Enemy.newInstance(params);
			break;
				
		}
		game.addEntity(ent);
	}
}
