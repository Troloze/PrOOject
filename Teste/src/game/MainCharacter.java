package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import engine.FileHandler;
import engine.InputHandler;
import engine.MyPanel;
import misc.Misc;

public class MainCharacter extends Entity {
	
	private static MainCharacter instance;
	
	private final int PLAYER_SPEED = 500;
	
	Image sprite;
	FileHandler fileHandler;
	InputHandler input;
	
	private MainCharacter() {
		
		fileHandler = FileHandler.getInstance();
		input = InputHandler.getInstance();
		position = new Point2D.Double();
		
		defaultPlayerStatus();
		getPlayerImage();
		
	}
	
	public static MainCharacter getInstance() {
		if(instance == null) {
			instance = new MainCharacter();
		}
		
		return instance;
	}
	
	public void defaultPlayerStatus() {
		
		position.setLocation(MyPanel.PANEL_WIDTH/2, MyPanel.PANEL_HEIGHT/2);
		
	}
	
	public void getPlayerImage() {
		
		sprite = fileHandler.openImage("sprites/player/MQ.png");
		
	}
	
	public void update(double delta) {
		
		move(delta);
		
	}
	
	public void draw(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		Point2D position2 = new Point2D.Double();
		Point2D scale = new Point2D.Double();
		double v;
		Point2D rotation = new Point2D.Double();
		
		/*for (int i = 0; i < 10000; i++) {
			position2.setLocation(Math.random() * 900, Math.random() * 600);
			scale.setLocation(15.0/1500.0 * (1 + Math.random()), 13.0/1301.0 * (1 + Math.random()));
			v = Math.random() * 2 * Math.PI;
			rotation.setLocation(Math.cos(v), Math.sin(v));
			AffineTransform at = new AffineTransform(
					scale.getX() * rotation.getX(),
					scale.getX() * rotation.getY(),
					scale.getY() * (-rotation.getY()),
					scale.getY() * rotation.getX(),
					scale.getX() * rotation.getX() * 750.0 - scale.getY() * rotation.getY() * 1301.0 * 2 / 3.0 + position2.getX(),
					scale.getX() * rotation.getY() * 750.0 + scale.getY() * rotation.getX() * 1301.0 * 2 / 3.0 + position2.getY());
			
			g2.drawImage(sprite, at, null);
				
		}/**/
		g2.drawImage(sprite, (int) position.getX(), (int) position.getY(), (int) Misc.Background.TILE_WIDTH, (int) Misc.Background.TILE_HEIGHT, null);
		
	}
	
	public void move(double delta) {
		
		if(input.getInput(InputHandler.KEY_UP) == 1) {
			position.setLocation(position.getX(), position.getY() - (int) (PLAYER_SPEED * delta));
		}
		
		if(input.getInput(InputHandler.KEY_DOWN) == 1) {
			position.setLocation(position.getX(), position.getY() + (int) (PLAYER_SPEED * delta));
		}
		
		if(input.getInput(InputHandler.KEY_LEFT) == 1) {
			position.setLocation(position.getX() - (int) (PLAYER_SPEED * delta), position.getY());
		}
		
		if(input.getInput(InputHandler.KEY_RIGHT) == 1) {
			position.setLocation(position.getX() + (int) (PLAYER_SPEED * delta), position.getY());
		}	
	}

	@Override
	public void update() {}

	@Override
	public void destroy() {}

	@Override
	public Entity newInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
}