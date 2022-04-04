package engine;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public final class MyFrame extends JFrame {

	private static final long serialVersionUID = 6587137810595205039L;
	
	MyPanel gamePanel;
	
	public MyFrame() {
		
		gamePanel = MyPanel.getInstance();
		
		this.setTitle("PrOOject");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(gamePanel);
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		gamePanel.startGame();
	}
}
