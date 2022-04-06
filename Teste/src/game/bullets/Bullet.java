package game.bullets;

import engine.SpriteHolder;
import game.Collisionable;
import game.Hazard;
import game.Sprite;
import misc.Transform;

public abstract class Bullet extends Hazard implements SpriteHolder {

	private Sprite sprite;
	
	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCollision(Collisionable entity) {
		// TODO Auto-generated method stub

	}
}
