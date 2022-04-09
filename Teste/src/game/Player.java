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
	public static final Point2D DEFAULT_POS = new Point2D.Double(0, 200);
	public static final int PLAYER_SPEED = 400;
	private static InputHandler input;
	
	private int blinkTime;
	private double downTime = 1.0;
	private double shootCooldown;
	private double hitCooldown = 0.0;
	private boolean isFocus = false;
	private boolean isFast = false;
	
	private Entity hitbox;
	
	private int hp = 0;
	private final int hpMax = 3;
	
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
		
		this.hp = hpMax;
	}

	public void setHitbox(Entity hitbox) {
		this.hitbox = hitbox;
	}
	
	@Override
	public void update(double delta) {		
		if (destroyed) return;
		double rate = 1;
		if (hitCooldown > 0) {
			hitCooldown -= delta;
			blinkTime++;
			if (blinkTime == 5) {
				sprite.setAlpha(0.0f);
			}
			if (blinkTime == 10) {
				sprite.setAlpha(1.0f);
				blinkTime = 0;
			}
			if (hitCooldown < 0)  {
				sprite.setAlpha(1.0f);
				blinkTime = 0;
				hitCooldown = 0;
			}
		}
		
		isFocus = input.getInput(InputHandler.KEY_FOCUS) == 1;
		isFast = input.getInput(InputHandler.KEY_BOMB) == 1;
		if (isFocus && !isFast) {
			rate = 0.4;
			if (hitbox != null) ((PlayerHitbox) hitbox).show();
		}
		if (!isFocus && isFast) {
			rate = 2.0;
			if (hitbox != null) ((PlayerHitbox) hitbox).hide();
		}
		if (isFocus == isFast) {
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
		
		Game.getInstance().setPPos(transform.getPosition());
		
		((PlayerHitbox) hitbox).move();
		
		if(shootCooldown < System.nanoTime()/1000000000.0) {
			shootCooldown += 0.08;
			if(input.getInput(InputHandler.KEY_SHOOT) == 1) {
				InstanceParams par = new InstanceParams();
				par.transform = new Transform(transform);
				par.transform.getPosition().setLocation(
						transform.getPosition().getX() + 
						((isFocus == isFast) ? -40 : ((isFocus) ? -20 : -50)), 
						transform.getPosition().getY());
				par.transform.getDefaultScale().setLocation(25, 25);
				par.transform.setScale(1);
				par.transform.setZPosition(10);
				par.direction = isFocus ? 90 : 85;
				par.direction = (isFocus == isFast) ? 85 : ((isFocus) ? 90 : 70);
				
				
				
				EntityInstancer.instance(EntityInstancer.ENT_PLAYER_BULLET, par);
				
				par.direction = (isFocus == isFast) ? 95 : ((isFocus) ? 90 : 110);
				par.transform.getPosition().setLocation(
						transform.getPosition().getX() + 
						((isFocus == isFast) ? 40 : ((isFocus) ? 20 : 50)), 
						transform.getPosition().getY());
				EntityInstancer.instance(EntityInstancer.ENT_PLAYER_BULLET, par);
				
				par.direction = 90;
				par.transform.getPosition().setLocation(
						transform.getPosition().getX(), 
						transform.getPosition().getY() + 
						((isFocus == isFast) ? -10 : ((isFocus) ? -20 : 0)));
				EntityInstancer.instance(EntityInstancer.ENT_PLAYER_BULLET, par);
			}
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
		if (hitCooldown > 0.0) return;
		hp -= 1;
		if (hp == 0) {
			destroy();
			GameStateHandler.setState(GameStateHandler.STATE_LOSE);
		}
		hitCooldown = downTime;
	}

	@Override
	public double getDamage() {
		return 0;
	}

	
}
