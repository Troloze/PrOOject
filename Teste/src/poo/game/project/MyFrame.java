package poo.game.project;

import javax.swing.JFrame;

public final class MyFrame extends JFrame {

	private static final long serialVersionUID = 6587137810595205039L;
	
	MyPanel gamePanel;
	
	public MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("PrOOject");
		
		gamePanel = new MyPanel();
		this.add(gamePanel);
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		System.out.printf("ola");
		
		gamePanel.startGame();
	}
}
