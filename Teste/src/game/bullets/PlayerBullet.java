package game.bullets;

import engine.ImageBufferHandler;
import game.Collider;
import game.Collisionable;
import game.Sprite;
import misc.InstanceParams;
import misc.Transform;

public class PlayerBullet extends Bullet {

	private Sprite spr;
	
	public static PlayerBullet newInstance(InstanceParams params) {
		return new PlayerBullet(params);
	}
	
	private PlayerBullet(InstanceParams params) {
		super(game.patterns.GenericBulletPattern.getInstance());
		
		this.collider.setDamageFlags(Collider.FLAG_PLAYER_BULLET);
		this.collider.setHitFlags(Collider.FLAG_ENEMY);
		this.collider.toggleHazard(true);
		this.collider.toggleTarget(true);
		this.collider.setHitbox(20);
		this.collider.setDamagebox(20);
		
		
		
		this.damage = 1;
		this.direction = 180 + params.direction;
		this.speed = 1000;
		this.lifeTime = 1.5;
		this.transform = new Transform(params.transform);
		if (this.transform == null) {
			destroy();
			return;
		}
		this.transform.setRotation(params.direction - 90);
		this.spr = new Sprite(this);
		this.spr.set(ImageBufferHandler.HALF, ImageBufferHandler.B_BLUE);
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
			pattern.setArgument("Scale", 1);
			pattern.setArgument("Start", 1);
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
