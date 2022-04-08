package game.bullets;

import engine.ImageBufferHandler;
import game.Collider;
import game.Collisionable;
import game.Sprite;
import misc.InstanceParams;
import misc.Transform;

public class GenericBullet extends Bullet {

private Sprite spr;
	
	public static GenericBullet newInstance(InstanceParams params) {
		return new GenericBullet(params);
	}
	
	private GenericBullet(InstanceParams params) {
		super(game.patterns.GenericBulletPattern.getInstance());
		
		this.collider.setDamageFlags(Collider.FLAG_ENEMY_BULLET);
		this.collider.setHitFlags(Collider.FLAG_PLAYER);
		this.collider.toggleHazard(true);
		this.collider.toggleTarget(true);
		this.collider.setHitbox(15);
		this.collider.setDamagebox(15);
		
		this.damage = 1;
		this.direction = params.direction;
		this.speed = params.speed;
		this.lifeTime = 10;
		this.transform = new Transform(params.transform);
		if (this.transform == null) {
			destroy();
			return;
		}
		this.transform.setRotation(params.direction + 90);
		this.transform.getDefaultScale().setLocation(50, 50);
		this.spr = new Sprite(this);
		this.spr.set(ImageBufferHandler.ARROW, ImageBufferHandler.B_BLUE);
		this.spr.setAlpha(0f);
	}
	
	@Override
	public Transform getTransform() {
		return this.transform;
	}

	@Override
	public void onCollision(Collisionable collider) {
		pattern.onDeath(this);
		destroy();
	}

	@Override
	public void update(double delta) {
		if (pattern != null) {
			pattern.setArgument("Scale", 0.4);
			pattern.setArgument("Start", 0.5);
		}
		baseUpdate(delta);
		if (this.spr != null) this.spr.setAlpha((float) this.alpha);
	}

	@Override
	public void destroy() {
		if (destroyed) return;
		baseDestroy();
		if (spr != null) spr.destroy();
		spr = null;
		destroyed = true;
	}

}
