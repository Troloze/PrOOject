package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import misc.GetGoodException;

public abstract class MenuElement {
	public static final int MAIN_MENU = 0;
	public static final int PAUSE_MENU = 1;
	public static final int RANKING_MENU = 2;
	
	private static int mainMenuIndex = 0;
	private static int pauseMenuIndex = 0;
	private static int rankingMenuIndex = 0;
	
	protected boolean focus;

	public void function(GameStateHandler game) {
		
	}
	
	public static ArrayList<MenuElement> getMainMenu() {
		ArrayList<MenuElement> mainMenuElements = new ArrayList<>();
		
		mainMenuElements.add(StartButton.getButtonInstance());
		mainMenuElements.add(RankButton.getButtonInstance());
		mainMenuElements.add(ExitButton.getButtonInstance());
		
		return mainMenuElements;
	}
	
	public static ArrayList<MenuElement> getPauseMenu() {
		ArrayList<MenuElement> pauseMenuElements = new ArrayList<>();
		
		pauseMenuElements.add(ResumeButton.getButtonInstance());
		pauseMenuElements.add(MainMenuButton.getButtonInstance());
		pauseMenuElements.add(ExitButton.getButtonInstance());
		
		return pauseMenuElements;
	}
	
	public static ArrayList<MenuElement> getRankingMenu() {
		ArrayList<MenuElement> rankingMenuElements = new ArrayList<>();
		
		rankingMenuElements.add(ResetButton.getButtonInstance());
		rankingMenuElements.add(BackButton.getButtonInstance());
		
		return rankingMenuElements;
	}
	
	public static void drawMainMenu(Graphics2D g2) {
		TextParameter.setFont(g2, TextParameter.MENU_FONT_SIZE);
		
		String[] str = {"START", "RANKING", "QUIT"};
		
		int x, y;
		
		for(int i = 0; i < 3; i++) {
			if(mainMenuIndex == 2 - i) {
				g2.setColor(TextParameter.FOCUS_COLOR);
			} else {
				g2.setColor(TextParameter.NOT_FOCUS_COLOR);
			}
			
			x = TextParameter.getXForCenteredText(g2, str[2 - i]);
			y = TextParameter.STRING_PANEL_Y_END - (i * (TextParameter.getStringHeight(g2, str[2 - i]) - TextParameter.OFFSET_CLOSE));
			
			g2.drawString(str[2 - i], x, y);
		}
	}
	
	public static void drawPauseMenu(Graphics2D g2) {
		TextParameter.setFont(g2, TextParameter.PAUSE_FONT_SIZE);
		
		String[] str = {"RESUME", "MAIN MENU", "QUIT"};
		
		int x, y;
		
		for(int i = 0; i < 3; i++) {
			if(pauseMenuIndex == 2 - i) {
				g2.setColor(TextParameter.FOCUS_COLOR);
			} else {
				g2.setColor(TextParameter.NOT_FOCUS_COLOR);
			}
			
			x = TextParameter.getXForCenteredText(g2, str[2 - i]);
			y = TextParameter.getYForCenteredText(g2, str[1]) + ((1 - i) * (TextParameter.getStringHeight(g2, str[2 - i]) / 2  + TextParameter.OFFSET_CLOSE));
			
			g2.drawString(str[2 - i], x, y);
		}
	}
	
	public static void drawRankingMenu(Graphics2D g2) {
		TextParameter.setFont(g2, TextParameter.MENU_FONT_SIZE);
		
		String[] str = {"RESET", "BACK"};
		
		int x, y;
		
		for(int i = 0; i < 2; i++) {
			if(rankingMenuIndex == 1 - i) {
				g2.setColor(Color.red);
			} else {
				g2.setColor(Color.pink);
			}
			
			x = TextParameter.getXForCenteredText(g2, str[1 - i]);
			y = TextParameter.STRING_PANEL_Y_END - (i * (TextParameter.getStringHeight(g2, str[1 - i]) - TextParameter.OFFSET_CLOSE));
			
			g2.drawString(str[1 - i], x, y);
		}
	}

	public static int getMenuIndex(int menu) {
		switch(menu) {
		case MAIN_MENU:
			return mainMenuIndex;
		case PAUSE_MENU:
			return pauseMenuIndex;
		case RANKING_MENU:
			return rankingMenuIndex;
		default:
			return -1;
		}
	}
	
	public static void setMenuIndex(int buttonIndex, int menu) {
		switch(menu) {
		case MAIN_MENU:
			mainMenuIndex = buttonIndex;
		case PAUSE_MENU:
			pauseMenuIndex = buttonIndex;
		case RANKING_MENU:
			rankingMenuIndex = buttonIndex;
		}
	}
	
	public static void setDefault() {
		mainMenuIndex = 0;
		pauseMenuIndex = 0;
		rankingMenuIndex = 0;
	} 
	
	protected boolean getFocus() {
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
			game.toRanking();
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
			try {
				game.quit();
			} catch (GetGoodException e) {
				// TODO Auto-generated catch block
				System.out.printf("Pontuacao minima necessaria para fechar o jogo não alcancada: %d/%d", e.getPlrScore(), e.getMinScore());
			}
		}
	}
	
	public static class BackButton extends MenuElement {
		private static BackButton instance;
		
		private BackButton() {}
		
		public static BackButton getButtonInstance() {
			if(instance == null) {
				instance = new BackButton();
			}
			
			return instance;
		}
		
		@Override
		public void function(GameStateHandler game) {
			game.returnToMain();
		}
	}
	
	public static class ResetButton extends MenuElement {
		private static ResetButton instance;
		
		private ResetButton() {}
		
		public static ResetButton getButtonInstance() {
			if(instance == null) {
				instance = new ResetButton();
			}
			
			return instance;
		}
		
		@Override
		public void function(GameStateHandler game) {
			game.reset();
		}
	}
}
