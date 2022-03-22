package poo.game.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 3505784181017226130L;
	
	private final int PANEL_WIDTH = 960;
	private final int PANEL_HEIGTH = 720;
	private final int FPS = 60;
	
	Thread gameThread;
	InputHandler input;
	
	MainCharacter mainCharacter;
	
	public MyPanel() {
		mainCharacter = new MainCharacter(this);
		input = InputHandler.getInstance();
		
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGTH));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setLayout(null);
		this.setOpaque(true);
		
		this.add(input);
	}
	
	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double nanoSeconds = 1000000000.0;
		double drawInterval = nanoSeconds / FPS;
		double lastTime = System.nanoTime();
		double currentTime;
		double delta = 0;
		double a = lastTime;
		double b;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
	
			if(delta >= 1) {
				b = (currentTime - a) / nanoSeconds;
				a = currentTime;
				update(b);
				repaint();
				delta--;
			}
			
		}
		
	}
	
	public void update(double b) {
		input.updateInputStatus();
		mainCharacter.update(b);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		mainCharacter.draw(g2);
	}
		
}
