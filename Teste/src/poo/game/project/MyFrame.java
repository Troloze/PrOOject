package poo.game.project;

import javax.swing.JFrame;

public final class MyFrame extends JFrame {

	private static final long serialVersionUID = 6587137810595205039L;
	
	MyPanel gamePanel;
	
	public MyFrame() {
		gamePanel = new MyPanel();
		
		this.setTitle("PrOOject");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(gamePanel);
		
		this.pack();
		this.setLocationRelativeTo(null);
		
		gamePanel.startGame();
	}
}
