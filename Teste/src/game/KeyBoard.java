package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import engine.FileHandler;
import engine.MyPanel;

public class KeyBoard {
	private static KeyBoard instance;
	
	public static final int CHAR_Y_MAX = 5;
	public static final int CHAR_X_MAX = 10;
	public static final int CHARS_MAX = 15;
	public static final int SCREEN_BOTTON = MyPanel.PANEL_HEIGHT;
	public static final int SCREEN_CENTER = (int) ((double) MyPanel.PANEL_WIDTH / 2.0);
	public static final int FONT_SIZE = (int) ((double) MyPanel.PANEL_HEIGHT * 0.06);
	public static final int POSITION_X_KEYBOARD = SCREEN_CENTER - (int) (((double) CHAR_X_MAX / 2.0) * FONT_SIZE);
	public static final int POSITION_Y_KEYBOARD = SCREEN_BOTTON - (CHAR_Y_MAX * FONT_SIZE) + (int) ((double) FONT_SIZE * 0.5);
	public static final int POSITION_X_STRING = SCREEN_CENTER - (int) (((double) CHARS_MAX / 2.0) * FONT_SIZE);
	public static final int POSITION_Y_STRING = POSITION_Y_KEYBOARD - (int) ((double) FONT_SIZE * 1.5);
	
	private FileHandler file;
	private int charYPosition = 0;
	private int charXPosition = 0;
	private String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.!?,0123456789-_()|/\\ <>";
	
	private char[] chars;
	private int charsIndex = 0;
	
	private KeyBoard() {
		file = FileHandler.getInstance();
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
		int positionX, positionY;
		
		g2.setFont(new Font(file.openFont("font/impact.ttf").getFontName(), Font.PLAIN, FONT_SIZE));
		
		for(int i = 0; i < CHAR_Y_MAX; i++) {
			for(int j = 0; j < CHAR_X_MAX; j++) {
				positionX = POSITION_X_KEYBOARD + (j * FONT_SIZE);
				positionY = POSITION_Y_KEYBOARD + (i * FONT_SIZE);
				
				if(charYPosition == i && charXPosition == j) g2.setColor(Color.red);
				g2.drawString(String.valueOf(str.charAt((i * CHAR_X_MAX) + j)), positionX, positionY);
				g2.setColor(Color.white);
			}
		}
		
		drawString(g2);
	}
	
	public void drawString(Graphics2D g2) {
		int positionXStr, positionYStr;
		
		for(int i = 0; i < charsIndex; i++) {
			positionXStr = POSITION_Y_STRING;
			positionYStr = SCREEN_CENTER - (int) (((double) charsIndex / 2.0) * FONT_SIZE) + (i * FONT_SIZE);
			
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
		return String.valueOf(chars);
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
