package game;

import java.awt.geom.Point2D;

import misc.Misc;

public abstract class Hazard extends Entity{
	protected double rotationSpeed;
	protected double rotationAcceleration;
	protected double rotationDrag;
	protected double speed;
	protected double acceleration;
	protected double drag;
	protected double direction = 0;
	protected double life;
	protected double damage;
	
	protected Point2D directionRate = new Point2D.Double(1.0, 0.0);
	protected double oldDirection = 0;
	
	protected double startTime;
	protected double lifeTime;
	protected Pattern pattern;
	
	protected boolean alive;
	
	protected Collider collider;
	
	protected Hazard() {
		collider = new Collider();
		alive = true;
		startTime = System.nanoTime()/Misc.Other.nanoSecond;
		pattern = null;
	}
	
	protected Hazard(Pattern pat) {
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
		
		transform.rotation += rotationSpeed;
		
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
	 * Lembrar de n�o permitir que atualize se n�o estiver vivo.
	 */
	@Override
	public void update(double delta) {
		if (!alive) return;
		double currentTime = System.nanoTime()/Misc.Other.nanoSecond;
		updateStats();
		
		if (pattern != null) pattern.cast(startTime, currentTime);
		if (currentTime >= startTime + lifeTime) destroy();
	}
	
	public Collider getCollider() {
		return collider;
	}
		
	public double getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public double getRotationAcceleration() {
		return rotationAcceleration;
	}

	public void setRotationAcceleration(double rotationAcceleration) {
		this.rotationAcceleration = rotationAcceleration;
	}

	public double getRotationDrag() {
		return rotationDrag;
	}

	public void setRotationDrag(double rotationDrag) {
		this.rotationDrag = rotationDrag;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getDrag() {
		return drag;
	}

	public void setDrag(double drag) {
		this.drag = drag;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getOldDirection() {
		return oldDirection;
	}

	public void setOldDirection(double oldDirection) {
		this.oldDirection = oldDirection;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(double lifeTime) {
		this.lifeTime = lifeTime;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public boolean isAlive() {
		return alive;
	}


	public void setDirectionRate(Point2D directionRate) {
		this.directionRate = directionRate;
	}

	public void setCollider(Collider collider) {
		this.collider = collider;
	}

	@Override
	public void destroy() {
		if (pattern != null) pattern.onEnd(this);
		if (pattern != null) pattern.onDeath(this);
	}

	public abstract void onCollision(Hazard entity);
}
