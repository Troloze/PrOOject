package game;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.ImageBufferHandler;
import engine.SpriteHolder;
import misc.Misc;
import misc.ShapeData;
import misc.Transform;

public class Background extends Entity implements SpriteHolder {

	
	private static final int sizeX = 27;
	private static final int sizeY = 13;
	private Map<Point, Sprite> tiles;
	private double newTileThreshold;
	private int newTileYValue;
	
	public Background() {
		transform = new Transform();
		transform.getDefaultScale().setLocation(1, 1);
		transform.setZPosition(0);
		tiles = new HashMap<>();
		for (int j = -sizeY; j < sizeY; j++) {
			generateLine(j);
		}
		
		newTileYValue = sizeY;
		newTileThreshold = 43;
	}
	
	private void generateLine(int y) {
		Transform trans = new Transform();
		Sprite spr;
		trans.setScale(1.01);
		trans.getDefaultScale().setLocation(50, 43);
		trans.setZPosition(0);
		Point p;
		for (int i = - sizeX; i < sizeX; i++) {
			p = new Point(i, y);
			trans.getPosition().setLocation(getPosition(p));
			trans.setRotation((getRotation(p)));
			spr = new Sprite(this);
			spr.setOffset(trans);
			spr.set(ImageBufferHandler.TRIANGLE, (getType(p)) ? ImageBufferHandler.T_GRAY1 : ImageBufferHandler.T_GRAY2);
			tiles.put(p, spr);			
		}
	}
	
	private Point2D getPosition(Point source) {
		return new Point2D.Double(source.getX() * 25, (-source.getY() * 43) + (isDown(source) ? -43.0/3.0 : 0));
	}
	
	private double getRotation(Point source) { 		
		return (isDown(source)) ? 180 : 0;
	}
	
	private boolean getType(Point source) {
		return (isDown(source)) ? true : false;
	}
	
	private boolean isDown(Point source) {
		Point t = new Point();
		t.setLocation(
				(source.getX() < 0) ? - source.getX() : source.getX(),
				(source.getY() < 0) ? - source.getY() : source.getY()
				);
		return (t.getX()%2 != t.getY()%2);
	}
	
	private void destroyLine(int y) {
		Point p = new Point();
		Sprite spr;
		for (int i = -sizeX; i < sizeX; i++) {
			p.setLocation(i, y);
			spr = tiles.get(p);
			if(spr == null) continue;
			spr.destroy();
		}
	}
	
	private Sprite pop(Point point) {
		Sprite spr;
		spr = tiles.get(point);
		if (spr == null) return null;
		tiles.remove(point);
		spr.set(ImageBufferHandler.TRIANGLE, ImageBufferHandler.T_ORANGE1);
		spr.setAlpha(1.0f);
		return spr;
	}
	
	private Sprite[] massPop(Point center, int[][] it, boolean isDown) {
		if (it == null) return null;
		Sprite[] ret = new Sprite[it.length];
		Point p = new Point();
		for (int i = 0; i < it.length; i++) {
			p.setLocation(center.getX() + it[i][0], center.getY() + ((isDown) ? (it[i][1]) : it[i][1]));
			
			ret[i] = pop(p);
			if (ret[i] == null) return null;
		}
		return ret;
	}
	
	private boolean test(Point point) {
		if (tiles.get(point) == null) return false;
		return true;
	}
	
	private boolean massTest(Point center, int[][] it, boolean isDown) {
		if (it == null) return false;
		Point p = new Point();
		for (int i = 0; i < it.length; i++) {
			p.setLocation(center.getX() + it[i][0], center.getY() + ((isDown) ? (-it[i][1]) : it[i][1]));
			
			if (!test(p)) return false;
		}
		return true;
	}
		
	private Cluster getEnemy(Point orig, ShapeData sd) {
		if (sd == null || orig == null) return null;
		Point p = new Point();
		
		List<Point> coords = new ArrayList<>();
		List<Sprite> sprites = new ArrayList<>();
		List<Transform> transf = new ArrayList<>();
		
		int[][][] iterate;
		int[][] toIterate;
		int[][] toReturn;
		int n, k;
		Sprite[] spriteList;
		boolean isDown = (isDown(orig));
		if (isDown) orig.setLocation(orig.getX(), orig.getY() + 1);
		isDown = false;
		Point2D worldPos = getPosition(orig);
		Transform newTrans = new Transform(transform);
		newTrans.getPosition().setLocation(worldPos);
		
		Cluster retCluster = new Cluster(newTrans);

		iterate = sd.shape;
		toReturn = iterate[0];
		k = 0;
		n = (int) (Math.random() * 2);
		while (k < 3) {
			toIterate = iterate[n];
			if (massTest(orig, toIterate, isDown)) {
				spriteList = massPop(orig, toIterate, isDown);
				if(spriteList == null) return null;
				for (int i = 0; i < spriteList.length; i++) {
					newTrans = new Transform(transform);
					newTrans.setScale(1);
				
					newTrans.getDefaultScale().setLocation(50, 43);
					p = new Point();
					p.setLocation(toReturn[i][0], toReturn[i][1]);
					worldPos = getPosition(p);
					newTrans.getPosition().setLocation(worldPos);
					coords.add(p);
					newTrans.setRotation(getRotation(p));
					sprites.add(spriteList[i]);
					transf.add(newTrans);
				}
				break;
			}
			n = (n + 1)%3;
			k++;
		}
		retCluster.generateCluster(sd.size, coords, sprites, transf);
		retCluster.getTransform().setRotation(sd.angle[n]);
		retCluster.offsetCenter(new Point2D.Double(/*50 * sd.offsetX[n], ((isDown) ? -1 : 1) * 43 * sd.offsetY[n]/**/));

		return retCluster;
	}
	// Nunca usar nenhuma dessas duas, não houve tempo o suficiente pra resolver todos os problemas!!
	@SuppressWarnings("unused")
	private Cluster getEnemyFromScreen(Point2D orig, ShapeData sd) {
		return getEnemy(Misc.Background.world2Back(transform.getPosition(), orig), sd);
	}
	
	@Override
	public Transform getTransform() {
		return transform;
	}
	
	@Override
	public void update(double delta) {
		transform.getPosition().setLocation(transform.getPosition().getX(), transform.getPosition().getY() + 100 * delta);
		if (transform.getPosition().getY() > newTileThreshold) {
			generateLine(newTileYValue);
			destroyLine(newTileYValue - 2 * sizeY);
			newTileYValue += 1;
			newTileThreshold += 43;
		}
	}

	@Override
	public void destroy() {
		for (Sprite spr : tiles.values()) {
			if (spr != null) spr.destroy();
		}
		tiles.clear();
		tiles = null;
		destroyed = true;
	}

}
