package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.InputHandler;
import misc.GetGoodException;

public class GameStateHandler {
	private static GameStateHandler instance;
	
	public static final int STATE_MAIN_MENU = 0;
	public static final int STATE_PLAYING = 1;
	public static final int STATE_PAUSED = 2;
	public static final int STATE_LOSE = 3;
	public static final int STATE_RANKING = 4;
	public static final int STATE_INSERTING = 5;

	private static int state = 0;
	
	private InputHandler inputHandler;
	
	private RankInfo mainInfo;
	private RankList rankList;
	private KeyBoard keyBoard;
	private ArrayList<MenuElement> mainMenu;
	private ArrayList<MenuElement> pauseMenu;
	private ArrayList<MenuElement> rankingMenu;
	
	private GameStateHandler() {
		inputHandler = InputHandler.getInstance();
		
		mainInfo = RankInfo.getInstance();
		rankList = RankList.getInstance();
		keyBoard = KeyBoard.getInstance();
		mainMenu = MenuElement.getMainMenu();
		pauseMenu = MenuElement.getPauseMenu();
		rankingMenu = MenuElement.getRankingMenu();
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
			ranking();
			break;
		case STATE_INSERTING:
			insert();
			break;
		}
	}
	
	private void mainMenu() {
		if(inputHandler.getInput(InputHandler.KEY_UP) == 0) {
			if(MenuElement.getMenuIndex(MenuElement.MAIN_MENU) > 0) {
				MenuElement.setMenuIndex(MenuElement.getMenuIndex(MenuElement.MAIN_MENU) - 1, MenuElement.MAIN_MENU);
			} else {
				MenuElement.setMenuIndex(2, MenuElement.MAIN_MENU);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_DOWN) == 0) {
			if(MenuElement.getMenuIndex(MenuElement.MAIN_MENU) < 2) {
				MenuElement.setMenuIndex(MenuElement.getMenuIndex(MenuElement.MAIN_MENU) + 1, MenuElement.MAIN_MENU);
			} else {
				MenuElement.setMenuIndex(0, MenuElement.MAIN_MENU);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_SHOOT) == 0) {
			mainMenu.get(MenuElement.getMenuIndex(MenuElement.MAIN_MENU)).function(this);
		}
		
		mainMenu.get(MenuElement.getMenuIndex(MenuElement.MAIN_MENU)).setFocus(true);
	}
	
	private void pause() {
		if(inputHandler.getInput(InputHandler.KEY_UP) == 0) {
			if(MenuElement.getMenuIndex(MenuElement.PAUSE_MENU) > 0) {
				MenuElement.setMenuIndex(MenuElement.getMenuIndex(MenuElement.PAUSE_MENU) - 1, MenuElement.PAUSE_MENU);
			} else {
				MenuElement.setMenuIndex(2, MenuElement.PAUSE_MENU);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_DOWN) == 0) {
			if(MenuElement.getMenuIndex(MenuElement.PAUSE_MENU) < 2) {
				MenuElement.setMenuIndex(MenuElement.getMenuIndex(MenuElement.PAUSE_MENU) + 1, MenuElement.PAUSE_MENU);
			} else {
				MenuElement.setMenuIndex(0, MenuElement.PAUSE_MENU);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_SHOOT) == 0) {
			pauseMenu.get(MenuElement.getMenuIndex(MenuElement.PAUSE_MENU)).function(this);
		}
		
		pauseMenu.get(MenuElement.getMenuIndex(MenuElement.PAUSE_MENU)).setFocus(true);
	}
	
	private void ranking() {
		if(inputHandler.getInput(InputHandler.KEY_UP) == 0) {
			if(MenuElement.getMenuIndex(MenuElement.RANKING_MENU) > 0) {
				MenuElement.setMenuIndex(MenuElement.getMenuIndex(MenuElement.RANKING_MENU) - 1, MenuElement.RANKING_MENU);
			} else {
				MenuElement.setMenuIndex(1, MenuElement.RANKING_MENU);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_DOWN) == 0) {
			if(MenuElement.getMenuIndex(MenuElement.RANKING_MENU) < 1) {
				MenuElement.setMenuIndex(MenuElement.getMenuIndex(MenuElement.RANKING_MENU) + 1, MenuElement.RANKING_MENU);
			} else {
				MenuElement.setMenuIndex(0, MenuElement.RANKING_MENU);
			}
		} else if(inputHandler.getInput(InputHandler.KEY_SHOOT) == 0) {
			rankingMenu.get(MenuElement.getMenuIndex(MenuElement.RANKING_MENU)).function(this);
		}
		
		rankingMenu.get(MenuElement.getMenuIndex(MenuElement.RANKING_MENU)).setFocus(true);
	}
	
	private void insert() {
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
				mainInfo.setName(keyBoard.getString());
				mainInfo.setScore(13);
				rankList.sortRankList();
				rankList.save();
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
		
		setState(STATE_PLAYING);
		Game.getInstance().startGame();
	}
	
	public void resume() {
		MenuElement.setDefault();
		setState(STATE_PLAYING);
	}
	
	public void returnToMain() {
		Game.getInstance().destroyAll();
		MenuElement.setDefault();
		setState(STATE_MAIN_MENU);
	}
	
	public void toRanking() {
		rankList.formatRankList();
		setState(STATE_RANKING);
	}
	
	public void quit() throws GetGoodException {
		if(mainInfo.getScore() < 500000) {
			GetGoodException e = new GetGoodException(mainInfo.getScore());
			throw e;
		}
		
		System.exit(0);
	}
	
	public void gameOver() {
		Game.getInstance().destroyAll();
		if(rankList.isEligible()) {	
			setState(STATE_INSERTING);
		} else {
			setState(STATE_RANKING);
		}
	}
	
	public void reset() {
		rankList.reset();
		returnToMain();
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.pink);
		
		switch(state) {
		case STATE_MAIN_MENU:
			MenuElement.drawMainMenu(g2);
			break;
		case STATE_PAUSED:
			MenuElement.drawPauseMenu(g2);
			break;
		case STATE_LOSE:
			break;
		case STATE_RANKING:
			rankList.draw(g2);
			MenuElement.drawRankingMenu(g2);
			break;
		case STATE_INSERTING:
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
