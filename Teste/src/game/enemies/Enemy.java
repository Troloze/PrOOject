package game.enemies;

import engine.SpriteHolder;
import game.Collider;
import game.Collisionable;
import game.Hazard;
import game.Sprite;
import game.patterns.TestEnemyPattern;
import misc.InstanceParams;
import misc.Transform;

public class Enemy extends Hazard implements SpriteHolder {

	private Sprite spr;
	
	public Enemy(InstanceParams instPar) {
		super();
		this.alpha = instPar.spriteData.alpha;
		
		updateInstanceData(instPar);
		//this.transform = new Transform(instPar.transform);
		this.collider.setDamageFlags(Collider.FLAG_ENEMY);
		this.collider.setHitFlags(Collider.FLAG_PLAYER | Collider.FLAG_PLAYER_BULLET);
		this.collider.toggleHazard(true);
		this.collider.toggleTarget(true);
		this.collider.setHitbox(43);
		this.collider.setDamagebox(10);
		this.life = 1;
		this.spr = new Sprite(this);
		this.pattern = TestEnemyPattern.getInstance();
		this.spr.set(instPar.spriteData.type, instPar.spriteData.color);
	}


	public static Enemy newInstance(InstanceParams instPar) {
		return new Enemy(instPar);
	}
	
	
	@Override
	public Transform getTransform() {
		return transform;
	}

	@Override
	public void onCollision(Collisionable collider) {
		hit(2);
	}

	@Override
	public void update(double delta) {
		baseUpdate(delta);
		transform.setRotation(transform.getRotation() + 200 * delta);
	}

	@Override
	public void destroy() {
		baseDestroy();
		if (spr != null) spr.destroy();
		spr = null;
		destroyed = true;
	}
}
