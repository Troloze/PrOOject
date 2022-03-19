package poo.game.project;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 3505784181017226130L;
	
	private final int PANEL_WIDTH = 1280;
	private final int PANEL_HEIGTH = 960;
	private final int FPS = 60;
	
	Thread gameThread;
	
	MainCharacter mainCharacter;
	
	public MyPanel() {
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGTH));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setLayout(null);
		this.setOpaque(true);
		
		mainCharacter = new MainCharacter();
		
		this.add(mainCharacter);
	}
	
	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double ns = 1000000000.0;
		double drawInterval = ns / FPS;
		double delta = 0;
		double lastTime = System.nanoTime();
		double currentTime;
		double a = lastTime;
		double b;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
	
			if(delta >= 1) {
				b = (currentTime - a) / ns;
				a = currentTime;
				mainCharacter.move(b);
				delta--;
			}
			
		}
		
	}
	
	public void update() {
		
	}
	
	public void paintComponents() {
		
	}
		
}
