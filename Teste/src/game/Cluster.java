package game;


import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.SpriteHolder;
import misc.Transform;

public class Cluster implements SpriteHolder {
	
	
	private Entity body;
	private Transform transform;
	private int clusterSize;
	private List<Point> coordinates;
	private Map<Point, Sprite> sprites;
	private Map<Point, Transform> offset;
	private boolean destroyed;
	
	public Cluster() {
		coordinates = new ArrayList<>();
		sprites = new HashMap<>();
		offset = new HashMap<>();
		transform = new Transform();
	}
	
	public Cluster(Transform transform) {
		this.coordinates = new ArrayList<>();
		this.sprites = new HashMap<>();
		this.offset = new HashMap<>();
		this.transform = new Transform(transform);
	}
	
	public void generateCluster(int n, List<Point> coordinates, List<Sprite> sprites, List<Transform> transforms) {
		if (coordinates == null || sprites == null) return;
		if ((n != coordinates.size()) || (n != sprites.size()) || (n != transforms.size())) return;
		Point coord;
		Sprite sprite;
		Transform transform;
		for (int i = 0; i < n; i++) {
			coord = coordinates.get(i);
			sprite = sprites.get(i);
			sprite.setBody(this);
			transform = relativeTransform(transforms.get(i));
			sprite.setOffset(transform);
			this.coordinates.add(coord);
			this.sprites.put(coord, sprite);
			this.offset.put(coord, transform);
		}
		clusterSize = n;
	}
	
	public Cluster split(int n, int[] values, Point2D offNew, Point2D offOld) {
		if (n != values.length) return null;
		if (n > clusterSize) {
			System.out.println("Invalid cluster split request: invalid ammount");
			return null;
		}
		Point p = coordinates.get(values[0]);
		Sprite spr = sprites.get(p);
		Transform trans = offset.get(p);
		Cluster retCluster = new Cluster(trans);
		retCluster.offsetCenter(offNew);

		List<Point> nCoords = new ArrayList<>();
		List<Sprite> nSprites = new ArrayList<>();
		List<Transform> nOffset = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			if (values[i] > n) {
				System.out.println("Invalid cluster split request: Invalid index");
				continue;
			}
			p = coordinates.get(values[i]);
			coordinates.remove(values[i]);
			
			spr = sprites.get(p);
			sprites.remove(p);
			
			trans = offset.get(p);
			offset.remove(p);
			
			nCoords.add(p);
			nSprites.add(spr);
			nOffset.add(trans);
		}
		
		retCluster.generateCluster(n, nCoords, nSprites, nOffset);
		
		this.offsetCenter(offOld);
		
		return retCluster;
	}
	
	public Point2D relativeDistance(Point2D point) {
		return new Point2D.Double(point.getX() - transform.getPosition().getX(), point.getY() - transform.getPosition().getY());
	}
	
	public Transform relativeTransform(Transform transform) {
		Transform retTransform = new Transform(transform);
		
		retTransform.getPosition().setLocation(
				transform.getPosition().getX() - this.transform.getPosition().getX(),
				transform.getPosition().getY() - this.transform.getPosition().getY());
		
		//retTransform.setRotation(transform.getRotation() - this.transform.getRotation());
		retTransform.setZPosition(0);
		return retTransform;
	}
	
	public void offsetCenter(Point2D off) {
		if (off == null) return;
		if (off.getX() == off.getY() && off.getY() == 0.0) return;
		Point2D tPos = transform.getPosition();
		Transform trans;
		transform.getPosition().setLocation(tPos.getX() + off.getY(), tPos.getY() + off.getY());
		for (Point p : coordinates) {
			trans = offset.get(p);
			tPos = trans.getPosition();
			trans.getPosition().setLocation(tPos.getX() + off.getY(), tPos.getY() + off.getY());
			sprites.get(p).setOffset(trans);
		}
	}
	
	public void update(double delta) {
		transform.setRotation(transform.getRotation() + 100 * delta);
		if (body == null) return;
		if (body.isDestroyed()) return;
		transform.follow(body.getTransform(), Transform.FOLLOW_NOT_DEFAULT_SCALE);			
	}
	
	public int getClusterSize() {
		return clusterSize;
	}
	
	@Override
	public Transform getTransform() {
		return transform;
	}
	
	public void destroy() {
		for (Sprite spr: sprites.values()) {
			if (!spr.isDestroyed()) spr.destroy();
			spr = null;
		}
		
		offset.clear();
		sprites.clear();
		coordinates.clear();
		offset = null;
		sprites = null;
		body = null;
		destroyed = true;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
}
