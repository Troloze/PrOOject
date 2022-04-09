package misc;


import game.patterns.Pattern;

public class InstanceParams {
	public Transform transform = null;
	
	public ImageData spriteData = null;
	
	public double rotationSpeed = 0.0;
	public double rotationAcceleration = 0.0;
	public double rotationDrag = 0.0;
	public double speed = 0.0;
	public double acceleration = 0.0;
	public double drag = 0.0;
	public double direction = 0.0;
	public double life = 0.0;
	public double damage = 0.0;
	public double lifeTime = 1.0;
	public double value = 5;
	
	public Pattern pattern = null;
	
	public InstanceParams() {
		spriteData = new ImageData();
		transform = new Transform();
		spriteData.alpha = 1.0f;
		transform.setScale(1);
	}
	
}
