package poo.game.project;

import java.awt.Color;

import javax.swing.JLabel;

public class MainCharacter extends JLabel{
	MyKeyListener keyListener;
	
	public MainCharacter() {
		this.setBounds(100, 100, 50, 50);
		this.setBackground(Color.white);
		this.setOpaque(true);
		
		keyListener = new MyKeyListener();
		
		this.addKeyListener(keyListener);
	}
}
