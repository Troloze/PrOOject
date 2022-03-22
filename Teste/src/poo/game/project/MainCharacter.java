package poo.game.project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MainCharacter extends Entity {
	
	private final int PLAYER_SPEED = 500;
	
	Sprite sprite;
	InputHandler input;
	
	public MainCharacter(MyPanel panel) {
		
		input = InputHandler.getInstance();
		position = new Point2D.Double();
		
		defaultPlayerStatus();
		getPlayerImage();
		
	}
	
	public void defaultPlayerStatus() {
		
		position.setLocation(300, 300);
		
	}
	
	public void getPlayerImage() {
		
		sprite = new Sprite();
		Image image = new ImageIcon("C:\\Users\\tiago\\git\\PrOOject\\Teste\\sprites\\player\\sprite.png").getImage();
		sprite.sprite = image;
		
		/*try {
			sprite.sprite = ImageIO.read(getClass().getResourceAsStream("/player/knight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	public void update(double delta) {
		
		move(delta);
		
	}
	
	public void draw(Graphics2D g2) {
		
		g2.drawImage(sprite.sprite, (int) position.getX(), (int) position.getY(), 300, 300, null);
		
		g2.dispose();
		
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