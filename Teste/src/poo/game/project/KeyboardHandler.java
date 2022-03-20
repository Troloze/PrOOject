package poo.game.project;

import java.util.HashMap;
import java.util.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public final class KeyboardHandler implements KeyListener{

	private static KeyboardHandler instance;
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
			System.out.println("KeyGen");
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
