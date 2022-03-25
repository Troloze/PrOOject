package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import engine.FileHandler;
import engine.InputHandler;
import engine.MyPanel;

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
		
		position.setLocation(300, 300);
		
	}
	
	public void getPlayerImage() {
		
		sprite = fileHandler.openImage("sprites/player/HQ.png");
		
	}
	
	public void update(double delta) {
		
		move(delta);
		
	}
	
	public void draw(Graphics2D g2) {
		
		g2.drawImage(sprite, (int) position.getX(), (int) position.getY(), 50, 50, null);
		
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
	
}