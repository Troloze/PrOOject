package game;

public final class Camera {
	
	private static Camera instance;
	
	private double x;
	private double y;
	private double z;
	
	private Camera() {
		reset();
	}
	
	public static Camera getInstance() {
		if (instance == null) {
			instance = new Camera();
		}
		return instance;
	}
	
	public void move(double dX, double dY) {
		x += dX;
		y += dY;
	}
	
	public void moveZ(double dZ) {
		z += dZ;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double nX) {
		x = nX;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double nY) {
		y = nY;
	}
	
	public double getZ() {
		return z;
	}
	
	

	public void setZ(double nZ) {
		z = nZ;
	}
	
	public void reset() {
		x = 0;
		y = 0;
		z = 10.0;
	}
}
