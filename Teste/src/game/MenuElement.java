package game;

import java.awt.Graphics2D;
import java.awt.Image;

public class MenuElement {
	
	public Image sprite;
	
	public MenuElement() {
		/*
		*	Constructor;
		*/
	}

	public void draw(Graphics2D g2) {}
	
	public void function() {}
	
	
	public static StartButton getStartButton() {
		return StartButton.getButtonInstance();
	}
	
	
	public static RankButton getRankButton() {
		return RankButton.getButtonInstance();
	}
	
	
	public static ExitButton getExitButton() {
		return ExitButton.getButtonInstance();
	}
	
	/*
	 * Subclasses;
	 */
	
	public static class StartButton extends MenuElement {
		private static StartButton instance;
		
		private StartButton() {
			
		}
		
		public static StartButton getButtonInstance() {
			if(instance == null) {
				instance = new StartButton();
			}
			
			return instance;
		}
		
		@Override
		public void function() {
			System.out.println("Start Button");
		}
		
		@Override
		public void draw(Graphics2D g2) {
			g2.fillRect(0, 0, 100, 100);
		}
	}
	
	public static class RankButton extends MenuElement{
		private static RankButton instance;
		
		private RankButton() {
			
		}
		
		public static RankButton getButtonInstance() {
			if(instance == null) {
				instance = new RankButton();
			}
			
			return instance;
		}
		
		@Override
		public void function() {
			System.out.println("Rank Button");
		}
		
		@Override
		public void draw(Graphics2D g2) {
			g2.fillRect(100, 100, 100, 100);
		}
	}
	
	public static class ExitButton extends MenuElement{
		private static ExitButton instance;
		
		private ExitButton() {
			
		}
		
		public static ExitButton getButtonInstance() {
			if(instance == null) {
				instance = new ExitButton();
			}
			
			return instance;
		}
		
		@Override
		public void function() {
			System.out.println("Exit Button");
		}
		
		@Override
		public void draw(Graphics2D g2) {
			g2.fillRect(200, 200, 100, 100);
		}
	}
}
