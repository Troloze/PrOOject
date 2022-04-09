package game.enemies;

import engine.ImageBufferHandler;
import engine.SpriteHolder;
import game.Collider;
import game.Collisionable;
import game.Game;
import game.Hazard;
import game.Sprite;
import game.patterns.TestEnemyPattern;
import misc.InstanceParams;
import misc.Transform;

public class Enemy extends Hazard implements SpriteHolder {

	private int t = 0;
	private Sprite spr;
	private double value;
	
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
		//this.speed = 200;
		this.value = instPar.value;
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
		hit(collider.getDamage());
	}

	@Override
	public void update(double delta) {
		pattern.setArgument("T", t++);
		pattern.setArgument("Sleep", 10);
		pattern.setArgument("Amount", 16);
		pattern.setArgument("Spread", 360.0);
		pattern.setArgument("Direction", 0);
		//pattern.setArgument("Speed", 100.0);
		InstanceParams par = new InstanceParams();
		par.speed = 500;
		par.rotationSpeed = 50;
		par.rotationAcceleration = 1;
		par.acceleration = 20;
		par.transform.setZPosition(10.01);
		par.spriteData.type = ImageBufferHandler.HOLLOW;
		par.spriteData.color = (int) (Math.random() * 7);
		par.lifeTime = 10;
		par.transform = transform;
		pattern.setArgument("InstParams", par);
		baseUpdate(delta);
		transform.setRotation(transform.getRotation() + 200 * delta);
	}

	@Override
	public void destroy() {
		if (destroyed) return;
		baseDestroy();
		Game.addScore(value);
		if (spr != null) spr.destroy();
		spr = null;
		destroyed = true;
	}
}
