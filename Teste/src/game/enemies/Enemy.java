package game.enemies;

import game.Cluster;
import game.Collisionable;
import game.Hazard;
import misc.Transform;

public abstract class Enemy extends Hazard {

	private Cluster cluster;
	
	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCollision(Collisionable collider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
