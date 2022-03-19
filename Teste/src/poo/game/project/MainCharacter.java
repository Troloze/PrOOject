package poo.game.project;

import java.awt.Color;

import javax.swing.JLabel;

public class MainCharacter extends JLabel{

	private static final long serialVersionUID = 741880497682412958L;
	
	MyKeyListener keyListener;
	
	public MainCharacter() {
		this.setBounds(100, 100, 50, 50);
		this.setBackground(Color.white);
		this.setOpaque(true);
		
		keyListener = new MyKeyListener();
		
		this.addKeyListener(keyListener);
	}
}
