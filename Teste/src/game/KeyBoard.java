package game;

import java.awt.Graphics2D;

public class KeyBoard {
	private static KeyBoard instance;
	
	public static final int CHAR_Y_MAX = 5;
	public static final int CHAR_X_MAX = 10;
	public static final int CHARS_MAX = 15;
	
	public static final int POSITION_X_KEYBOARD = TextParameter.STRING_PANEL_X_CENTER - (int) (((double) CHAR_X_MAX / 2.0) * TextParameter.KEYBOARD_FONT_SIZE);
	public static final int POSITION_Y_KEYBOARD = TextParameter.STRING_PANEL_Y_END - (CHAR_Y_MAX * TextParameter.KEYBOARD_FONT_SIZE) + (int) ((double) TextParameter.KEYBOARD_FONT_SIZE * 0.5);
	public static final int POSITION_X_STRING = TextParameter.STRING_PANEL_X_CENTER - (int) (((double) CHARS_MAX / 2.0) * TextParameter.KEYBOARD_FONT_SIZE);
	public static final int POSITION_Y_STRING = POSITION_Y_KEYBOARD - (int) ((double) TextParameter.KEYBOARD_FONT_SIZE * 1.5);
	
	private int charYPosition = 0;
	private int charXPosition = 0;
	private String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.!?,0123456789-_()|/\\ <>";
	
	private char[] chars;
	private int charsIndex = 0;
	
	private KeyBoard() {
		chars = new char[CHARS_MAX];
	}
	
	public static KeyBoard getInstance() {
		if(instance == null) {
			instance = new KeyBoard();
		}
		
		return instance;
	}
	
	public void setDefault() {
		charXPosition = 0;
		charYPosition = 0;
		charsIndex = 0;
		
		for(int i = 0; i < CHARS_MAX; i++) {
			chars[i] = '\0';
		}
	}
	
	public void draw(Graphics2D g2) {
		TextParameter.setFont(g2, TextParameter.KEYBOARD_FONT_SIZE);
		g2.setColor(TextParameter.NOT_FOCUS_COLOR);
		
		int x, y;
		
		for(int i = 0; i < CHAR_Y_MAX; i++) {
			for(int j = 0; j < CHAR_X_MAX; j++) {
				x = POSITION_X_KEYBOARD + (j * TextParameter.KEYBOARD_FONT_SIZE);
				y = POSITION_Y_KEYBOARD + (i * TextParameter.KEYBOARD_FONT_SIZE);
				
				if(charYPosition == i && charXPosition == j) g2.setColor(TextParameter.FOCUS_COLOR);
				g2.drawString(String.valueOf(str.charAt((i * CHAR_X_MAX) + j)), x, y);
				g2.setColor(TextParameter.NOT_FOCUS_COLOR);
			}
		}
		
		drawString(g2);
	}
	
	public void drawString(Graphics2D g2) {
		int positionXStr, positionYStr;
		
		for(int i = 0; i < charsIndex; i++) {
			positionXStr = POSITION_Y_STRING;
			positionYStr = TextParameter.STRING_PANEL_X_CENTER - (int) (((double) charsIndex / 2.0) * TextParameter.KEYBOARD_FONT_SIZE) + (i * TextParameter.KEYBOARD_FONT_SIZE);
			
			g2.drawString(String.valueOf(chars[i]), positionYStr, positionXStr);
			System.out.println(String.valueOf(chars));
		}
	}
	
	public char getChar() {
		return str.charAt((charYPosition * CHAR_X_MAX) + charXPosition);
	}
	
	public void addChar(char c) {
		if(charsIndex < CHARS_MAX) {
			chars[charsIndex] = c;
			charsIndex++;
		}
	}
	
	public void removeChar() {
		if(charsIndex > 0) {
			chars[charsIndex - 1] = '\0';
			charsIndex--;
		}
	}
	
	public String getString() {
		if(String.valueOf(chars).indexOf("\0") > 0) {
			return String.valueOf(chars).substring(0, String.valueOf(chars).indexOf("\0"));
		} else {
			return String.valueOf(chars);
		}
	}
	
	public int getCharYPosition() {
		return charYPosition;
	}

	public void setCharYPosition(int charYPosition) {
		this.charYPosition = charYPosition;
	}

	public int getCharXPosition() {
		return charXPosition;
	}

	public void setCharXPosition(int charXPosition) {
		this.charXPosition = charXPosition;
	}
}
