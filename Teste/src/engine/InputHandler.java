package engine;

//import java.awt.MouseInfo;
//import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
//import javax.swing.SwingUtilities;

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
		this.setFocusable(true);
	}
	
	private static void setup() {
		//mouseHandler = MouseHandler.getInstance();
		keyHandler = KeyboardHandler.getInstance();
				
		//this.addMouseListener(mouseHandler);
		instance.addKeyListener(keyHandler);
			
		for (int i = 0; i < 8; i++) {
			inputStatus.put(i, KEY_OUT);
		}
	}
		
	public static InputHandler getInstance() {
		if (instance == null) {
			instance = new InputHandler();
			setup();
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
	
	public static final class KeyboardHandler implements KeyListener{

		private static InputHandler.KeyboardHandler instance;
		private static Map<Integer, Integer> keyStatus;
		
		
		/*
		 * Used for simplicity, it will automatically add the keys set here to the keyStatus table.
		 */
		private final int[] keysOfInterest = {
			KeyEvent.VK_UP, 		// Event: Up
			KeyEvent.VK_RIGHT, 		// Event: Right
			KeyEvent.VK_DOWN, 		// Event: Down
			KeyEvent.VK_LEFT,		// Event: Left
			KeyEvent.VK_W,			// Event: Up
			KeyEvent.VK_D,			// Event: Right
			KeyEvent.VK_S,			// Event: Down
			KeyEvent.VK_A,			// Event: Left
			KeyEvent.VK_Z,			// Event: Shoot (Action)
			KeyEvent.VK_X,			// Event: Bomb 	(Cancel)
			KeyEvent.VK_C,			// Event: Focus
			KeyEvent.VK_SHIFT,		// Event: Focus
			KeyEvent.VK_CONTROL,	// Event: Bomb	(Cancel)
			KeyEvent.VK_SPACE,		// Event: Shoot	(Action)
			KeyEvent.VK_ESCAPE		// Event: Pause
		};	
			
		private KeyboardHandler() {
			keyStatus = new HashMap<>();
			for (int i: keysOfInterest) 
				keyStatus.put(i, 0);
		}

		public static KeyboardHandler getInstance() {
			if (instance == null) {
				instance = new KeyboardHandler();
			}
			return instance;
		}
		
		public int getKeyStatus(int key) {
			return keyStatus.get(key);
		}
		
		public Map<Integer, Integer> getKey() {
			return keyStatus;
		}
		
		// Unused
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if (keyStatus.containsKey(code)) {
				keyStatus.replace(code, 1);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			if (keyStatus.containsKey(code)) {
				keyStatus.replace(code, 0);
			}
		}

	}

	/*
	public final class MouseHandler implements MouseListener{

		private static InputHandler.MouseHandler instance;
		private static InputHandler inputHandler;
		private Point mousePosition;
		
		private int mouseStatus = 0;
		
		private MouseHandler() {
			
		}
		
		public static MouseHandler getInstance() {
			if (instance == null) {
				inputHandler = InputHandler.getInstance();
				instance = inputHandler.new MouseHandler();
			}
			return instance;
		}
		
		public void updateMousePosition() {
			mousePosition = MouseInfo.getPointerInfo().getLocation();
		}
		
		public Point getMousePosition() {
			return mousePosition;
		}
		
		public int getMouseButton(int key) {
			return mouseStatus & key;
		}
			
		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) 
				mouseStatus = mouseStatus | 0b100;
			
			if (SwingUtilities.isRightMouseButton(e)) 
				mouseStatus = mouseStatus | 0b010;
			
			if (SwingUtilities.isMiddleMouseButton(e)) 
				mouseStatus = mouseStatus | 0b001;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) 
				mouseStatus = mouseStatus & 0b011;
			
			if (SwingUtilities.isRightMouseButton(e)) 
				mouseStatus = mouseStatus & 0b101;
			
			if (SwingUtilities.isMiddleMouseButton(e)) 
				mouseStatus = mouseStatus & 0b110;
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

	}/**/
}
