package game;

import java.awt.geom.Point2D;

import engine.ImageBufferHandler;
import engine.InputHandler;

public class Player extends Entity{

	public Sprite sprite;
	public static final Point2D DEFAULT_POS = new Point2D.Double(0, 0);
	public static final int PLAYER_SPEED = 400;
	private static InputHandler input;
	
	public Point2D scale;
	
	public static Entity newInstance() {
		
		return new Player();
	}
	
	private Player() {
		this.position = new Point2D.Double();
		if (input == null) input = InputHandler.getInstance();
		this.sprite = new Sprite(this);
		scale = new Point2D.Double(50.0, 43.0);
		this.zPosition = 10.0;
		sprite.set(ImageBufferHandler.TRIANGLE, ImageBufferHandler.T_LIGHT1);
		
		
		
		this.position.setLocation(DEFAULT_POS.getX(), DEFAULT_POS.getY());
	}

	@Override
	public void update(double delta) {		
		double rate = 1;
		if (input.getInput(InputHandler.KEY_FOCUS) == 1) {
			rate = 0.4;
		}
		
		if(input.getInput(InputHandler.KEY_UP) == 1) {
			position.setLocation(position.getX(), position.getY() - (PLAYER_SPEED * delta * rate));
		}
		
		if(input.getInput(InputHandler.KEY_DOWN) == 1) {
			position.setLocation(position.getX(), position.getY() + (PLAYER_SPEED * delta * rate));
		}
		
		if(input.getInput(InputHandler.KEY_LEFT) == 1) {
			position.setLocation(position.getX() - (PLAYER_SPEED * delta * rate), position.getY());
		}
		
		if(input.getInput(InputHandler.KEY_RIGHT) == 1) {
			position.setLocation(position.getX() + (PLAYER_SPEED * delta * rate), position.getY());
		}
		
		
		
	}

	@Override
	public Point2D getScale() {
		return scale;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public double getRotation() {
		return 0.0;
	}
	
	@Override
	public void destroy() {}

	@Override
	public double getZPos() {
		// TODO Auto-generated method stub
		return zPosition;
	}

	
	

}
