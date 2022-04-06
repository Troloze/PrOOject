package game;

import misc.Transform;

public interface Collisionable {

	public abstract Collider getCollider();
	public abstract void onCollision(Collisionable collider);
	public abstract Transform getTransform();
	public abstract void destroy();
}
