package game;

import java.awt.geom.Point2D;

public abstract class Hazard {
	public double direction = 0.0;
	private double oldDirection = 0.0;
	private Point2D.Double directionRates = new Point2D.Double(1, 0);
	public double rotationSpeed = 0.0;
	public double rotationAcceleration = 0.0;
	public double rotationDrag = 0.0;
	
	public Point2D.Double speed = new Point2D.Double();
	public double acceleration = 0.0;
	public double drag = 0.0;
	
	public boolean isHittable = false;
	public double hitboxSize = 0.0;
	
	public boolean isHazard = false;
	public double damageboxSize = 0.0;
	
	public double life = 0.0;
	
	private double startTime;
	private Pattern pattern;
	
	public Hazard() {
		this.pattern = null;
		startTime = System.nanoTime()/1000000000.0;
	}
	
	public Hazard(Pattern pattern) {
		this.pattern = pattern;
		startTime = System.nanoTime()/1000000000.0;
	}	
	
	public Point2D.Double getDirectionRates() {
		return directionRates;
	}
	
	public void updateAtributes(double delta) {
		double accelerationResultant = rotationAcceleration - rotationDrag;
		rotationSpeed += accelerationResultant * delta;
		if (accelerationResultant <= 0) {
			if (rotationSpeed < 0) rotationSpeed = 0;
		}
		direction += rotationSpeed * delta;
		if (oldDirection != direction) {
			directionRates.x = Math.cos(Math.toRadians(direction));
			directionRates.y = Math.sin(Math.toRadians(direction));
		}
		accelerationResultant = acceleration - drag;
		
		speed.x += accelerationResultant * directionRates.x * delta;
		speed.y += accelerationResultant * directionRates.y * delta;
		
		oldDirection = direction;
	}
	
	public void hit(double damage) {
		life -= damage;
		if (life <= 0.0) {
			if (pattern != null) pattern.onDeath();
			pattern = null;
		}
	}
}
