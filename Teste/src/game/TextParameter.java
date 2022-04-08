package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import engine.FileHandler;
import engine.RenderSettings;

public class TextParameter {
	public static final int STRING_PANEL_HEIGHT = (int) ((double) RenderSettings.PANEL_HEIGHT * 0.95);
	public static final int STRING_PANEL_WIDTH = (int) ((double) RenderSettings.PANEL_WIDTH * 0.95);
	public static final int STRING_PANEL_X_BEGINNING = (RenderSettings.PANEL_WIDTH - STRING_PANEL_WIDTH) / 2;
	public static final int STRING_PANEL_Y_BEGINNING = (RenderSettings.PANEL_HEIGHT - STRING_PANEL_HEIGHT) / 2;
	public static final int STRING_PANEL_X_END = RenderSettings.PANEL_WIDTH - STRING_PANEL_X_BEGINNING;
	public static final int STRING_PANEL_Y_END = RenderSettings.PANEL_HEIGHT - STRING_PANEL_Y_BEGINNING;
	public static final int STRING_PANEL_X_CENTER = RenderSettings.PANEL_WIDTH / 2;
	public static final int STRING_PANEL_Y_CENTER = RenderSettings.PANEL_HEIGHT / 2;
	
	public static final int OFFSET_CLOSE = (int) (RenderSettings.PANEL_HEIGHT * 0.0333);
	public static final int OFFSET_AVERAGE = (int) (RenderSettings.PANEL_HEIGHT * 0.0666);
	public static final int OFFSET_DISTANT = (int) (RenderSettings.PANEL_HEIGHT * 0.1000);
	
	public static final int KEYBOARD_FONT_SIZE = (int) ((double) RenderSettings.PANEL_HEIGHT * 0.06);
	public static final int MENU_FONT_SIZE = (int) ((double) RenderSettings.PANEL_HEIGHT * 0.1);
	public static final int PAUSE_FONT_SIZE = (int) ((double) RenderSettings.PANEL_HEIGHT * 0.06);
	public static final int RANKING_FONT_SIZE = (int) ((double) RenderSettings.PANEL_HEIGHT * 0.06);
	
	public static final Color FOCUS_COLOR = Color.red;
	public static final Color NOT_FOCUS_COLOR = Color.pink;
	
	private static Font abstractFont;
	
	public static void setFont(Graphics2D g2, int fontSize) {
		if(abstractFont == null) TextParameter.loadAbstractFont();
		Font font = new Font(abstractFont.getFontName(), Font.PLAIN, fontSize);
		g2.setFont(font);
	}
	
	public static int getStringHeight(Graphics2D g2, String text) {
		return (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
	}
	
	public static int getStringWidth(Graphics2D g2, String text) {
		return (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	}
	
	public static int getXForCenteredText(Graphics2D g2, String text) {
		int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = (RenderSettings.PANEL_WIDTH / 2) - (lenght / 2);
		return x;
	}
	
	public static int getYForCenteredText(Graphics2D g2, String text) {
		int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		int y = (RenderSettings.PANEL_HEIGHT / 2) - (lenght / 2);
		return y;
	}
	
	public static void loadAbstractFont() {
		FileHandler file = FileHandler.getInstance();
		abstractFont = file.openFont("font/impact.ttf");
	}
}
