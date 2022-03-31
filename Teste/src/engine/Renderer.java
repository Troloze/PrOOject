package engine;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Comparator;
import java.util.PriorityQueue;

import game.Sprite;

public final class Renderer {
	
	private static Renderer instance;
	private static PriorityQueue<Sprite> gameBuffer = null;

	
	private Renderer() {
		gameBuffer = new PriorityQueue<>(10, new bufferComparator());
	}

	public void render(Graphics2D g2) {
		gameBuffer.forEach(spr -> {
			AffineTransform a;
			a = spr.getRenderInfo();
			g2.drawImage(null, a, null);
		});
	}
	
	public static void updateBuffer(Sprite sprite) {
		if (gameBuffer.remove(sprite)) 
			gameBuffer.add(sprite);
	}
	
	
	public static Renderer getInstance() {
		if (instance == null) {
			instance = new Renderer();
		}
		return instance;
	}
	
	
	private final class bufferComparator implements Comparator<Sprite> {
		public int compare(Sprite o1, Sprite o2) {
			return (o1.getCurrentZPosition() == o2.getCurrentZPosition()) ? 0 : (o1.getCurrentZPosition() > o2.getCurrentZPosition()) ? -1 : 1;
		}
		
	}
}
