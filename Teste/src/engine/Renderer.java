package engine;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.PriorityQueue;

import game.Sprite;

public final class Renderer {

	private static Renderer instance;
	private static PriorityQueue<Sprite> gameBuffer = null;
	private static PriorityQueue<Sprite> gameBuffer2 = null;
	

	
	private Renderer() {
		gameBuffer = new PriorityQueue<>(10, new bufferComparator());
	}
	
	public void add(Sprite spr) {
		gameBuffer.add(spr);
	}
	

	
	public void render(Graphics2D g2) {
		BufferedImage im;
		ImageData data;
		float alpha = 1.0f;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
		g2.setComposite(ac);
		
		int size = gameBuffer.size();
		gameBuffer2 = new PriorityQueue<>(gameBuffer);
		
		for (int i = 0; i < size; i++) {
			data = gameBuffer2.poll().getRenderInfo();
			//System.out.println(data);
			if (data.visible) {
				im = ImageBufferHandler.getImage(data.type, data.color, data.quality);
				if (data.alpha != alpha) {
					ac = ac.derive(data.alpha); 
					alpha = data.alpha;
					g2.setComposite(ac);
				}
				
				g2.drawImage(im, data.at, null);
			}
		}
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
