package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import game.Game;

public final class MyPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 3505784181017226130L;
	
	private static MyPanel instance;
	
	public static final int PANEL_WIDTH = (int) (1280.0 * 0.75);
	public static final int PANEL_HEIGHT = (int) (960 * 0.75);
	private static final int FPS = 60;
	
	public static int getFPS() {
		return FPS;
	}
	
	private InputHandler input;
	private GameLoop gameLoop;
	private Game game;
	
	private MyPanel() {
		gameLoop = GameLoop.getInstance();
		input = InputHandler.getInstance();
		game = Game.getInstance();
		
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setDoubleBuffered(true);
		this.setLayout(null);
		this.setOpaque(true);
		this.setVisible(true);
		
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
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
		
		g.setColor(new Color(0x1C, 0x1C, 0x27));
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		
		game.gamePaint(g2);
		
		g.dispose();
	}
}
