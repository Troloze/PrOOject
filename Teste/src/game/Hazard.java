package game;

import java.awt.geom.Point2D;

import misc.Misc;

public abstract class Hazard extends Entity{
	public double rotation;
	public double rotationSpeed;
	public double rotationAcceleration;
	public double rotationDrag;
	public double speed;
	public double acceleration;
	public double drag;
	public double direction = 0;
	public double hitboxRadius;
	public double damageboxRadius;
	public double life;
	public double damage;
	
	private Point2D directionRate = new Point2D.Double(1.0, 0.0);
	private double oldDirection = 0;
	
	private double startTime;
	private double lifeTime;
	private Pattern pattern;
	
	private boolean alive;
	
	Hazard() {
		alive = true;
		startTime = System.nanoTime()/Misc.Other.nanoSecond;
		pattern = null;
	}
	
	Hazard(Pattern pat) {
		alive = true;
		startTime = System.nanoTime()/Misc.Other.nanoSecond;
		this.pattern = pat;
		pattern.onStart(this);
	}
	
	public Point2D getDirectionRate() {
		return directionRate;
	}
	
	private void updateStats() {
		double accelResultant;
		double oldSpeed;
		
		oldSpeed = rotationSpeed;
		if (rotationSpeed > 0) {
			accelResultant = rotationAcceleration - rotationDrag;

			rotationSpeed += accelResultant;
			if (oldSpeed >= 0 && rotationSpeed < 0) 
				rotationSpeed = 0;
		}
		else {
			accelResultant = rotationAcceleration + rotationDrag;
			rotationSpeed += accelResultant;
			
			if (oldSpeed <= 0 && rotationSpeed > 0) 
				rotationSpeed = 0;
		}
		
		rotation += rotationSpeed;
		
		oldSpeed = speed;
		if (speed > 0) {
			accelResultant = acceleration - drag;

			speed += accelResultant;
			if (oldSpeed >= 0 && speed < 0) 
				speed = 0;
		}
		else {
			accelResultant = acceleration + drag;
			speed += accelResultant;
			
			if (oldSpeed <= 0 && speed > 0) 
				speed = 0;
		}
		
		if (oldDirection != direction) {
			directionRate.setLocation(Math.cos(direction), Math.sin(direction));
			oldDirection = direction;
		}
	}
	
	public void hit(double damage) {
		if (!alive) return;
		life -= damage;
		if (life <= 0) {
			alive = false;
			destroy();
		}
	}
	
	/**
	 * Lembrar de não permitir que atualize se não estiver vivo.
	 */
	public void update() {
		if (!alive) return;
		double currentTime = System.nanoTime()/Misc.Other.nanoSecond;
		updateStats();
		
		if (pattern != null) pattern.cast(startTime, currentTime);
		if (currentTime >= startTime + lifeTime) destroy();
	}
	
	@Override
	public void destroy() {
		if (pattern != null) pattern.onEnd(this);
		if (pattern != null) pattern.onDeath(this);
	}

	public abstract void onCollision(Hazard entity);
}
