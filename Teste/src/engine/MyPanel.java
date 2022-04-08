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
	
	public static final int PANEL_WIDTH = RenderSettings.PANEL_WIDTH;
	public static final int PANEL_HEIGHT = RenderSettings.PANEL_HEIGHT;
	private static final int FPS = 60;
	
	public static int getFPS() {
		return FPS;
	}
	
	private InputHandler input;
	private GameLoop gameLoop;
	private Renderer renderer;
	
	private MyPanel() {
		gameLoop = GameLoop.getInstance();
		input = InputHandler.getInstance();
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
		//g.setColor(Color.black);
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		
		@SuppressWarnings("unused")
		long c = System.nanoTime();
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		renderer.render(g2);
		
		//Game.getInstance().debugDraw(g2);
		//System.out.println((System.nanoTime() - c)/1000000000.0);
		
		g.dispose();
	}	
}
