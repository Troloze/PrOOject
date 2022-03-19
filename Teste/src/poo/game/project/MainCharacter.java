package poo.game.project;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MainCharacter extends JLabel{

	private static final long serialVersionUID = 741880497682412958L;
	
	MyKeyListener keyListener;
	ImageIcon sprite;
	
	public MainCharacter() {
		this.setBounds(100, 100, 320, 320);
		this.setBackground(Color.black);
		this.setLayout(null);
		this.setFocusable(true);
		this.setOpaque(true);
		
		sprite = new ImageIcon("C:\\Users\\tiago\\Pictures\\sprite.png");
		keyListener = MyKeyListener.getInstance();
		
		this.setIcon(sprite);
		this.addKeyListener(keyListener);
	}
	
	public void move(double delta) {
		System.out.printf("Moving...\n");
		if(keyListener.upPressed) this.setLocation(this.getX(), (this.getY() - (int) (500 * delta)));
		if(keyListener.downPressed) this.setLocation(this.getX(), this.getY() + (int) (500 * delta));
		if(keyListener.leftPressed) this.setLocation(this.getX() - (int) (500 * delta), this.getY());
		if(keyListener.rigthPressed) this.setLocation(this.getX() + (int) (500 * delta), this.getY());
	}
}
