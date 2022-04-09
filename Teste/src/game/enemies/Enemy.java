package game.enemies;

import engine.ImageBufferHandler;
import engine.SpriteHolder;
import game.Collider;
import game.Collisionable;
import game.Game;
import game.Hazard;
import game.Sprite;
import game.patterns.GenericEnemyPattern;
import misc.InstanceParams;
import misc.PatternParams;
import misc.Transform;

public class Enemy extends Hazard implements SpriteHolder {

	private int t = 0;
	private Sprite spr;
	private double value;
	private PatternParams patPar;
	
	
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
		this.value = instPar.value;
		this.spr = new Sprite(this);
		this.spr.set(instPar.spriteData.type, instPar.spriteData.color);
		this.pattern = GenericEnemyPattern.getInstance();
		this.patPar = instPar.patPar;
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
		if (!alive) Game.addScore(value);
	}

	@Override
	public void update(double delta) {
		t += 1;
		pattern.setArgument("T", t);
		for (int i = 0; i < patPar.n; i++) pattern.setArgument(patPar.keys[i], patPar.arguments[i]);
		baseUpdate(delta);
		transform.setRotation(transform.getRotation() + 200 * delta);
		
		if (Math.abs(transform.getPosition().getX()) > 500) destroy();
		if (Math.abs(transform.getPosition().getY()) > 400) destroy();
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
