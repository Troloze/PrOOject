package poo.game.project;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class MainCharacter extends Entity {
	
	private final int PLAYER_SPEED = 500;
	
	Sprite sprite;
	FileHandler fileHandler;
	InputHandler input;
	
	public MainCharacter(MyPanel panel) {
		
		sprite = new Sprite();
		fileHandler = FileHandler.getInstance();
		input = InputHandler.getInstance();
		position = new Point2D.Double();
		
		defaultPlayerStatus();
		getPlayerImage();
		
	}
	
	public void defaultPlayerStatus() {
		
		position.setLocation(300, 300);
		
	}
	
	public void getPlayerImage() {
		sprite.entityImage = fileHandler.openImage("C:/Users/tiago/git/PrOOject/Teste/sprites/player/sprite.png");
		/*sprite = new Sprite();
		Image image = new ImageIcon("C:/Users/tiago/git/PrOOject/Teste/sprites/player/sprite.png").getImage();
		sprite.entityImage = image;*/
	}
	
	public void update(double delta) {
		
		move(delta);
		
	}
	
	public void draw(Graphics2D g2) {
		
		g2.drawImage(sprite.entityImage, (int) position.getX(), (int) position.getY(), 300, 300, null);
		
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