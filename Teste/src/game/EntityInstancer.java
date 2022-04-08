package game;

import game.bullets.GenericBullet;
import game.bullets.PlayerBullet;
import game.enemies.Enemy;
import misc.InstanceParams;

public final class EntityInstancer {
	
	public static final int ENT_PLAYER = 0;
	public static final int ENT_PLAYER_BULLET = 1;
	public static final int ENT_TEST_ENEMY = 2;
	public static final int ENT_GENERIC_BULLET = 3;
	
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
	
	public static void instance(int type, InstanceParams params) {
		getInstance();
		Entity ent = null;
		switch (type) {
			case ENT_PLAYER:
				Entity hb;
				ent = Player.newInstance();
				hb = PlayerHitbox.newInstance(ent);
				((Player) ent).setHitbox(hb);
				game.addEntity(hb);
			break;
			case ENT_PLAYER_BULLET:
				ent = PlayerBullet.newInstance(params);
			break;
			case ENT_TEST_ENEMY:
				ent = Enemy.newInstance(params);
			break;
			case ENT_GENERIC_BULLET: 
				ent = GenericBullet.newInstance(params);
			break;
				
		}
		game.addEntity(ent);
	}
}
