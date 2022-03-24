package engine;

public final class GameLoop {
	
	private static GameLoop instance;
	
	private Thread gameThread;	
	private InputHandler input;
	
	public GameLoop() {
		input = InputHandler.getInstance();
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
		double ns = 1000000000.0;
		int fps = MyPanel.getFPS();
		double drawInterval = ns/fps;
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
				b = (currentTime - a) / ns;
				a = currentTime;
				input.updateInputStatus();
				if (input.getInput(InputHandler.KEY_UP) == 0) {
					System.out.println("HEY");
				}
				MyPanel.getInstance().repaint();
				delta--;
			}
		}
	}
}
