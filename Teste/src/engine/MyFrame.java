package engine;

import javax.swing.JFrame;

public final class MyFrame extends JFrame {

	private static final long serialVersionUID = 6587137810595205039L;
	
	MyPanel gamePanel;
	
	public MyFrame() {
		System.getProperties().setProperty("sun.java2d.opengl", "true");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(" ");
		
		gamePanel = MyPanel.getInstance();
		this.add(gamePanel);
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		gamePanel.startGame();
	}
}
