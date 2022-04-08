package game;

import java.awt.geom.Point2D;

import engine.ImageBufferHandler;
import engine.InputHandler;
import game.patterns.TestEnemyPattern;
import misc.InstanceParams;
import misc.Transform;

public class Player extends Entity implements Collisionable{

	private Collider collider;
	public Sprite sprite;
	public static final Point2D DEFAULT_POS = new Point2D.Double(0, 0);
	public static final int PLAYER_SPEED = 400;
	private static InputHandler input;
	
	private double shootCooldown;
	private boolean isFocus = false;
	
	private Entity hitbox;
	
	public static Entity newInstance() {
		return new Player();
	}
	
	private Player() {
		this.transform = new Transform(null, new Point2D.Double(50.0, 43.0), 1, 0 , 10.0);
		if (input == null) input = InputHandler.getInstance();
		this.sprite = new Sprite(this);
		this.collider = new Collider();
		this.collider.setDamageFlags(Collider.FLAG_PLAYER);
		this.collider.setHitFlags(0);
		this.collider.toggleHazard(true);
		this.collider.toggleTarget(false);
		this.collider.setHitbox(10);
		this.collider.setDamagebox(0);
		
		this.sprite.set(ImageBufferHandler.TRIANGLE, ImageBufferHandler.T_BLUE1);
		
		this.transform.getPosition().setLocation(DEFAULT_POS.getX(), DEFAULT_POS.getY());
		this.transform.setZPosition(10);

		this.destroyed = false;
		this.shootCooldown = (System.nanoTime()/1000000000.0) + 0.1;

	}

	public void setHitbox(Entity hitbox) {
		this.hitbox = hitbox;
	}
	
	@Override
	public void update(double delta) {		
		if (destroyed) return;
		double rate = 1;
		if (input.getInput(InputHandler.KEY_FOCUS) == 1) {
			rate = 0.4;
			isFocus = true;
			if (hitbox != null) ((PlayerHitbox) hitbox).show();
		}
		else {
			isFocus = false;
			if (hitbox != null) ((PlayerHitbox) hitbox).hide();
		}
		
		if(input.getInput(InputHandler.KEY_UP) == 1) {
			transform.getPosition().setLocation(transform.getPosition().getX(), transform.getPosition().getY() - (PLAYER_SPEED * delta * rate));
		}
		
		if(input.getInput(InputHandler.KEY_DOWN) == 1) {
			transform.getPosition().setLocation(transform.getPosition().getX(), transform.getPosition().getY() + (PLAYER_SPEED * delta * rate));
		}
		
		if(input.getInput(InputHandler.KEY_LEFT) == 1) {
			transform.getPosition().setLocation(transform.getPosition().getX() - (PLAYER_SPEED * delta * rate), transform.getPosition().getY());
		}
		
		if(input.getInput(InputHandler.KEY_RIGHT) == 1) {
			transform.getPosition().setLocation(transform.getPosition().getX() + (PLAYER_SPEED * delta * rate), transform.getPosition().getY());
		}
		
		if (transform.getPosition().getX() < -455) {
			transform.getPosition().setLocation(-455, transform.getPosition().getY());
		}
		
		if (transform.getPosition().getX() > 455) {
			transform.getPosition().setLocation(455, transform.getPosition().getY());
		}
		
		if (transform.getPosition().getY() < -330) {
			transform.getPosition().setLocation(transform.getPosition().getX(), -330);
		}
		
		if (transform.getPosition().getY() > 345) {
			transform.getPosition().setLocation(transform.getPosition().getX(), 345);
		}
		
		((PlayerHitbox) hitbox).move();
		
		if(shootCooldown < System.nanoTime()/1000000000.0) {
			shootCooldown += 0.08;
			if(input.getInput(InputHandler.KEY_SHOOT) == 1) {
				InstanceParams par = new InstanceParams();
				par.transform = new Transform(transform);
				par.transform.getPosition().setLocation(
						transform.getPosition().getX() + ((isFocus) ? -20 : -40), 
						transform.getPosition().getY());
				par.transform.getDefaultScale().setLocation(25, 25);
				par.transform.setScale(1);
				par.transform.setZPosition(10);
				par.direction = (isFocus) ? 90 : 85;
				
				
				
				EntityInstancer.instance(EntityInstancer.ENT_PLAYER_BULLET, par);
				
				par.direction = isFocus ? 90 : 95;
				par.transform.getPosition().setLocation(
						transform.getPosition().getX() + ((isFocus) ? 20 : 40), 
						transform.getPosition().getY());
				EntityInstancer.instance(EntityInstancer.ENT_PLAYER_BULLET, par);
				
				par.direction = 90;
				par.transform.getPosition().setLocation(
						transform.getPosition().getX(), 
						transform.getPosition().getY() + ((isFocus) ? -20 : 0));
				EntityInstancer.instance(EntityInstancer.ENT_PLAYER_BULLET, par);
			}
		}
		
		if (input.getInput(InputHandler.KEY_PAUSE) == 0) {
			InstanceParams par = new InstanceParams();

			par.transform = new Transform(transform);
			par.transform.getPosition().setLocation(-500, -100);
			par.transform.getDefaultScale().setLocation(50, 43);
			par.transform.setScale(1);
			par.transform.setZPosition(10);
			par.pattern = TestEnemyPattern.getInstance();
			par.spriteData.alpha = 1.0f;
			par.spriteData.type = ImageBufferHandler.TRIANGLE;
			par.spriteData.color = ImageBufferHandler.T_ORANGE1;
			par.lifeTime = 10.0;
			par.speed = 100;
			EntityInstancer.instance(EntityInstancer.ENT_TEST_ENEMY, par);
		}
		
	}

	@Override
	public Transform getTransform() {
		return transform;
	}

	@Override
	public void destroy() {
		if (destroyed) return;
		if (sprite != null) sprite.destroy();
		if (hitbox != null) hitbox.destroy();
		sprite = null;
		destroyed = true;
	}

	@Override
	public Collider getCollider() {
		
		return collider;
	}

	@Override
	public void onCollision(Collisionable collider) {
		//collider.destroy();
		System.out.println("Test");
	}

	@Override
	public double getDamage() {
		return 0;
	}

	
}
