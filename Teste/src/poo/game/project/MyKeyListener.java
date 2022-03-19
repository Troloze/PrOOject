package poo.game.project;

import java.util.HashMap;
import java.util.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class MyKeyListener implements KeyListener {
	
	private static MyKeyListener instance;		// Singleton;
	
	private Map<Integer, Integer> input = new HashMap<>();
	
	public final int KEY_UP = 0;
	public final int KEY_RIGHT = 1;
	public final int KEY_DOWN = 2;
	public final int KEY_LEFT = 3;
	public final int KEY_SHOOT = 4;
	public final int KEY_BOMB = 5;
	public final int KEY_PAUSE = 6;
	public final int KEY_FOCUS = 7;
	
	public final int KEY_JUST_PRESSED = 0;
	public final int KEY_PRESSED = 1;
	public final int KEY_JUST_RELEASED = 2;
	public final int KEY_OUT = 3;
	
	//boolean upPressed, downPressed, leftPressed, rigthPressed;

	private MyKeyListener() {
		
		for(int i = 0; i < 8; i++) {
			
			input.put(i, 3);
			
		}
		
	}
	
	public static MyKeyListener getInstance() {
		
		if(instance == null) {
			
			instance = new MyKeyListener();
			
		}
		
		return instance;
		
	}
	
	public int getInput(int key) {
		
		return input.get(key);
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//System.out.printf("Apertado: %c\n", code);
		
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
			
			if(input.get(KEY_UP) == 0 || input.get(KEY_UP) == 1) 
				input.replace(KEY_UP, 1);
			
			if(input.get(KEY_UP) == 2 || input.get(KEY_UP) == 3)
				input.replace(KEY_UP, 0);
			
		}
		
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
			
		}
		
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
			
		}
		
		if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)  {
			
		}
		
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) upPressed = false;
		if(code == KeyEvent.VK_S) downPressed = false;
		if(code == KeyEvent.VK_A) leftPressed = false;
		if(code == KeyEvent.VK_D) rigthPressed = false;
	}
	
}
