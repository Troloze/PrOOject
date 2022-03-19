package poo.game.project;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable {
	private final int PANEL_WIDTH = 1280;
	private final int PANEL_HEIGTH = 960;
	
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
		System.out.printf("ENTROU");
	}

	@Override
	public void run() {
		while(gameThread != null) {
			System.out.printf("Funcionando");
		}
	}
		
}
