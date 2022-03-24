package engine;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public final class MyPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 3505784181017226130L;
	
	private static MyPanel instance;
	
	private static final int PANEL_WIDTH = 1280;
	private static final int PANEL_HEIGTH = 960;
	private static final int FPS = 60;
	
	public static int getFPS() {
		return FPS;
	}
	
	private InputHandler input;
	private GameLoop gameLoop;
	
	MainCharacter mainCharacter;
	
	private MyPanel() {
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGTH));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setLayout(null);
		this.setOpaque(false);
		
		gameLoop = GameLoop.getInstance();
		input = InputHandler.getInstance();
		this.add(input);
	}
	
	public static MyPanel getInstance() {
		if (instance == null) {
			instance = new MyPanel();
		}
		return instance;
	}

	public void startGame() {
		gameLoop.startLoop();
	}

	@Override
	public void run() {
		gameLoop.loop();		
	}
	
	public void update() {
		
	}
	
	public void paint() {
		
	}
		
}
