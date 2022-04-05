package game;


import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.SpriteHolder;
import misc.Transform;

public class Cluster implements SpriteHolder {
	
	
	private Transform transform;
	private List<Point> coordinates;
	private Map<Point, Sprite> sprites;
	private Map<Sprite, Transform> offset;
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
			transform = relativeTransform(transforms.get(i));
			this.coordinates.add(coord);
			this.sprites.put(coord, sprite);
			this.offset.put(sprite, transform);
		}
	}
	
	public Cluster split(int n, int[] values) {
		
		return null;
	}
	
	public Point2D relativeDistance(Point2D point) {
		return new Point2D.Double(point.getX() - transform.position.getX(), point.getY() - transform.position.getY());
	}
	
	public Transform relativeTransform(Transform transform) {
		Transform retTransform = new Transform();
		
		retTransform.position.setLocation(
				transform.position.getX() - this.transform.position.getX(),
				transform.position.getY() - this.transform.position.getY());
		
		retTransform.scale.setLocation(
				transform.scale.getX() - this.transform.scale.getX(),
				transform.scale.getY() - this.transform.scale.getY());
		
		retTransform.rotation = transform.rotation - this.transform.rotation;
		retTransform.zPosition = transform.zPosition - this.transform.zPosition;
		
		return retTransform;
	}
	
	public void updateCenter() {
		
	}
	
	
	
	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return transform;
	}
	
	
	public void destroy() {
		for (Sprite spr: sprites.values()) {
			if (!spr.isDestroyed()) spr.destroy();
			spr = null;
		}
		offset.clear();
		sprites.clear();
		offset = null;
		sprites = null;
		destroyed = true;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
	
}
