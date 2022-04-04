package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import engine.MyPanel;

public abstract class MenuElement {
	private static final int SCREEN_HEIGHT = MyPanel.PANEL_HEIGHT;
	private static final int SCREEN_X_CENTER = MyPanel.PANEL_WIDTH / 2;
	private static final int SCREEN_Y_CENTER = MyPanel.PANEL_HEIGHT / 2;
	private static final int BOX_WIDTH = (int) ((double) MyPanel.PANEL_WIDTH * 0.2);
	private static final int BOX_HEIGHT = (int) ((double) SCREEN_HEIGHT * 0.1);
	private static final int BOX_GAPE = (int) ((double) BOX_HEIGHT / 4.0);
	private static final int BOX_DISTANCE = BOX_GAPE + BOX_HEIGHT;
	
	private static int mainMenuIndex = 0;
	private static int pauseMenuIndex = 0;
	
	private boolean focus;
	private Sprite image;

	public void function(GameStateHandler game) {}
	public void draw(Graphics2D g2) {}
	
	public static ArrayList<MenuElement> getMainMenu() {
		ArrayList<MenuElement> mainMenuElements = new ArrayList<>();
		
		mainMenuElements.add(StartButton.getButtonInstance());
		mainMenuElements.add(RankButton.getButtonInstance());
		mainMenuElements.add(ExitButton.getButtonInstance());
		
		return mainMenuElements;
	}
	
	public static ArrayList<MenuElement> getPauseMenu() {
		ArrayList<MenuElement> mainMenuElements = new ArrayList<>();
		
		mainMenuElements.add(ResumeButton.getButtonInstance());
		mainMenuElements.add(MainMenuButton.getButtonInstance());
		mainMenuElements.add(ExitButton.getButtonInstance());
		
		return mainMenuElements;
	}

	public static int getMainMenuIndex() {
		return mainMenuIndex;
	}
	
	public static void setMainMenuIndex(int buttonIndex) {
		MenuElement.mainMenuIndex = buttonIndex;
	}
	
	public static int getPauseMenuIndex() {
		return pauseMenuIndex;
	}
	
	public static void setPauseMenuIndex(int pauseMenuIndex) {
		MenuElement.pauseMenuIndex = pauseMenuIndex;
	}
	
	public boolean getFocus() {
		return focus;
	}
	
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
	
	/*
	 * Subclasses;
	 */

	public static class StartButton extends MenuElement {
		private static StartButton instance;
		
		private StartButton() {}
		
		public static StartButton getButtonInstance() {
			if(instance == null) {
				instance = new StartButton();
			}
			
			return instance;
		}
		
		@Override
		public void function(GameStateHandler game) {
			game.start();
			System.out.println("Start Button");
		}
		
		@Override
		public void draw(Graphics2D g2) {
			
			if(!getFocus()) {
				g2.setColor(Color.pink);
			} else {
				g2.setColor(Color.red);
				setFocus(false);
			}
			
			g2.fillRect(SCREEN_X_CENTER - (BOX_WIDTH / 2), SCREEN_HEIGHT - (3 * BOX_DISTANCE), BOX_WIDTH, BOX_HEIGHT);
		}
	}
	
	public static class RankButton extends MenuElement{
		private static RankButton instance;
		
		private RankButton() {}
		
		public static RankButton getButtonInstance() {
			if(instance == null) {
				instance = new RankButton();
			}
			
			return instance;
		}
		
		@Override
		public void function(GameStateHandler game) {
			game.setState(GameStateHandler.STATE_INSERTING);
			System.out.println("Rank Button");
		}
		
		@Override
		public void draw(Graphics2D g2) {
			
			if(!getFocus()) {
				g2.setColor(Color.pink);
			} else {
				g2.setColor(Color.red);
				setFocus(false);
			}
			
			g2.fillRect(SCREEN_X_CENTER - (BOX_WIDTH / 2), SCREEN_HEIGHT - (2 * BOX_DISTANCE), BOX_WIDTH, BOX_HEIGHT);
		}
	}
	
	public static class ResumeButton extends MenuElement{
		private static ResumeButton instance;
		
		private ResumeButton() {}
		
		public static ResumeButton getButtonInstance() {
			if(instance == null) {
				instance = new ResumeButton();
			}
			
			return instance;
		}
		
		@Override
		public void function(GameStateHandler game) {
			game.resume();
			System.out.println("Resume Button");
		}
		
		@Override
		public void draw(Graphics2D g2) {
			if(!getFocus()) {
				g2.setColor(Color.pink);
			} else {
				g2.setColor(Color.red);
				setFocus(false);
			}
			
			g2.fillRect(SCREEN_X_CENTER - (BOX_WIDTH / 2), SCREEN_Y_CENTER -  BOX_DISTANCE, BOX_WIDTH, BOX_HEIGHT);
		}
	}
	
	public static class MainMenuButton extends MenuElement{
		private static MainMenuButton instance;
		
		private MainMenuButton() {}
		
		public static MainMenuButton getButtonInstance() {
			if(instance == null) {
				instance = new MainMenuButton();
			}
			
			return instance;
		}
		
		@Override
		public void function(GameStateHandler game) {
			game.returnToMain();
			System.out.println("MainMenu Button");
		}
		
		@Override
		public void draw(Graphics2D g2) {
			if(!getFocus()) {
				g2.setColor(Color.pink);
			} else {
				g2.setColor(Color.red);
				setFocus(false);
			}
			
			g2.fillRect(SCREEN_X_CENTER - (BOX_WIDTH / 2), SCREEN_Y_CENTER, BOX_WIDTH, BOX_HEIGHT);
		}
	}
	
	public static class ExitButton extends MenuElement{
		private static ExitButton instance;
		
		private ExitButton() {}
		
		public static ExitButton getButtonInstance() {
			if(instance == null) {
				instance = new ExitButton();
			}
			
			return instance;
		}
		
		@Override
		public void function(GameStateHandler game) {
			game.quit();
		}
		
		@Override
		public void draw(Graphics2D g2) {
			if(!getFocus()) {
				g2.setColor(Color.pink);
			} else {
				g2.setColor(Color.red);
				setFocus(false);
			}
			
			if(GameStateHandler.getState() == GameStateHandler.STATE_MAIN_MENU) g2.fillRect(SCREEN_X_CENTER - (BOX_WIDTH / 2), SCREEN_HEIGHT - BOX_HEIGHT - BOX_GAPE, BOX_WIDTH, BOX_HEIGHT);
			if(GameStateHandler.getState() == GameStateHandler.STATE_PAUSED) g2.fillRect(SCREEN_X_CENTER - (BOX_WIDTH / 2), SCREEN_Y_CENTER + BOX_DISTANCE, BOX_WIDTH, BOX_HEIGHT);
		
		}
	}
}
