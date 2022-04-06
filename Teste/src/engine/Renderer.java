package engine;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import game.Sprite;
import misc.ImageData;

public final class Renderer {

	private static Renderer instance;
	private static List<Sprite> addQueue = null;
	private static List<Sprite> updateQueue = null;
	private static List<Sprite> destroyQueue = null;
	private static PriorityQueue<Sprite> gameBuffer = null;
	private static PriorityQueue<Sprite> gameBuffer2 = null;
	
	private static boolean renderLock = false;
	
	private Renderer() {
		addQueue = new ArrayList<>();
		updateQueue = new ArrayList<>();
		destroyQueue = new ArrayList<>();
		gameBuffer = new PriorityQueue<>(100, new bufferComparator());
	}
	
	public void add(Sprite spr) {
		if (spr == null) return;
		if (!renderLock) {
			gameBuffer.add(spr);
			return;
		}
		addQueue.add(spr);
	}
	
	private void addQueued() {
		for (Sprite spr : addQueue) {
			add(spr);
		}
		addQueue.clear();
	}

	public static void updateBuffer(Sprite spr) {
		if (spr == null) return;
		if (!renderLock) {
			if (gameBuffer.remove(spr)) gameBuffer.add(spr);
			return;
		}
		updateQueue.add(spr);
	}
	
	private void updateQueued() {
		for (Sprite spr : updateQueue) {
			updateBuffer(spr);
		}
		updateQueue.clear();
	}
	
	public void destroy(Sprite spr) {
		if (spr == null) return;
		if (!renderLock) {
			gameBuffer.remove(spr);
			return;
		}
		destroyQueue.add(spr);
	}
	
	private void destroyQueued() {
		for (Sprite spr : destroyQueue) {
			destroy(spr);
		}
		destroyQueue.clear();
	}
	
	public void render(Graphics2D g2) {
		renderLock = true;
		BufferedImage im;
		ImageData data;
		Sprite spr;
		float alpha = 1.0f;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
		g2.setComposite(ac);

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
		}
		renderLock = false;
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
