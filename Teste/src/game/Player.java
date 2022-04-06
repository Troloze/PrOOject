package game;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import engine.ImageBufferHandler;
import engine.InputHandler;
import misc.Transform;

public class Player extends Entity implements Collisionable{

	private Collider collider;
	public Sprite sprite;
	public static final Point2D DEFAULT_POS = new Point2D.Double(0, 0);
	public static final int PLAYER_SPEED = 400;
	private static InputHandler input;
	
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
		
		this.transform.position.setLocation(DEFAULT_POS.getX(), DEFAULT_POS.getY());
		this.transform.zPosition = 10;
		this.destroyed = false;
	}

	@Override
	public void update(double delta) {		
		if (destroyed) return;
		double rate = 1;
		if (input.getInput(InputHandler.KEY_FOCUS) == 1) {
			rate = 0.4;
		}
		
		if(input.getInput(InputHandler.KEY_UP) == 1) {
			transform.position.setLocation(transform.position.getX(), transform.position.getY() - (PLAYER_SPEED * delta * rate));
		}
		
		if(input.getInput(InputHandler.KEY_DOWN) == 1) {
			transform.position.setLocation(transform.position.getX(), transform.position.getY() + (PLAYER_SPEED * delta * rate));
		}
		
		if(input.getInput(InputHandler.KEY_LEFT) == 1) {
			transform.position.setLocation(transform.position.getX() - (PLAYER_SPEED * delta * rate), transform.position.getY());
		}
		
		if(input.getInput(InputHandler.KEY_RIGHT) == 1) {
			transform.position.setLocation(transform.position.getX() + (PLAYER_SPEED * delta * rate), transform.position.getY());
		}
		
		
		
	}

	@Override
	public Transform getTransform() {
		return transform;
	}

	@Override
	public void destroy() {
		if (!sprite.isDestroyed()) sprite.destroy();
		sprite = null;
		destroyed = true;
	}

	@Override
	public Collider getCollider() {
		
		return collider;
	}

	@Override
	public void onCollision(Collisionable collider) {
		System.out.println("Hm");
		collider.destroy();
		
	}
}
