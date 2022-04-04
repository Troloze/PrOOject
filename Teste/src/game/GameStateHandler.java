package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Scanner;

import engine.FileHandler;
import engine.InputHandler;

public class GameStateHandler {
	private static GameStateHandler instance;
	
	public static final int STATE_MAIN_MENU = 0;
	public static final int STATE_PLAYING = 1;
	public static final int STATE_PAUSED = 2;
	public static final int STATE_LOSE = 3;
	public static final int STATE_RANKING = 4;
	public static final int STATE_INSERTING = 5;

	private static int state = 0;
	
	private Rank rank;
	private KeyBoard keyBoard;
	private FileHandler fileHandler;
	private InputHandler inputHandler;
	private ArrayList<MenuElement> mainMenu;
	private ArrayList<MenuElement> pauseMenu;
	private MainCharacter mainCharacter;
	
	private GameStateHandler() {
		rank = Rank.getInstance();
		keyBoard = KeyBoard.getInstance();
		mainMenu = MenuElement.getMainMenu();
		pauseMenu = MenuElement.getPauseMenu();
		fileHandler = FileHandler.getInstance();
		inputHandler = InputHandler.getInstance();
		mainCharacter = MainCharacter.getInstance();
	}
	
	public static GameStateHandler getInstance() {
		if(instance == null) {
			instance = new GameStateHandler();
		}
		
		return instance;
	}

	public void updateState() {		
		switch(state) {
		case STATE_MAIN_MENU:
			mainMenu();
			break;
		case STATE_PAUSED:
			pause();
			break;
		case STATE_LOSE:
			break;
		case STATE_RANKING:
			break;
		case STATE_INSERTING:
			insert();
			break;
		}
	}
	
	public void mainMenu() {
		if(inputHandler.getInput(InputHandler.KEY_UP) == 0) {
			if(MenuElement.getMainMenuIndex() > 0) {
				MenuElement.setMainMenuIndex(MenuElement.getMainMenuIndex() - 1);
			} else {
				MenuElement.setMainMenuIndex(2);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_DOWN) == 0) {
			if(MenuElement.getMainMenuIndex() < 2) {
				MenuElement.setMainMenuIndex(MenuElement.getMainMenuIndex() + 1);
			} else {
				MenuElement.setMainMenuIndex(0);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_SHOOT) == 0) {
			mainMenu.get(MenuElement.getMainMenuIndex()).function(this);
		}
		
		mainMenu.get(MenuElement.getMainMenuIndex()).setFocus(true);
	}
	
	public void pause() {
		if(inputHandler.getInput(InputHandler.KEY_UP) == 0) {
			if(MenuElement.getPauseMenuIndex() > 0) {
				MenuElement.setPauseMenuIndex(MenuElement.getPauseMenuIndex() - 1);
			} else {
				MenuElement.setPauseMenuIndex(2);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_DOWN) == 0) {
			if(MenuElement.getPauseMenuIndex() < 2) {
				MenuElement.setPauseMenuIndex(MenuElement.getPauseMenuIndex() + 1);
			} else {
				MenuElement.setPauseMenuIndex(0);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_SHOOT) == 0) {
			pauseMenu.get(MenuElement.getPauseMenuIndex()).function(this);
		}
		
		pauseMenu.get(MenuElement.getPauseMenuIndex()).setFocus(true);
	}
	
	public void insert() {
		if(inputHandler.getInput(InputHandler.KEY_UP) == 0) {
			if(keyBoard.getCharYPosition() > 0) {
				keyBoard.setCharYPosition(keyBoard.getCharYPosition() - 1);
			} else {
				keyBoard.setCharYPosition(KeyBoard.CHAR_Y_MAX - 1);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_DOWN) == 0) {
			if(keyBoard.getCharYPosition() < KeyBoard.CHAR_Y_MAX - 1) {
				keyBoard.setCharYPosition(keyBoard.getCharYPosition() + 1);
			} else {
				keyBoard.setCharYPosition(0);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_RIGHT) == 0) {
			if(keyBoard.getCharXPosition() < KeyBoard.CHAR_X_MAX - 1) {
				keyBoard.setCharXPosition(keyBoard.getCharXPosition() + 1);
			} else {
				keyBoard.setCharXPosition(0);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_LEFT) == 0) {
			if(keyBoard.getCharXPosition() > 0) {
				keyBoard.setCharXPosition(keyBoard.getCharXPosition() - 1);
			} else {
				keyBoard.setCharXPosition(KeyBoard.CHAR_X_MAX - 1);
			}
		} else if (inputHandler.getInput(InputHandler.KEY_SHOOT) == 0) {
			if(keyBoard.getChar() == '>') {
				rank.setName(keyBoard.getString());
				rank.setScore(13);
				rank.sortRankList();
				fileHandler.rewriteFile("rank.txt", rank.getRankList());
				keyBoard.setDefault();
				setState(0);
			} else {
				if(keyBoard.getChar() == '<') {
					keyBoard.removeChar();
				} else {
					keyBoard.addChar(keyBoard.getChar());
				}
			}
			
			
			
		}
	}
	
	public void start() {
		setState(1);
	}
	
	public void resume() {
		setState(1);
	}
	
	public void returnToMain() {
		setState(0);
	}
	
	public void ranking() {
		
	}
	
	public void quit() {
		System.exit(0);
	}
	
	public void gameOver() {
		
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.pink);
		
		switch(state) {
		case STATE_MAIN_MENU:
			mainMenu.forEach(element -> {element.draw(g2);});
			break;
		case STATE_PAUSED:
			pauseMenu.forEach(element -> {element.draw(g2);});
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			keyBoard.draw(g2);
			break;
		}
	}
	
	public static int getState() {
		return state;
	}
	
	public static void setState(int aux) {
		state = aux;
	}
}
