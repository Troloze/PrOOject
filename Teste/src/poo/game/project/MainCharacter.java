package poo.game.project;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MainCharacter extends JLabel{

	private static final long serialVersionUID = 741880497682412958L;
	
	InputHandler input;
	ImageIcon sprite;
	
	public MainCharacter() {
		this.setBounds(100, 100, 640, 640);
		this.setBackground(Color.red);
		this.setLayout(null);
		this.setOpaque(true);
		
		sprite = new ImageIcon("C:\\Users\\karla\\Desktop\\Untitled.png");
		input = InputHandler.getInstance();
		
		this.setIcon(sprite);
	}
	
	public void move(double delta) {
		//System.out.printf("Moving...\n");
		if(input.getInput(InputHandler.KEY_UP) == 1) this.setLocation(this.getX(), (this.getY() - (int) (500 * delta)));
		if(input.getInput(InputHandler.KEY_DOWN) == 1) this.setLocation(this.getX(), this.getY() + (int) (500 * delta));
		if(input.getInput(InputHandler.KEY_LEFT) == 1) this.setLocation(this.getX() - (int) (500 * delta), this.getY());
		if(input.getInput(InputHandler.KEY_RIGHT) == 1) this.setLocation(this.getX() + (int) (500 * delta), this.getY());		
	}
}
