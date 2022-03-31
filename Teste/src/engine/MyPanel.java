package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import game.Game;
import game.MainCharacter;

public final class MyPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 3505784181017226130L;
	
	private static MyPanel instance;
	
	public static final int PANEL_WIDTH = 1280;
	public static final int PANEL_HEIGHT = 960;
	private static final int FPS = 60;
	
	public static int getFPS() {
		return FPS;
	}
	
	private InputHandler input;
	private GameLoop gameLoop;
	private Game game;
	private Renderer renderer;
	
	private MyPanel() {
		gameLoop = GameLoop.getInstance();
		input = InputHandler.getInstance();
		game = Game.getInstance();
		renderer = Renderer.getInstance();
		
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setBackground(Color.blue);
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
		g.setColor(new Color(0x1C, 0x1C, 0x27));
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		
		Graphics2D g2 = (Graphics2D) g;
		
		game.gamePaint(g2);
		renderer.render(g2);
				
		g.dispose();
	}	
}
