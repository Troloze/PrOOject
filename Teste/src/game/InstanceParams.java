package game;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Map;

public class InstanceParams {
	public Point2D positionDouble = null;
	public Point positionInt = null;
	public Point2D scale = null;
	
	public double rotation = 0.0;
	public double rotationSpeed = 0.0;
	public double rotationAcceleration = 0.0;
	public double rotationDrag = 0.0;
	public double speed = 0.0;
	public double acceleration = 0.0;
	public double drag = 0.0;
	public double direction = 0.0;
	public double hitboxRadius = 0.0;
	public double damageboxRadius = 0.0;
	public double life = 0.0;
	public double damage = 0.0;
	public double lifeTime = 0.0;
	
	public Pattern pattern = null;
	
	public Map<String, String> other;
}
