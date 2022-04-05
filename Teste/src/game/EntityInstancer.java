package game;

import misc.InstanceParams;

public final class EntityInstancer {
	
	public static final int ENT_PLAYER = 0;
	public static final int ENT_TEST = -1;
	
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
			case ENT_TEST:
				ent = TestEntity.newInstance(params.rotation, params.position, params.scale);
		}
		game.addEntity(ent);
	}
}