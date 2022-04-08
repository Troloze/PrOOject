package engine;

public class RenderSettings {
	public static final int GAME_WIDTH = 1280;
	public static final int GAME_HEIGHT = 960;
	public static final double PANEL_RATE = 0.75;
	public static final int PANEL_WIDTH = (int) (GAME_WIDTH * PANEL_RATE);
	public static final int PANEL_HEIGHT = (int) (GAME_HEIGHT * PANEL_RATE);
	
	public static final int PANEL_WIDTH_CENTER = PANEL_WIDTH / 2;
	public static final int PANEL_HEIGHT_CENTER = PANEL_HEIGHT / 2;
	
	public static final double BASE_TRIANGLE_WIDTH = 50;
	public static final double BASE_TRIANGLE_HEIGHT = 43;
	
	public static final double BASE_BULLET_SIZE = 50;
}
