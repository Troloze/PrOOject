package engine;

import game.Game;

public final class GameLoop {
	
	private static GameLoop instance;
	
	private Thread gameThread;
	private Game game;
	private InputHandler input;
	
	private GameLoop() {
		input = InputHandler.getInstance();
		game = Game.getInstance();
	}
	
	public void startLoop() {
		gameThread = new Thread(MyPanel.getInstance());
		gameThread.start();	
	}
	
	public static GameLoop getInstance() {
		if (instance == null) {
			instance = new GameLoop();
		}
		return instance;
	}
	
	public void loop() {
		double nanoSeconds = 1000000000.0;
		int fps = MyPanel.getFPS();
		double drawInterval = nanoSeconds/fps;
		double delta = 0;
		double lastTime = System.nanoTime();
		double currentTime;
		double a = lastTime	;
		double b;
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
	
			if(delta >= 1) {
				b = (currentTime - a) / nanoSeconds;
				a = currentTime;
				
				input.updateInputStatus();
				game.gameUpdate(b);
				MyPanel.getInstance().repaint();
				
				delta--;
			}
		}
	}
}
