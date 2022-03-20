package poo.game.project;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

public final class InputHandler extends JComponent{
	
	private static final long serialVersionUID = 5912494406317256638L;
	
	private static InputHandler instance;
	//private static MouseHandler mouseHandler;
	private static KeyboardHandler keyHandler;
	
	private static Map<Integer, Integer> inputStatus = new HashMap<>();
	
	public static final int KEY_UP = 0;
	public static final int KEY_RIGHT = 1;
	public static final int KEY_DOWN = 2;
	public static final int KEY_LEFT = 3;
	public static final int KEY_SHOOT = 4;
	public static final int KEY_BOMB = 5;
	public static final int KEY_PAUSE = 6;
	public static final int KEY_FOCUS = 7;
	
	//public static final int MOUSE_LEFT = 0b100;
	//public static final int MOUSE_MIDDLE = 0b010;
	//public static final int MOUSE_RIGHT = 0b001;
	
	public static final int KEY_JUST_PRESSED = 0;
	public static final int KEY_PRESSED = 1;
	public static final int KEY_JUST_RELEASED = 2;
	public static final int KEY_OUT = 3;
	
	private InputHandler() {
		this.setBounds(0, 0, 1280, 960);
		//this.setOpaque(true);
		//this.setBackground(Color.red);
		this.setFocusable(true);
		this.setLayout(null);
		
		//mouseHandler = MouseHandler.getInstance();
		keyHandler = KeyboardHandler.getInstance();
		
		//this.addMouseListener(mouseHandler);
		this.addKeyListener(keyHandler);
		
		for (int i = 0; i < 8; i++) {
			inputStatus.put(i, KEY_OUT);
		}
	}
		
	public static InputHandler getInstance() {
		if (instance == null) {
			instance = new InputHandler();
		}
		return instance;
	}
	
	public void updateInputStatus() {
		/*if (getMousePosition() != null)
			System.out.printf("%d, %d\n", getMousePosition().x, getMousePosition().y);*/
		if (keyHandler.getKeyStatus(KeyEvent.VK_UP) == 1 || keyHandler.getKeyStatus(KeyEvent.VK_W) == 1)
			setInput(KEY_UP, true);
		else 
			setInput(KEY_UP, false);
		
		if (keyHandler.getKeyStatus(KeyEvent.VK_RIGHT) == 1 || keyHandler.getKeyStatus(KeyEvent.VK_D) == 1) 
			setInput(KEY_RIGHT, true);
		else 
			setInput(KEY_RIGHT, false);
	
		if (keyHandler.getKeyStatus(KeyEvent.VK_DOWN) == 1 || keyHandler.getKeyStatus(KeyEvent.VK_S) == 1)
			setInput(KEY_DOWN, true);
		else 
			setInput(KEY_DOWN, false);
		
		if (keyHandler.getKeyStatus(KeyEvent.VK_LEFT) == 1 || keyHandler.getKeyStatus(KeyEvent.VK_A) == 1) 
			setInput(KEY_LEFT, true);
		else 
			setInput(KEY_LEFT, false);
		
		if (keyHandler.getKeyStatus(KeyEvent.VK_Z) == 1 || keyHandler.getKeyStatus(KeyEvent.VK_SPACE) == 1) 
			setInput(KEY_SHOOT, true);
		else 
			setInput(KEY_SHOOT, false);
		
		if (keyHandler.getKeyStatus(KeyEvent.VK_X) == 1 || keyHandler.getKeyStatus(KeyEvent.VK_CONTROL) == 1) 
			setInput(KEY_BOMB, true);
		else 
			setInput(KEY_BOMB, false);
		
		if (keyHandler.getKeyStatus(KeyEvent.VK_C) == 1 || keyHandler.getKeyStatus(KeyEvent.VK_SHIFT) == 1) 
			setInput(KEY_FOCUS, true);
		else 
			setInput(KEY_FOCUS, false);
		
		if (keyHandler.getKeyStatus(KeyEvent.VK_ESCAPE) == 1) 
			setInput(KEY_PAUSE, true);
		else 
			setInput(KEY_PAUSE, false);
		
	}
	
	public int getInput(int key) {
		return inputStatus.get(key);
	}

	private void setInput(int key, Boolean isDown) {
		int value = inputStatus.get(key);
		inputStatus.replace(key, (isDown) ? ((value == 0 || value == 1) ? 1 : 0) : ((value == 0 || value == 1) ? 2 : 3));
	}
	
}
