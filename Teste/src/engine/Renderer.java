package engine;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import game.Sprite;
import misc.ImageData;

public final class Renderer {

	private static Renderer instance;
	private static List<Sprite> addQueue = null;
	private static List<Sprite> destroyQueue = null;
	private static List<Sprite> testBuffer = null;
		
	private static bufferComparator bC;

	private static boolean toUpdate = false;
	
	private Renderer() {
		addQueue = new ArrayList<>();
		destroyQueue = new ArrayList<>();
		bC = new bufferComparator();
		testBuffer = new ArrayList<>();
	}
	
	public void add(Sprite spr) {
		if (spr == null) return;
		addQueue.add(spr);
		

	}
	
	private void addQueued() {
		for (Sprite spr : addQueue) {
			testBuffer.add(spr);
		}
		addQueue.clear();
	}

	public static void updateBuffer() {
		toUpdate = true;
	}
	
	public static void updateQueued() {
		if (toUpdate) testBuffer.sort(bC);
	}
		
	public void destroy(Sprite spr) {
		if (spr == null) return;
		destroyQueue.add(spr);
	}
	
	private void destroyQueued() {
		for (Sprite spr : destroyQueue) {
			testBuffer.remove(spr);
		}
		destroyQueue.clear();
	}
	
	public void render(Graphics2D g2) {
		BufferedImage im;
		ImageData data;
		float alpha = 1.0f;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
		g2.setComposite(ac);
		
		/*
		gameBuffer2 = new PriorityQueue<>(gameBuffer);
		while (!gameBuffer2.isEmpty()) {
			spr = gameBuffer2.poll();
			if (spr.isDestroyed()) continue;
			data = spr.getRenderInfo();
			
			if (data.visible) {
				im = ImageBufferHandler.getImage(data.type, data.color, data.quality);
				
				if (data.alpha != alpha) {
					ac = ac.derive(data.alpha); 
					alpha = data.alpha;
					g2.setComposite(ac);
				}
				//System.out.println(data.at);

				//g2.setColor(Color.red);
				//g2.drawOval((int) data.at.getTranslateX(), (int) data.at.getTranslateY(), 10, 10);
				g2.drawImage(im, data.at, null);
			}
		}/**/
		
		for (Sprite spr : testBuffer) {
			if (spr.isDestroyed()) continue;
			data = spr.getRenderInfo();
			
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
		addQueued();
		updateQueued();
		destroyQueued();
	}
	
	public static Renderer getInstance() {
		if (instance == null) {
			instance = new Renderer();
		}
		return instance;
	}
	
	private final class bufferComparator implements Comparator<Sprite> {
		
		public int compare(Sprite o1, Sprite o2) {
			return (o1.getCurrentZPosition() == o2.getCurrentZPosition()) ? 0 : (o1.getCurrentZPosition() > o2.getCurrentZPosition()) ? 1 : -1;
		}
		
	}
}
